import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable()
export class TeacherService {

  private urlToAddTeacher: string = 'http://localhost:8089/teachers/add';
  private urlForRequestTeacher = 'http://localhost:8089/teacher/';
  private urlToGetAllTeachers = 'http://localhost:8089/teachers';
  private urlToUpdateTeacher = 'http://localhost:8089/teacher/';
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': "*"
    })
  };

  constructor(private http: HttpClient) {
  };

  getTeacherByIDd(id: string) : Observable<Teacher>{
    // get teacher
    return this.http.get<Teacher>(this.urlForRequestTeacher + id.toString());
  }

  /** POST: add a new teacher to the database */
  addTeacher (teacher: Teacher): Observable<Teacher> {
    return this.http.post<Teacher>(this.urlToAddTeacher, teacher, this.httpOptions);
  }

  /** PUT: update teacher in the database */
  updateTeacher (teacher: Teacher): Observable<Teacher> {
    return this.http.put<Teacher>(this.urlToUpdateTeacher, teacher, this.httpOptions);
  }

  /** DELETE: delete teacher from the server */
  deleteTeacher (id: number): Observable<Boolean> {
    const url = `${this.urlForRequestTeacher}${id}`;
    console.log(url);
    return this.http.delete<Boolean>(url, this.httpOptions);
  }

  getAllTeachers() {
    return this.http.get<Teacher[]>(this.urlToGetAllTeachers);
  }

}
