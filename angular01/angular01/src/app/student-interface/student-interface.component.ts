import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-student-interface',
  templateUrl: './student-interface.component.html',
  styleUrls: ['./student-interface.component.css']
})
export class StudentInterfaceComponent implements OnInit {

  urlForRequestLessons = 'http://localhost:8089/lessons/student/1';
  lessonsJSON : Lesson[] = [];
  urlForRequestStudent = 'http://localhost:8089/student/1';
  studentJSON : Student = null;


  constructor(private http: HttpClient) { }

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
  }

  showModal : boolean;

  onClick(event) {
    this.showModal = true;
  }

  hide(){
    this.showModal = false;
  }

  // TODO изменять студента напрямую в studentJSON, подумать над верификацией пустых полей и пробелов
  editButtonPressed(lastName: HTMLInputElement,
                    firstName: HTMLInputElement,
                    birthDate: HTMLInputElement) {

    let studentChanged = new Student(
      this.studentJSON.studentId,
      firstName.value,
      lastName.value,
      birthDate.valueAsDate

    );

    console.log(studentChanged);
    this.hide();
  }
}
