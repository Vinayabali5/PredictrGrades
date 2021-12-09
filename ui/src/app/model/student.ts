import { StudentQualification } from './student-qualification';

export interface Student {
  id: number,
  surname: string,
  firstName: string,
  dob: Date,
  linkId: string,
  qualifications: StudentQualification[],
  careerAims: string,
  workVoluntaryExperience: string,
  hobbiesInterestsOtherAchievements: string,
  favoriteSubject: string,
  courseworkOrExams: string,
  reasonsForReigateCollege: string,
  otherSchoolCollegeAppliedFor:string,
  learningSupportNeedsDetails: string
}
