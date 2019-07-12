import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable()
export class AdminService {

  private urlToAddAdmin: string = 'http://localhost:8089/admin/add';
  private urlForRequestAdmin = 'http://localhost:8089/admin/';
  private urlToGetAllAdmins = 'http://localhost:8089/admins';
  private urlToUpdateAdmin = 'http://localhost:8089/admin/';
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': "*"
    })
  };

  constructor(private http: HttpClient) {
  };

  getAdminByIDd(id: string) : Observable<Admin>{
    // get teacher
    return this.http.get<Admin>(this.urlForRequestAdmin + id.toString());
  }

  /** POST: add a new teacher to the database */
  addAdmin (admin: Admin): Observable<Admin> {
    return this.http.post<Admin>(this.urlToAddAdmin, admin, this.httpOptions);
  }

  /** PUT: update teacher in the database */
  updateAdmin (admin: Admin): Observable<Admin> {
    return this.http.put<Admin>(this.urlToUpdateAdmin, admin, this.httpOptions);
  }

  /** DELETE: delete teacher from the server */
  deleteAdmin (id: number): Observable<Boolean> {
    const url = `${this.urlForRequestAdmin}${id}`;
    console.log(url);
    return this.http.delete<Boolean>(url, this.httpOptions);
  }

  getAllAdmins() {
    return this.http.get<Admin[]>(this.urlToGetAllAdmins);
  }

}
