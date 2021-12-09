import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormBuilder, Validators, FormGroup, FormControl } from '@angular/forms';

import { QualificationType } from '../model/qualification-type';

import { QualificationTypeService } from '../qualification-type.service';

@Component({
  selector: 'app-qualification-type-selection',
  templateUrl: './qualification-type-selection.component.html',
  styleUrls: ['./qualification-type-selection.component.css'],
  providers: [QualificationTypeService]
})
export class QualificationTypeSelectionComponent implements OnInit {

  errorMessage: string;

  public qualificationTypes: QualificationType[];

  qualificationTypeIdValue: number;
  @Output()
  qualificationTypeIdChange = new EventEmitter<number>();

  constructor(private qualificationTypeService: QualificationTypeService) {
  }

  ngOnInit(): void {
    this.getQualificationTypes()
  }

  getQualificationTypes() {
    this.qualificationTypeService.getQualificationTypes().subscribe(
      res => this.qualificationTypes = res,
      error => this.errorMessage = <any>error
    );
  }

  @Input()
  get qualificationTypeId() {
    return this.qualificationTypeIdValue;
  }

  set qualificationTypeId(val) {
    this.qualificationTypeIdValue = val;
    this.qualificationTypeIdChange.emit(this.qualificationTypeIdValue);
  }
}
