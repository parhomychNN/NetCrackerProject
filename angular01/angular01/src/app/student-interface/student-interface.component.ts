import {Component, Input, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Student} from "../models/Student";
import {StudentService} from "../services/student.service";

@Component({
  selector: 'app-student-interface',
  templateUrl: './student-interface.component.html',
  styleUrls: ['./student-interface.component.css'],

})
export class StudentInterfaceComponent implements OnInit {

  urlForRequestLessons = 'http://localhost:8089/lessons/student/1';
  lessonsJSON : Lesson[] = [];
  urlForRequestStudent = 'http://localhost:8089/student/1';
  studentJSON : Student;

  constructor(
    private http: HttpClient,
    private studentService: StudentService
  ) {}

  // TODO на welcome при нажатии на sign in делать запрос в базу
  ngOnInit(): void {
    // get lessons
    this.http.get<Lesson[]>(this.urlForRequestLessons).subscribe(
      data => {
        console.log(data);
        for (let i = 0; i < data.length; i++) {
          this.lessonsJSON[i] = data[i];
        }
      }
    );

        // get student
    this.http.get<Student>(this.urlForRequestStudent).subscribe(
      data => {
        console.log(data);
        this.studentJSON = data;
      }
    );

    //this.studentJSON = this.studentService.studentLoaded;

  }

  showModal : boolean;
  private st: Student;

  showEditDialog(event) {
    this.showModal = true;
  }

  hideEditDialog(){
    this.showModal = false;
  }

  // TODO изменять студента напрямую в studentJSON, подумать над верификацией пустых полей и пробелов
  editOkButtonPressed(lastName: HTMLInputElement,
                      firstName: HTMLInputElement,
                      birthDate: HTMLInputElement) {

    this.studentJSON.firstName = firstName.value;
    this.studentJSON.lastName = lastName.value;
    this.studentJSON.date = birthDate.valueAsDate;



    console.log(this.studentJSON);
    this.hideEditDialog();

  }
}
