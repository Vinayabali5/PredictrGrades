import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-student-details',
  templateUrl: './student-details.component.html',
  styleUrls: ['./student-details.component.css']
})
export class StudentDetailsComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  @Input() student = {
    surname: 'Horgan',
    firstName: 'Michael',
    careerAims: 'Test',
    workVoluntaryExperience : 'test work',
    hobbiesInterestsOtherAchievements: 'test hobbies',
    favoriteSubject: 'test fav subject',
    courseworkOrExams: 'test course work',
    reasonsForReigateCollege: 'test reason for reigate college'
  }

}
