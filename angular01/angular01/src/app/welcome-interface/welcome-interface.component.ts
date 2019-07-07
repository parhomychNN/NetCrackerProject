import {Component, NgModule, OnInit} from '@angular/core';
import {NgForm, FormsModule} from "@angular/forms";
import {BrowserModule} from "@angular/platform-browser";
import {Router} from "@angular/router";
import {StudentService} from "../services/student.service";
import {Student} from "../models/Student";

@NgModule({
  imports: [
    BrowserModule,
    FormsModule
  ]
})


@Component({
  selector: 'app-welcome-interface',
  templateUrl: './welcome-interface.component.html',
  styleUrls: ['./welcome-interface.component.css'],

})
export class WelcomeInterfaceComponent implements OnInit {

  login: string;
  password: string;

  constructor(private router: Router, private studentService: StudentService) {
  }

  ngOnInit() {
  }

  signInButtonPressed(form: NgForm) {
    this.login = form.value.login;
    this.password = form.value.password;

    switch (this.login) {
      case "student":
        if (this.password == "student") {
          // this.studentService.getStudentByIDd(1);
          this.router.navigate([('/student')])
            .then(success => console.log('navigation success?' , success))
            .catch(console.error);
        }
        break;
      case "teacher":
        if (this.password == "teacher") {
          this.router.navigate([('/teacher')])
            .then(success => console.log('navigation success?' , success))
            .catch(console.error);
        }
        break;
      case "admin":
        if (this.password == "admin") {
          this.router.navigate([('/admin')])
            .then(success => console.log('navigation success?' , success))
            .catch(console.error);
        }
        break;
      default:
        console.log("Invalid login");
        break;
    }


  }
}
