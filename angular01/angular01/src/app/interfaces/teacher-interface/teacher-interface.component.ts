import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {HttpClient} from "@angular/common/http";
import {StudentService} from "../../services/student.service";
import {Router} from "@angular/router";
import {TeacherService} from "../../services/teacher.service";

@Component({
  selector: 'app-teacher-interface',
  templateUrl: './teacher-interface.component.html',
  styleUrls: ['./teacher-interface.component.css']
})
export class TeacherInterfaceComponent implements OnInit {
  urlForRequestLessons = 'http://localhost:8089/lessons/teacher/4';
  urlForRequestTeacher = 'http://localhost:8089/teacher/4';
  lessonsJSON: Lesson[] = [];
  teacherJSON: Teacher;
  showModal: boolean;

  constructor(
    public authService: AuthService,
    private http: HttpClient,
    private studentService: TeacherService,
    private router: Router
  ) {
  }

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
    this.http.get<Teacher>(this.urlForRequestTeacher).subscribe(
      data => {
        console.log(data);
        this.teacherJSON = data;
      }
    );
  }

  showEditDialog(event) {
    this.showModal = true;
  }

  hideEditDialog() {
    this.showModal = false;
  }

  editOkButtonPressed(lastNameText: string,
                      firstNameText: string,
                      academicDegreeText: string) {
    this.teacherJSON = {
      teacherId: this.teacherJSON.teacherId,
      firstName: firstNameText,
      lastName: lastNameText,
      academicDegree: academicDegreeText
    };
    this.studentService.updateTeacher(this.teacherJSON).subscribe(value => {
      this.teacherJSON = value;
    });
    console.log(this.teacherJSON);
    this.hideEditDialog();
  }
}
