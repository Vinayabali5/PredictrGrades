import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { AppConfigService } from './app-config.service';

import { MessageService } from './message.service';
import { BasicApiService } from './basic-api.service';
import { Student } from './model/student';
import { StudentQualification } from './model/student-qualification';

import { StudentQualificationFormData } from './model/forms/student-qualification-form-data';

@Injectable({
  providedIn: 'root'
})
export class StudentQualificationService extends BasicApiService {

  private url = '/student-qualifications';

  constructor(
    protected config: AppConfigService,
    protected http: HttpClient,
    protected messageService: MessageService
  ) {
    super(http, messageService);
    this.url = config.getConfig().apiUrl + '/student-qualifications';
}

  create(studentQualficaion: StudentQualificationFormData): Observable<StudentQualificationFormData> {
    console.log('Attempting Create');
    const url = `${this.url}`;
    return this.http.post<StudentQualificationFormData>(url, studentQualficaion, this.httpOptions).pipe(
      tap(_ => this.log(`creating student qualification: ${studentQualficaion}`)),
      catchError(this.handleError<StudentQualificationFormData>(`create studentQualficaion=${studentQualficaion}`))
    );
  }

  update(studentQualficaion: StudentQualificationFormData): Observable<StudentQualificationFormData> {
    console.log('Attempting Update');
    const url = `${this.url}`;
    return this.http.put<StudentQualificationFormData>(url, studentQualficaion, this.httpOptions).pipe(
      tap(_ => this.log(`updating student qualification: ${studentQualficaion}`)),
      catchError(this.handleError<StudentQualificationFormData>(`update studentQualficaion=${studentQualficaion}`))
    );
  }

  deleteById(id): Observable<StudentQualification> {
    console.log('Attempting Delete');
    const url = `${this.url}/${id}`;
    return this.http.delete<StudentQualification>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted student qualification with id=${id}`)),
      catchError(this.handleError<StudentQualification>(`deleteById id=${id}`))
    );
  }

}
