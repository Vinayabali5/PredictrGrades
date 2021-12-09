import { Component, OnInit, ViewChild, ElementRef, Input, Inject  } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { HttpEventType, HttpErrorResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { UploadService } from  '../upload.service';

class DataModel {
  studentId: number
}

@Component({
  selector: 'app-upload-evidence',
  templateUrl: './upload-evidence.component.html',
  styleUrls: ['./upload-evidence.component.css']
})
export class UploadEvidenceComponent implements OnInit {

  @ViewChild("fileUpload", {static: false})
  fileUpload: ElementRef;
  files  = [];

  constructor(
    private uploadService: UploadService,
    @Inject(MAT_DIALOG_DATA) public data: DataModel
  ) {

  }

  ngOnInit(): void {
  }

  uploadFile(file) {
    const formData = new FormData();
    formData.append('studentId', this.data.studentId.toString());
    formData.append('file', file.data);
    file.inProgress = true;
    this.uploadService.upload(formData).pipe(
      map(event => {
        switch (event.type) {
          case HttpEventType.UploadProgress:
            file.progress = Math.round(event.loaded * 100 / event.total);
            break;
          case HttpEventType.Response:
            file.inProgress = false;
            file.success = true;
            return event;
        }
      }),
      catchError((error: HttpErrorResponse) => {
        console.log(error);
        file.inProgress = false;
        file.success = false;
        file.error = error.error.message;
        return of(`${file.data.name} upload failed.`);
      })
    ).subscribe(
      (event: any) => {
        if (typeof (event) === 'object') {
          if (event.body !== null) console.log(event.body);
        }
      }
    );
  }

  private uploadFiles() {
    this.fileUpload.nativeElement.value = '';
    this.files.forEach(file => {
      if (file.progress < 100) {
        this.uploadFile(file);
      }
    });
    // this.files = [];
  }

  onClick() {
    const fileUpload = this.fileUpload.nativeElement;
    fileUpload.onchange = () => {
      for (let index = 0; index < fileUpload.files.length; index++) {
       const file = fileUpload.files[index];
       this.files.push({
         name: file.name,
         data: file,
         inProgress: false,
         progress: 0
       });
      }
      this.uploadFiles();
    };
    fileUpload.click();
  }

}
