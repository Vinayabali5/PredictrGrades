import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpErrorResponse, HttpEventType } from  '@angular/common/http';
import { map } from  'rxjs/operators';

import { AppConfigService } from './app-config.service';

import { MessageService } from './message.service';
import { BasicApiService } from './basic-api.service';

@Injectable({
  providedIn: 'root'
})
export class UploadService extends BasicApiService {

  private url = '/upload';

  constructor(
    protected config: AppConfigService,
    protected http: HttpClient,
    protected messageService: MessageService
  ) {
    super(http, messageService);
    this.url = config.getConfig().apiUrl + '/upload';
  }

  public upload(formData) {
  	return this.http.post<any>(this.url, formData, {
        reportProgress: true,
        observe: 'events',
        responseType: 'json'
      });
  }

}
