import { Component, Inject, Input } from '@angular/core';
import { FormGroup, FormBuilder,  Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

import { AppConfigService } from '../app-config.service';

import { gradeValidator } from '../validators/grade.validator';

import { StudentQualificationService } from '../student-qualification.service';

import { StudentQualificationFormData } from '../model/forms/student-qualification-form-data';
import { StudentQualificationDTO } from '../model/dto/student-qualification-dto';

@Component({
  selector: 'app-edit-student-qualification',
  templateUrl: './edit-student-qualification.component.html',
  styleUrls: ['./edit-student-qualification.component.css']
})
export class EditStudentQualificationComponent {

  public useMockGrade;

  @Input() studentQualficaion: StudentQualificationFormData;

  public form: FormGroup;

  public ctls;

  constructor(
    protected config: AppConfigService,
    private formBuilder: FormBuilder,
    private studentQualficaionService: StudentQualificationService,
    public dialogRef: MatDialogRef<EditStudentQualificationComponent>,
    @Inject(MAT_DIALOG_DATA) public data: StudentQualificationFormData
  ) {
    this.useMockGrade = config.getConfig().useMockGrade;
    
    this.studentQualficaion = data;
    console.log(data);

    this.form = this.formBuilder.group({
      id: [data.id],
      studentId: [data.studentId],
      qualificationId: [data.qualificationId],
      grade: [data.grade, [
        Validators.required,
        gradeValidator
      ]],
      examBoardId: [data.examBoardId],
      mockGrade: [data.mockGrade, [
        gradeValidator
      ]]
    });
    //IFD: console.log(this.form);
    this.ctls = {
       grade: this.form.get('grade'),
       mockGrade: this.form.get('mockGrade'),
    }
  }

  public save(): void {
    console.log("Attempting save.");
    // Convert form data into DTO
    let dto: StudentQualificationDTO = new StudentQualificationDTO(this.form.value);
    //IFD: console.log(dto);
    // Send DTO to service for processing
    this.studentQualficaionService.update(dto).subscribe(
      response => {
        //IFD: console.log(response);
        this.dialogRef.close({
          result: response
        });
      },
      // TODO: add errorhandling
      error => {
        console.log(error);
      }
    );
  }

}
