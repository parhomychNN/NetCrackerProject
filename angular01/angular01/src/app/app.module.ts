import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StudentInterfaceComponent } from './student-interface/student-interface.component';
import {Routes, RouterModule} from "@angular/router";
import { TeacherInterfaceComponent } from './teacher-interface/teacher-interface.component';
import { WelcomeInterfaceComponent } from './welcome-interface/welcome-interface.component';
import { AdminInterfaceComponent } from './admin-interface/admin-interface.component';

// TODo скинуть код (четверг) и git ignore на большие компоненты
// paths
const appRoutes: Routes = [
  {path: '', component: WelcomeInterfaceComponent},
  // TODO решение об id прнимает кнопка Sign in, не нужно протаскивать в адресную строку
  {path: 'student/:id', component: StudentInterfaceComponent},
  {path: 'teacher/:id', component: TeacherInterfaceComponent},
  {path: 'admin/:id', component: AdminInterfaceComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    StudentInterfaceComponent,
    TeacherInterfaceComponent,
    WelcomeInterfaceComponent,
    AdminInterfaceComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent],
  exports: [RouterModule]
})
export class AppModule { }
