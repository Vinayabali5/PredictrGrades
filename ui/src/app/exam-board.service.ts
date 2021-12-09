import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { AppConfigService } from './app-config.service';

import { BasicApiService } from './basic-api.service';
import { MessageService } from './message.service';

import { ExamBoard } from './model/exam-board';

@Injectable({
  providedIn: 'root'
})
export class ExamBoardService extends BasicApiService {

  private url = '/exam-boards';

  constructor(
    protected config: AppConfigService,
    protected http: HttpClient,
    protected messageService: MessageService
  ) {
    super(http, messageService);
    this.url = config.getConfig().apiUrl + '/exam-boards';
  }

  getExamBoards(): Observable<ExamBoard[]> {
    return this.http.get<ExamBoard[]>(this.url)
      .pipe(
        tap(_ => this.log('fetched exam boards')),
        catchError(this.handleError<ExamBoard[]>('getExamBoards', []))
      );
  }
}
