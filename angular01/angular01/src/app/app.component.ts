import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {forEach} from "@angular/router/src/utils/collection";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Учебный центр';


  constructor(private http: HttpClient) {

  }

  ngOnInit(): void {
  }

}
