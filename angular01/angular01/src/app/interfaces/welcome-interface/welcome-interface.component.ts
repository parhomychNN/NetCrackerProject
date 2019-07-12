import {Component, NgModule, OnInit} from '@angular/core';
import {NgForm, FormsModule} from "@angular/forms";
import {BrowserModule} from "@angular/platform-browser";
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth.service";

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
  private message: string;

  constructor(private router: Router, public authService: AuthService) {
    this.message = '';
  }

  ngOnInit() {
  }

  login(username: string, password: string): boolean {
    this.message = '';
    if (!this.authService.login(username, password)) {
      this.message = 'Неверные данные для входа. Проверьте логин или пароль';
      setTimeout(function () {
        this.message = '';
      }.bind(this), 2500);
    }
    return false;
  }

  logout(): boolean {
    this.authService.logout();
    return false;
  }

  enterTheProfile() {
    switch (this.authService.getUser()) {
      case 'admin':
        this.router.navigate([('/admin')])
          .then(success => console.log('navigation success?', success))
          .catch(console.error);
        break;
      case 'student':
        this.router.navigate([('/student')])
          .then(success => console.log('navigation success?', success))
          .catch(console.error);
        break;
      case 'teacher':
        this.router.navigate([('/teacher')])
          .then(success => console.log('navigation success?', success))
          .catch(console.error);
        break;
    }
  }
}
