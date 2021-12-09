import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddInterviewInformationComponent } from './add-interview-information.component';

describe('AddStudentInterviewComponent', () => {
  let component: AddInterviewInformationComponent;
  let fixture: ComponentFixture<AddInterviewInformationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddInterviewInformationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddInterviewInformationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
