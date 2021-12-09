import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import 'bootbox';

import { AppConfigService } from '../app-config.service';

import { MatDialog, MatDialogRef } from '@angular/material/dialog';

import { Student } from '../model/student';
import { StudentQualification } from '../model/student-qualification';

import { StudentQualificationFormData } from '../model/forms/student-qualification-form-data'

import { StudentService } from '../student.service';
import { StudentQualificationService } from '../student-qualification.service';
import { StudentInterviewInformation } from '../model/student-interview-information';


import { StudentQualificationDialogData } from '../student-qualification-dialog-data';
import { AddStudentQualificationComponent } from '../add-student-qualification/add-student-qualification.component';
import { EditStudentQualificationComponent } from '../edit-student-qualification/edit-student-qualification.component';
import { UploadEvidenceComponent } from '../upload-evidence/upload-evidence.component';
import { AddInterviewInformationComponent } from '../add-interview-information/add-interview-information.component';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit {

  public useMockGrade;

  private linkId: string;

  @Input() student: Student;

  constructor(
    protected config: AppConfigService,
    private route: ActivatedRoute,
    private studentService: StudentService,
    private studentQualificationService: StudentQualificationService,
    private location: Location,
    private matDialog: MatDialog
  ) {
    this.useMockGrade = config.getConfig().useMockGrade;
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe( params => {
       this.linkId = params['linkId'];
       this.getStudent();
     });
  }

  getStudent(): void {
    if (this.linkId !== undefined) {
      this.studentService.getByLinkId(this.linkId).subscribe(student => {
        this.student = student;
        this.student.qualifications.sort((a,b) => {
          var aTitle = a.qualification.type.type + ' ' + a.qualification.title;
          var bTitle = b.qualification.type.type + ' ' + b.qualification.title;
          if (aTitle < bTitle) {
            return -1;
          } else if (aTitle > bTitle) {
            return 1
          }
          return 0;
        })
      });
    }
  }

  upload(): void {
    const dialogRef = this.matDialog.open(UploadEvidenceComponent, {
      data: {
        studentId: this.student.id
      }
    })
    dialogRef.afterClosed().subscribe(result => {
      //this.getStudent();
    });
  }

  add(): void {
    const dialogRef = this.matDialog.open(AddStudentQualificationComponent, {
      data: {
        id: null,
        studentId: this.student.id
      }
    })
    dialogRef.afterClosed().subscribe(result => {
      //IFD: console.log('The dialog was closed');
      //IFD: console.log(result);
      this.getStudent();
    });
  }

  editStudentInterview(student): void {
    console.log(student);
    const dialogRef = this.matDialog.open(AddInterviewInformationComponent, {
      data: {
        id: student.id,
        linkId: student.linkId,
        careerAims: student.careerAims,
        workVoluntaryExperience: student.workVoluntaryExperience,
        hobbiesInterestsOtherAchievements: student.hobbiesInterestsOtherAchievements,
        favoriteSubject: student.favoriteSubject,
        courseworkOrExams: student.courseworkOrExams,
        reasonsForReigateCollege: student.reasonsForReigateCollege,
        otherSchoolCollegeAppliedFor: student.otherSchoolCollegeAppliedFor,
        learningSupportNeedsDetails: student.learningSupportNeedsDetails

      }
    })
    dialogRef.afterClosed().subscribe(result => {
      //IFD: console.log('The dialog was closed');
      //IFD: console.log(result);
      this.getStudent();
    });
  }

  edit(qualification): void {
    //IFD:
    console.log(qualification);
    const dialogRef = this.matDialog.open(EditStudentQualificationComponent, {
      data: {
        id: qualification.id,
        studentId: this.student.id,
        qualificationId: qualification.qualification.id,
        grade: qualification.grade,
        examBoardId: qualification.examBoard != null ? qualification.examBoard.id : null
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      //IFD: console.log('The dialog was closed');
      //IFD: console.log(result);
      this.getStudent();
    });
  }

  delete(qualification): void {
    // //IFD: console.log(qualification);
    // if(confirm("Are you sure to delete?")) {
    //   //IFD: console.log("Delete confirmed.");
    //   this.studentQualificationService.deleteById(qualification.id).subscribe(() => this.getStudent());
    // }
    var ref = this;
    bootbox.confirm("Are you sure to delete?", function(result) {
      if (result === true) {
        ref.studentQualificationService.deleteById(qualification.id).subscribe(() => ref.getStudent());
      }
    })
  }


}
