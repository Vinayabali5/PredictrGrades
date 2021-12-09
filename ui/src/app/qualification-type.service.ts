import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { AppConfigService } from './app-config.service';

import { BasicApiService } from './basic-api.service';
import { MessageService } from './message.service';

import { QualificationType } from './model/qualification-type';

@Injectable({
  providedIn: 'root'
})
export class QualificationTypeService extends BasicApiService {

  private url = '/qualification-types';

  constructor(
    protected config: AppConfigService,
    protected http: HttpClient,
    protected messageService: MessageService
  ) {
    super(http, messageService);
    this.url = config.getConfig().apiUrl + '/qualification-types';
  }

  getQualificationTypes(): Observable<QualificationType[]> {
    return this.http.get<QualificationType[]>(this.url)
      .pipe(
        tap(_ => this.log('fetched qualification types')),
        catchError(this.handleError<QualificationType[]>('getQualificationTypes', []))
      );
  }

}
