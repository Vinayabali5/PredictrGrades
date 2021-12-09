import { QualificationType } from './qualification-type';

export interface Qualification {
  id: number,
  qualificationTypeId: number,
  type: QualificationType,
  title: string,
  _fullTitle: string
}
