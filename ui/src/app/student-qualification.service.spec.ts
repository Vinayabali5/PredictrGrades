import { TestBed } from '@angular/core/testing';

import { StudentQualificationService } from './student-qualification.service';

describe('StudentQualificationService', () => {
  let service: StudentQualificationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StudentQualificationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
