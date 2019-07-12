import {Injectable} from '@angular/core';
import {Router} from "@angular/router";

@Injectable()
export class AuthService {
  constructor(private router: Router){}

  login(user: string, password: string): boolean {
    if (user === 'admin' && password === 'admin' ||
      user === 'student' && password === 'student' ||
      user === 'teacher' && password === 'teacher') {
      localStorage.setItem('username', user);
      return true;
    }
    return false;
  }

  logout(): any {
    localStorage.removeItem('username');
    console.log(this.getUser());
    this.router.navigate([('')])
      .then(success => console.log('navigation success?', success))
      .catch(console.error);
  }

  getUser(): any {
    return localStorage.getItem('username');
  }

  isLoggedIn(): boolean {
    return this.getUser() !== null;
  }
}

export const AUTH_PROVIDERS: Array<any> = [
  { provide: AuthService, useClass: AuthService }
];
