import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditStudentQualificationComponent } from './edit-student-qualification.component';

describe('EditStudentQualificationComponent', () => {
  let component: EditStudentQualificationComponent;
  let fixture: ComponentFixture<EditStudentQualificationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditStudentQualificationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditStudentQualificationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
