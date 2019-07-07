import {Student} from "../models/Student";
import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";

@Injectable()
export class StudentService{

  urlForRequestStudent = 'http://localhost:8089/student/';
  public studentLoaded: Student;

  constructor(private http: HttpClient){};

  getStudentByIDd(id: number) : Student{
    // get student
    this.http.get<Student>(this.urlForRequestStudent + id).subscribe(
      data => {
        this.studentLoaded = data;
      }
    );

    console.log(this.studentLoaded + " <========== studentLoaded in service");
    return this.studentLoaded;
  }

}
