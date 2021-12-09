import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QualificationSelectionComponent } from './qualification-selection.component';

describe('QualificationSelectionComponent', () => {
  let component: QualificationSelectionComponent;
  let fixture: ComponentFixture<QualificationSelectionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QualificationSelectionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QualificationSelectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
