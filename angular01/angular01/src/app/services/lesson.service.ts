import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {StudentService} from "./student.service";
import {TeacherService} from "./teacher.service";

@Injectable()
export class LessonService {

  private urlToAddLesson: string = 'http://localhost:8089/lessons/add';
  private urlForRequestLesson = 'http://localhost:8089/lesson/';
  private urlToGetAllLessons = 'http://localhost:8089/lessons';
  private urlToUpdateLesson = 'http://localhost:8089/lesson/';
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient,
              private studentService: StudentService,
              private teacherService: TeacherService) {
  };

  /** GET: get one lesson from the database */
  getLessonById(id: string): Observable<Lesson> {
    return this.http.get<Lesson>(this.urlForRequestLesson + id.toString());
  }

  /** POST: add a new lesson to the database */
  addLesson(lesson: Lesson): Observable<Lesson> {
    return this.http.post<Lesson>(this.urlToAddLesson, lesson, this.httpOptions);
  }

  /** PUT: update lesson in the database */
  updateLesson(lesson: Lesson): Observable<Lesson> {
    return this.http.put<Lesson>(this.urlToUpdateLesson, lesson, this.httpOptions);
  }

  /** DELETE: delete lesson from the server */
  deleteLesson(id: number): Observable<Boolean> {
    const url = `${this.urlForRequestLesson}${id}`;
    console.log(url);
    return this.http.delete<Boolean>(url, this.httpOptions);
  }

  /** GET: get all the lessons from the database */
  getAllLessons() {
    return this.http.get<Lesson[]>(this.urlToGetAllLessons);
  }

  createLessonFromInputData(studentId: string,
                            teacherId: string,
                            subject: string,
                            date: Date,
                            price: number): Lesson {
    let teacherForAddingInSubject: Teacher;
    let studentForAddingInSubject: Student;
    let lessonForReturn: Lesson;
    this.teacherService.getTeacherByIDd(teacherId).subscribe(teach => {
      teacherForAddingInSubject = teach;
      this.studentService.getStudentByIDd(studentId).subscribe(stud => {
        studentForAddingInSubject = stud;
        lessonForReturn = {
          id: 1,
          subject: subject,
          teacher: teacherForAddingInSubject,
          student: studentForAddingInSubject,
          date: date,
          price: price
        };
      });
    });
    return lessonForReturn;
  }

}
