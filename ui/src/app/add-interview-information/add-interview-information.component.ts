import { Component, Inject, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AppConfigService } from '../app-config.service';
import { StudentInterviewInformationDTO } from '../model/dto/student-interview-information-dto';
import { StudentInterviewInformation } from '../model/student-interview-information';
import { StudentService } from '../student.service';

@Component({
  selector: 'app-add-interview-information',
  templateUrl: './add-interview-information.component.html',
  styleUrls: ['./add-interview-information.component.css']
})
export class AddInterviewInformationComponent {

  public studentInterviewInformation: StudentInterviewInformation;

  public form: FormGroup;

  public stls;
  courseworkOrExams: any;

  constructor(
    protected config: AppConfigService,
    private formBuilder: FormBuilder,
    private studentService:StudentService,
    public dialogRef: MatDialogRef<AddInterviewInformationComponent>,
    @Inject(MAT_DIALOG_DATA) public data: StudentInterviewInformation
  ) {

    this.studentInterviewInformation = data;
    console.log(data);

    this.form = this.formBuilder.group({
      id: [data.id],
      linkId:[data.linkId],
      careerAims: [data.careerAims],
      workVoluntaryExperience: [data.workVoluntaryExperience],
      hobbiesInterestsOtherAchievements: [data.hobbiesInterestsOtherAchievements],
      favoriteSubject: [data.favoriteSubject],
      courseworkOrExams: [data.courseworkOrExams],
      reasonsForReigateCollege: [data.reasonsForReigateCollege],
      otherSchoolCollegeAppliedFor:[data.otherSchoolCollegeAppliedFor],
      learningSupportNeedsDetails:[data.learningSupportNeedsDetails]
    });

    this.stls = {
      careerAims: this.form.get('careerAims'),
      workVoluntaryExperience: this.form.get('workVoluntaryExperience'),
      hobbiesInterestsOtherAchievements: this.form.get('hobbiesInterestsOtherAchievements'),
      favoriteSubject: this.form.get('favoriteSubject'),
      courseworkOrExams: this.form.get('courseworkOrExams'),
      reasonsForReigateCollege: this.form.get('reasonsForReigateCollege'),
      otherSchoolCollegeAppliedFor: this.form.get('otherSchoolCollegeAppliedFor'),
      learningSupportNeedsDetails: this.form.get('learningSupportNeedsDetails')
   }
   
   this.studentInterviewInformation = data;

   }

   Coursework: any = ['Coursework', 'Exam', 'Both']

   changeCourseWork(e) {
    this.courseworkOrExams.setValue(e.target.value, {
      onlySelf: true
    })
  }

   add(): void {
    console.log("Attempting to edit student interview details: ");
    console.log(this.studentInterviewInformation);
    //IFD: console.log(this.form);

    let dto: StudentInterviewInformationDTO = new StudentInterviewInformationDTO(this.form.value);
    //IFD: console.log(dto);

    this.studentService.update(dto).subscribe(
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
      this.studentInterviewInformation.id = undefined;
  }

  displayFormContent() {
    console.log(this.form);
  }

}
