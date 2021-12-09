import { AbstractControl, ValidatorFn } from '@angular/forms';

export function gradeValidator(control: AbstractControl) {
  let grade = control.value;
  if (grade) {
    const regex = /^([Dd][Ss][\*]{0,1}|[Dd][Ss]|[Mm][Ee]|[Pp][Aa]|[Pp][12]|[A]{1,1}[\*]{1,1}|[1-9A-FUXa-f]{1,1})$/;

    const valid = regex.test(grade);
    console.log(valid);
    if (!valid) {
      return {
        grade: "Grade is incorrectly formatted. Valid entry are: 1-9, A*-F, U, X, DS*, DS, ME, PA, P1, P2."
      }
    }
  }
  return null;
}
