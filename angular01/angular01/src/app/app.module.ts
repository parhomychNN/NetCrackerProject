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
import { TeachAddNewComponent } from './crudOperationsInterfaces/teachers/teach-add-new/teach-add-new.component';
import { TeachDeleteOneComponent } from './crudOperationsInterfaces/teachers/teach-delete-one/teach-delete-one.component';
import { TeachEditOneComponent } from './crudOperationsInterfaces/teachers/teach-edit-one/teach-edit-one.component';
import { TeachFindAllComponent } from './crudOperationsInterfaces/teachers/teach-find-all/teach-find-all.component';
import { TeachFindByIdComponent } from './crudOperationsInterfaces/teachers/teach-find-by-id/teach-find-by-id.component';
import {TeacherService} from "./services/teacher.service";
import { LessonAddNewComponent } from './crudOperationsInterfaces/lessons/lesson-add-new/lesson-add-new.component';
import { LessonDeleteOneComponent } from './crudOperationsInterfaces/lessons/lesson-delete-one/lesson-delete-one.component';
import { LessonEditOneComponent } from './crudOperationsInterfaces/lessons/lesson-edit-one/lesson-edit-one.component';
import { LessonFindAllComponent } from './crudOperationsInterfaces/lessons/lesson-find-all/lesson-find-all.component';
import { LessonFindByIdComponent } from './crudOperationsInterfaces/lessons/lesson-find-by-id/lesson-find-by-id.component';
import {LessonService} from "./services/lesson.service";
import {AuthService} from "./services/auth.service";
import {AUTH_PROVIDERS} from "./services/auth.service";
import {LoggedInGuard} from "./guards/logged-in.guard";
import {AdminService} from "./services/admin.service";


registerLocaleData(localeRu, 'ru');

// paths
const appRoutes: Routes = [
  {path: '', component: WelcomeInterfaceComponent},
  {
    path: 'student',
    component: StudentInterfaceComponent,
    canActivate: [LoggedInGuard]
  },
  {
    path: 'teacher',
    component: TeacherInterfaceComponent,
    canActivate: [LoggedInGuard]
  },
  {
    path: 'admin',
    component: AdminInterfaceComponent,
    canActivate: [LoggedInGuard]
  }
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
    TeachAddNewComponent,
    TeachDeleteOneComponent,
    TeachEditOneComponent,
    TeachFindAllComponent,
    TeachFindByIdComponent,
    LessonAddNewComponent,
    LessonDeleteOneComponent,
    LessonEditOneComponent,
    LessonFindAllComponent,
    LessonFindByIdComponent
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
    StudentService,
    TeacherService,
    LessonService,
    AdminService,
    AuthService,
    AUTH_PROVIDERS,
    LoggedInGuard
  ],
  bootstrap: [AppComponent],
  exports: [RouterModule]
})
export class AppModule { }
