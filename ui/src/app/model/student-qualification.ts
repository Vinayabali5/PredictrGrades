import { Qualification } from './qualification';
import { ExamBoard } from './exam-board';

export interface StudentQualification {
  id: number,
  qualification: Qualification,
  grade: string,
  examBoard: ExamBoard
}
