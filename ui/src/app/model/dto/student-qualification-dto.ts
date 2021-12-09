import { StudentQualificationFormData } from '../forms/student-qualification-form-data';

export class StudentQualificationDTO {
  public id: number;
  public studentId: number;
  public qualificationId: number;
  public grade: string;
  public examBoardId: number;
  public mockGrade: string;
  
  constructor(data: StudentQualificationFormData) {
    this.id = data.id;
    this.studentId = data.studentId;
    this.qualificationId = data.qualificationId;
    this.grade = data.grade;
    this.examBoardId = data.examBoardId;
    this.mockGrade = data.mockGrade;
  }

}
