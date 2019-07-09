import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable, pipe} from "rxjs";
import {catchError} from "rxjs/operators";
import {ConfigService} from "../config/config.service";

@Injectable()
export class StudentService{

  private urlToAddStudent: string = 'http://localhost:8089/students/add';
  private urlForRequestStudent = 'http://localhost:8089/student/';
  private urlToGetAllStudents = 'http://localhost:8089/students';
  private urlToUpdateStudent = 'http://localhost:8089/student/';

  constructor(private http: HttpClient){};

  getStudentByIDd(id: string) : Observable<Student>{
    // get student
    return this.http.get<Student>(this.urlForRequestStudent + id.toString());
  }

  /** POST: add a new student to the database */
  addStudent (student: Student): Observable<Student> {
    return this.http.post<Student>(this.urlToAddStudent, student, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    });
  }

  /** PUT: update student in the database */
  updateStudent (student: Student): Observable<Student> {
    return this.http.put<Student>(this.urlToUpdateStudent, student, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    });
  }

  getAllStudents() {
    return this.http.get<Student[]>(this.urlToGetAllStudents);
  }
}




/*this.http.post<Student>(this.urlToAddStudent, this.studentAdded, {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
});*/
