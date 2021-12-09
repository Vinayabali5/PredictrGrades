import { Component, Input, Inject } from '@angular/core';
import { FormGroup,  FormBuilder,  Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

import { AppConfigService } from '../app-config.service';

import { gradeValidator } from '../validators/grade.validator';

import { StudentQualification } from '../model/student-qualification';
import { StudentQualificationService } from '../student-qualification.service';

import { StudentQualificationFormData } from '../model/forms/student-qualification-form-data';
import { StudentQualificationDTO } from '../model/dto/student-qualification-dto';

@Component({
  selector: 'app-add-student-qualification',
  templateUrl: './add-student-qualification.component.html',
  styleUrls: ['./add-student-qualification.component.css']
})
export class AddStudentQualificationComponent  {

  public useMockGrade;

  public studentQualfication: StudentQualificationFormData;
  public qualificationTypeId: number = 0;

  public form: FormGroup;

  public ctls;

  constructor(
    protected config: AppConfigService,
    private formBuilder: FormBuilder,
    private studentQualficaionService: StudentQualificationService,
    public dialogRef: MatDialogRef<AddStudentQualificationComponent>,
    @Inject(MAT_DIALOG_DATA) public data: StudentQualificationFormData
  ) {
    this.useMockGrade = config.getConfig().useMockGrade;
    //IFD: console.log(data);
    this.form = this.formBuilder.group({
      studentId: [data.studentId],
      qualificationId: [null, [
        Validators.required
      ]],
      grade: [null, [
        Validators.required,
        gradeValidator
      ]],
      examBoardId: [null, []],
      mockGrade: [null, [
        gradeValidator
      ]]
    });
    //IFD: console.log(this.form);
    this.ctls = {
       qualificationId: this.form.get('qualificationId'),
       grade: this.form.get('grade'),
       examBoardId: this.form.get('examBoardId'),
       mockGrade: this.form.get('mockGrade'),
    }
    this.studentQualfication = data;
    //IFD: console.log(data);
  }

  add(): void {
    console.log("Attempting to add qualification: ");
    //IFD: console.log(this.studentQualfication);
    //IFD: console.log(this.form);

    let dto: StudentQualificationDTO = new StudentQualificationDTO(this.form.value);
    //IFD: console.log(dto);

    this.studentQualficaionService.create(dto).subscribe(
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

  clearQualification(): void {
    if (this.qualificationTypeId != undefined && this.qualificationTypeId != 0) {
      this.studentQualfication.qualificationId = undefined;
    }
  }

  displayFormContent() {
    console.log(this.form);
  }

}
