import { TestBed } from '@angular/core/testing';

import { ExamBoardService } from './exam-board.service';

describe('ExamBoardService', () => {
  let service: ExamBoardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ExamBoardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
