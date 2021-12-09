import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { AppConfigService } from './app-config.service';

import { BasicApiService } from './basic-api.service';
import { MessageService } from './message.service';

import { Qualification } from './model/qualification';

@Injectable({
  providedIn: 'root'
})
export class QualificationService extends BasicApiService {

  private url = '/qualifications';

  constructor(
    protected config: AppConfigService,
    protected http: HttpClient,
    protected messageService: MessageService
  ) {
    super(http, messageService);
    this.url = config.getConfig().apiUrl + '/qualifications';
  }

  getQualifications(): Observable<Qualification[]> {
    return this.http.get<Qualification[]>(this.url)
      .pipe(
        tap(_ => this.log('fetched qualifications')),
        catchError(this.handleError<Qualification[]>('getQualifications', []))
      );
  }

}
