import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { StudentComponent } from './student/student.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

const routes: Routes = [
   { path: 'link', component: StudentComponent },
   { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

export const routingComponents = [StudentComponent, PageNotFoundComponent ]
