import { StudentInterviewInformation } from "../student-interview-information";


export class StudentInterviewInformationDTO {
  public id: number;
  public linkId: string;
  public careerAims: string;
  public workVoluntaryExperience: string;
  public hobbiesInterestsOtherAchievements: string;
  public favoriteSubject: string;
  public courseworkOrExams: string;
  public reasonsForReigateCollege: string;
  public otherSchoolCollegeAppliedFor:string;
  public learningSupportNeedsDetails:string;
  
  constructor(data: StudentInterviewInformation) {
    this.id = data.id;
    this.linkId = data.linkId;
    this.careerAims = data.careerAims;
    this.workVoluntaryExperience = data.workVoluntaryExperience;
    this.hobbiesInterestsOtherAchievements = data.hobbiesInterestsOtherAchievements;
    this.favoriteSubject = data.favoriteSubject;
    this.courseworkOrExams = data.courseworkOrExams;
    this.reasonsForReigateCollege = data.reasonsForReigateCollege;
    this.otherSchoolCollegeAppliedFor = data.otherSchoolCollegeAppliedFor;
    this.learningSupportNeedsDetails = data.learningSupportNeedsDetails;
  }

}