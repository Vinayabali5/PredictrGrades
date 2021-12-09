import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddStudentQualificationComponent } from './add-student-qualification.component';

describe('AddStudentQualificationComponent', () => {
  let component: AddStudentQualificationComponent;
  let fixture: ComponentFixture<AddStudentQualificationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddStudentQualificationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddStudentQualificationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
