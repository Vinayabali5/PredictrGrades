import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { AppConfigService } from './app-config.service';

import { MessageService } from './message.service';
import { BasicApiService } from './basic-api.service';
import { Student } from './model/student';
import { StudentInterviewInformation } from './model/student-interview-information';

@Injectable({
  providedIn: 'root'
})
export class StudentService extends BasicApiService {

  private url = '/students';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:8080'
   })
  };

  constructor(
    protected config: AppConfigService,
    protected http: HttpClient,
    protected messageService: MessageService
  ) {
    super(http, messageService);
    this.url = config.getConfig().apiUrl + '/students';
  }

  /** GET student by LinkId. Will 404 if id not found */
  getByLinkId(linkId: string): Observable<Student> {
    const url = `${this.url}?LinkId=${linkId}`;
    return this.http.get<Student>(url).pipe(
      tap(_ => this.log(`fetched student with LinkId=${linkId}`)),
      catchError(this.handleError<Student>(`getByLinkId linkId=${linkId}`))
    );
  }

  /**
   * This update method is used to update the interview fields. 
   */
  update(studentInterview: StudentInterviewInformation): Observable<StudentInterviewInformation> {
    console.log('Attempting Update');
    const url = `${this.url}/${studentInterview.id}/pre-interview`;
    return this.http.post<StudentInterviewInformation>(url, studentInterview, this.httpOptions).pipe(
      tap(_ => this.log(`updating student interview Information: ${studentInterview}`)),
      catchError(this.handleError<StudentInterviewInformation>(`update studentInterviewInformation=${studentInterview}`))
    );
  }

}
