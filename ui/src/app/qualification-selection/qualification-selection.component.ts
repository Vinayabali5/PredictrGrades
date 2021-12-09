import { Component, OnInit, OnChanges, Input, Output, EventEmitter, forwardRef, HostBinding } from '@angular/core';
import { FormGroup, ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';

import { Qualification } from '../model/qualification';
import { QualificationType } from '../model/qualification-type';

import { QualificationService } from '../qualification.service';

@Component({
  selector: 'app-qualification-selection',
  templateUrl: './qualification-selection.component.html',
  styleUrls: ['./qualification-selection.component.css'],
  providers: [{
    provide: NG_VALUE_ACCESSOR,
    useExisting: forwardRef(() => QualificationSelectionComponent),
    multi: true
  }]

})
export class QualificationSelectionComponent implements OnInit, OnChanges, ControlValueAccessor {

  @Input() parentForm: FormGroup;
  @Input() formControlName: string;

  onChange: any = () => {};
  onTouched: any = () => {};

  public qualifications: Qualification[];
  private allQualifications: Qualification[];

  @Input() id: string;
  @Input() name: string;
  _qualificationTypeId: number;

  _qualificationId: number = null;
  @Output() qualificationIdChange = new EventEmitter<number>();

  private previousTypeId;

  constructor(private qualificationService: QualificationService) {
  }

  ngOnInit(): void {
    this.getQualifications()
  }

  ngOnChanges() : void {
    
  }

  // === Implementing the ControlValueAccessor interface
  writeValue(value) {
    if (value) {
      this._qualificationId = value;
    }
  }

  registerOnChange(fn) { this.onChange = fn;  }

  registerOnTouched(fn) { this.onTouched = fn; }
  // === End ControlValueAccessor interface

  getQualifications() {
    this.qualificationService.getQualifications().subscribe(
      res => {
        this.allQualifications = res;
        this.qualifications = res;
      },
      //error => this.errorMessage = <any>error
    );
  }

  // === Getters and Setters

  @Input()
  get qualificationId(): number {
    return this._qualificationId;
  }

  set qualificationId(val: number) {
    this._qualificationId = val;
    this.qualificationIdChange.emit(this._qualificationId);
    this.onChange(val);
    this.onTouched();
  }

  @Input()
  get qualificationTypeId(): number {
    return this._qualificationTypeId;
  }

  set qualificationTypeId(val: number) {
    if (this._qualificationTypeId !== 0) {
      this.previousTypeId = this._qualificationTypeId;
      if (this._qualificationTypeId !== val) {
        this.qualificationId = null;
      }
    }
    this._qualificationTypeId = val;
  }

}
