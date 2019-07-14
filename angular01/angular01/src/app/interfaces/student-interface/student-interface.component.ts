import {Component, Input, OnInit, Output} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {StudentService} from "../../services/student.service";
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {LessonService} from "../../services/lesson.service";

@Component({
  selector: 'app-student-interface',
  templateUrl: './student-interface.component.html',
  styleUrls: ['./student-interface.component.css']
})
export class StudentInterfaceComponent implements OnInit {

  urlForRequestLessons = 'http://localhost:8089/lessons/student/1';
  lessonsJSON: Lesson[] = [];
  urlForRequestStudent = 'http://localhost:8089/student/1';
  studentJSON: Student;
  showModal: boolean;
  private isSignUpForTheLessonFormActive: boolean = false;

  constructor(
    private http: HttpClient,
    private studentService: StudentService,
    private lessonService: LessonService,
    private authService: AuthService,
    private router: Router
  ) {
  }

  // TODO на welcome при нажатии на sign in делать запрос в базу
  ngOnInit(): void {
    // get lessons
    this.updateLessonsList();
    // get student
    this.http.get<Student>(this.urlForRequestStudent).subscribe(
      data => {
        console.log(data);
        this.studentJSON = data;
      }
    );
  }

  showEditDialog(event) {
    this.showModal = true;
  }

  hideEditDialog() {
    this.showModal = false;
  }

  editOkButtonPressed(lastName: string,
                      firstName: string,
                      birthDate: Date) {
    /*this.studentJSON.firstName = firstName.value;
    this.studentJSON.lastName = lastName.value;
    let dateUtil: DateUtil = new DateUtil();
    this.studentJSON.date = dateUtil.formatDate(birthDate.valueAsDate);*/
    this.studentJSON = {
      studentId: this.studentJSON.studentId,
      firstName: firstName,
      lastName: lastName,
      date: birthDate
    };
    this.studentService.updateStudent(this.studentJSON).subscribe(value => {
      this.studentJSON = value;
    });
    console.log(this.studentJSON);
    this.hideEditDialog();
  }

  showSignUpForTheLessonForm() {
    this.isSignUpForTheLessonFormActive = true;
  }

  updateLessonsList() {
    // get lessons
    this.http.get<Lesson[]>(this.urlForRequestLessons).subscribe(
      data => {
        console.log(data);
        for (let i = 0; i < data.length; i++) {
          this.lessonsJSON[i] = data[i];
        }
      }
    );
  }
}
