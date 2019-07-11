import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-admin-interface',
  templateUrl: './admin-interface.component.html',
  styleUrls: ['./admin-interface.component.css']
})
export class AdminInterfaceComponent implements OnInit {

  urlForRequestAdmin = 'http://localhost:8089/admin/8';
  adminJSON : Admin;
  showModal : boolean;

  /*workspace - show activity that admin does
  for student: stAdd, stEdit, stFind, stFindAll, stDelete;
  'start' - to show nothing*/
  private workspaceStudent: string = 'start';
  private workspaceTeacher: string = 'start';
  private workspaceLesson: string = 'start';

  constructor(private http: HttpClient) { }

  ngOnInit() {

    this.http.get<Admin>(this.urlForRequestAdmin).subscribe(
      admin => {
        console.log(admin);
        this.adminJSON = admin;
      }
    )

  }

  showEditDialog(event) {
    this.showModal = true;
  }

  hideEditDialog() {
    this.showModal = false;
  }

  editOkButtonPressed(lastName: HTMLInputElement,
                      firstName: HTMLInputElement,
                      position: HTMLInputElement) {

    this.adminJSON.firstName = firstName.value.toString();
    this.adminJSON.lastName = lastName.value.toString();
    this.adminJSON.position = position.value.toString();

    console.log(this.adminJSON);

    this.hideEditDialog();

  }

  // functions for students information
  showStAdd() {
    this.workspaceStudent = 'stAdd';
  }

  showStFind() {
    this.workspaceStudent = 'stFind';
  }

  showStFindAll() {
    this.workspaceStudent = 'stFindAll';
  }

  showStEdit() {
    this.workspaceStudent = 'stEdit';
  }

  showStDelete() {
    this.workspaceStudent = 'stDelete';
  }

  showStStart() {
    this.workspaceStudent = 'start';
  }

  // functions for teachers information
  showTeachAdd() {
    this.workspaceTeacher = 'teachAdd';
  }

  showTeachFind() {
    this.workspaceTeacher = 'teachFind';
  }

  showTeachFindAll() {
    this.workspaceTeacher = 'teachFindAll';
  }

  showTeachEdit() {
    this.workspaceTeacher = 'teachEdit';
  }

  showTeachDelete() {
    this.workspaceTeacher = 'teachDelete';
  }

  showTeachStart() {
    this.workspaceTeacher = 'start';
  }

  // functions for lessons information
  showLessonAdd() {
    this.workspaceLesson = 'lessonAdd';
  }

  showLessonFind() {
    this.workspaceLesson = 'lessonFind';
  }

  showLessonFindAll() {
    this.workspaceLesson = 'lessonFindAll';
  }

  showLessonEdit() {
    this.workspaceLesson = 'lessonEdit';
  }

  showLessonDelete() {
    this.workspaceLesson = 'lessonDelete';
  }

  showLessonStart() {
    this.workspaceLesson = 'start';
  }

}
