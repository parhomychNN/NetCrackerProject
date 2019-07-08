import {Injectable} from "@angular/core";

@Injectable()
export class DateUtil{

  formatDate(iDate: Date) {
    let inputDate : Date = new Date(iDate);
    let formattedDate = new Date(inputDate.getFullYear()+'-'+(inputDate.getMonth() + 1)+'-'+
      inputDate.getDate());
    return formattedDate;
  }

  constructor(){};
}
