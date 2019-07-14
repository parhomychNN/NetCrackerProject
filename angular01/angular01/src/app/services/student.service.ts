import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable, pipe, throwError} from "rxjs";
import {isNull} from "util";
import {catchError} from "rxjs/operators";

@Injectable()
export class StudentService {

  private urlToAddStudent: string = 'http://localhost:8089/students/add';
  private urlForRequestStudent = 'http://localhost:8089/student/';
  private urlToGetAllStudents = 'http://localhost:8089/students';
  private urlToUpdateStudent = 'http://localhost:8089/student/';
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) {
  };

  getStudentByIDd(id: string): Observable<Student> {
    // get student
    return this.http.get<Student>(this.urlForRequestStudent + id.toString());
  }

  /** POST: add a new student to the database */
  addStudent(student: Student): Observable<Student> {
    return this.http.post<Student>(this.urlToAddStudent, student, this.httpOptions);
  }

  /** PUT: update student in the database */
  updateStudent(student: Student): Observable<Student> {
    return this.http.put<Student>(this.urlToUpdateStudent, student, this.httpOptions);
  }

  /** DELETE: delete student from the server */
  deleteStudent(id: number): Observable<Boolean> {
    const url = `${this.urlForRequestStudent}${id}`;
    console.log(url);
    return this.http.delete<Boolean>(url, this.httpOptions);
  }

  getAllStudents() {
    return this.http.get<Student[]>(this.urlToGetAllStudents);
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // return an observable with a user-facing error message
    return throwError(
      'Something bad happened; please try again later.');
  };
}
