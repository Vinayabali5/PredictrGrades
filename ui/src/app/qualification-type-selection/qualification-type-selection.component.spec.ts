import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QualificationTypeSelectionComponent } from './qualification-type-selection.component';

describe('QualificationTypeSelectionComponent', () => {
  let component: QualificationTypeSelectionComponent;
  let fixture: ComponentFixture<QualificationTypeSelectionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QualificationTypeSelectionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QualificationTypeSelectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
