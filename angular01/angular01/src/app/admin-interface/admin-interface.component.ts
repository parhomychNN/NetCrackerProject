import { Component, OnInit } from '@angular/core';
import {Student} from "../models/Student";

@Component({
  selector: 'app-admin-interface',
  templateUrl: './admin-interface.component.html',
  styleUrls: ['./admin-interface.component.css']
})
export class AdminInterfaceComponent implements OnInit {

  urlForRequestAdmin = 'http://localhost:8089/student/1';
  adminJSON : Student;

  showModal : boolean;

  constructor() { }

  ngOnInit() {
  }

  showEditDialog(event) {
    this.showModal = true;
  }

  hideEditDialog() {
    this.showModal = false;
  }

  editOkButtonPressed(lastName: HTMLInputElement,
                      firstName: HTMLInputElement,
                      birthDate: HTMLInputElement) {


    this.hideEditDialog();

  }

}
