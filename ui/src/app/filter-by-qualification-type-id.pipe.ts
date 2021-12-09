import { Pipe, PipeTransform } from '@angular/core';

import { Qualification } from './model/qualification';

@Pipe({
  name: 'filterByQualificationTypeId'
})
export class FilterByQualificationTypeIdPipe implements PipeTransform {

  transform(items: Qualification[], filterId: number): any[] {
    console.log(`Filtering by qualification type id: ${filterId}`);
    if(!items) return [];
    if(!filterId || filterId == 0) return items;
    return items.filter(it => it.qualificationTypeId == filterId);
  }

}
