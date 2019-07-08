import { BrowserModule } from '@angular/platform-browser';
import {LOCALE_ID, NgModule} from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StudentInterfaceComponent } from './interfaces/student-interface/student-interface.component';
import {Routes, RouterModule} from "@angular/router";
import { TeacherInterfaceComponent } from './interfaces/teacher-interface/teacher-interface.component';
import { WelcomeInterfaceComponent } from './interfaces/welcome-interface/welcome-interface.component';
import { AdminInterfaceComponent } from './interfaces/admin-interface/admin-interface.component';
import {FormsModule} from "@angular/forms";

import { registerLocaleData } from '@angular/common';
import localeRu from '@angular/common/locales/ru';
import {StudentService} from "./services/student.service";
import { StudFindByIdComponent } from './crudOperationsInterfaces/students/stud-find-by-id/stud-find-by-id.component';
import { StudFindAllComponent } from './crudOperationsInterfaces/students/stud-find-all/stud-find-all.component';
import { StudDeleteOneComponent } from './crudOperationsInterfaces/students/stud-delete-one/stud-delete-one.component';
import { StudEditOneComponent } from './crudOperationsInterfaces/students/stud-edit-one/stud-edit-one.component';
import { StudAddNewComponent } from './crudOperationsInterfaces/students/stud-add-new/stud-add-new.component';

registerLocaleData(localeRu, 'ru');

// TODo скинуть код (четверг) и git ignore на большие компоненты
// paths
const appRoutes: Routes = [
  {path: '', component: WelcomeInterfaceComponent},
  // TODO решение об id прнимает кнопка Sign in, не нужно протаскивать в адресную строку
  {path: 'student', component: StudentInterfaceComponent},
  {path: 'teacher', component: TeacherInterfaceComponent},
  {path: 'admin', component: AdminInterfaceComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    StudentInterfaceComponent,
    TeacherInterfaceComponent,
    WelcomeInterfaceComponent,
    AdminInterfaceComponent,
    StudFindByIdComponent,
    StudFindAllComponent,
    StudDeleteOneComponent,
    StudEditOneComponent,
    StudAddNewComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [
    { provide: LOCALE_ID, useValue: 'ru' },
    StudentService
  ],
  bootstrap: [AppComponent],
  exports: [RouterModule]
})
export class AppModule { }
