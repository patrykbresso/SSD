import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Conference} from "../conference";
import {Schedule} from "../schedule";
import {ScheduleItem} from "../schedule-item";
import {formatDate} from "ngx-bootstrap";
import {Author} from "../author";

@Component({
  selector: 'app-conference-details',
  templateUrl: './conference-details.component.html',
  styleUrls: ['./conference-details.component.css']
})
export class ConferenceDetailsComponent implements OnInit {

  data: any;
  conference: Conference;
  scheduleItems: ScheduleItem[];

  constructor(
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.data = this.route.snapshot.data;
    this.conference = this.data.details;
    this.scheduleItems = this.conference.schedule.presentationsAndPosters;
  }

  headElements = ['Presentation Title', 'Author', 'Start Date', 'Start Time', 'Duration', 'Location'];

  getAuthors(scheduleItem: ScheduleItem): string {
    let authors: Author[] = [];
    if (scheduleItem.presentation !== null && scheduleItem.presentation !== undefined) {
      scheduleItem.presentation.authors.forEach(a => authors.push(a));
    }
    if (scheduleItem.posters !== null && scheduleItem.posters !== undefined) {
      scheduleItem.posters.forEach(p => p.authors.forEach(a => authors.push(a)));
    }
    let authorsString = '';
    authors.forEach(a => authorsString += (a.academicTitle + ' ' + a.name + ' ' + a.surname + ', '));
    return authorsString.slice(0, authorsString.length - 2);
  }

  getDuration(scheduleItem: ScheduleItem) {
    let start = Date.parse(scheduleItem.startDate);
    let end = Date.parse(scheduleItem.endDate);
    let diff = Math.floor(((end - start) % 86400000) / 3600000);
    return diff.toString() + ' h';
  }
}
