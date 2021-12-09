import { NgModule, APP_INITIALIZER } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule }    from '@angular/common/http';

import { OrderModule } from 'ngx-order-pipe';

import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';

import { AppConfigService } from './app-config.service';

import { StudentDetailsComponent } from './student-details/student-details.component';
import { EditStudentQualificationComponent } from './edit-student-qualification/edit-student-qualification.component';
import { AddStudentQualificationComponent } from './add-student-qualification/add-student-qualification.component';
import { QualificationTypeSelectionComponent } from './qualification-type-selection/qualification-type-selection.component';
import { QualificationSelectionComponent } from './qualification-selection/qualification-selection.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

import { materialModules } from './material';
import { filterComponents } from './filters';
import { UploadEvidenceComponent } from './upload-evidence/upload-evidence.component';
import { AddInterviewInformationComponent } from './add-interview-information/add-interview-information.component';

const appInitializerFn = (appConfig: AppConfigService) => {
  return () => {
    return appConfig.loadAppConfig();
  };
};

@NgModule({
  declarations: [
    AppComponent,
    routingComponents,
    filterComponents,

    StudentDetailsComponent,
    EditStudentQualificationComponent,
    AddStudentQualificationComponent,
    QualificationTypeSelectionComponent,
    QualificationSelectionComponent,
    UploadEvidenceComponent,
    AddInterviewInformationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    OrderModule,
    materialModules
  ],
  providers: [
    AppConfigService,
    {
      provide: APP_INITIALIZER,
      useFactory: appInitializerFn,
      multi: true,
      deps: [AppConfigService]
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {

}
