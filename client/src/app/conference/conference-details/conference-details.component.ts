import { AuthStorageService } from './../../auth/service/auth-storage.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { Conference } from "../conference";
import { Schedule } from "../schedule";
import { ScheduleItem } from "../schedule-item";
import { formatDate } from "ngx-bootstrap";
import { Author } from "../author";
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-conference-details',
  templateUrl: './conference-details.component.html',
  styleUrls: ['./conference-details.component.css']
})
export class ConferenceDetailsComponent implements OnInit {

  private data: any;
  private conference: Conference;
  private scheduleItems: ScheduleItem[];

  private loggedAsConferenceOrganiser: boolean;
  private isUserAssignToConference: boolean;

  constructor(
    private readonly route: ActivatedRoute,
    private readonly authStorageService: AuthStorageService,
    private readonly toastr: ToastrService,
    private readonly router: Router
  ) { }

  ngOnInit() {
    this.data = this.route.snapshot.data;
    this.conference = this.data.details;
    this.scheduleItems = this.conference.schedule.presentationsAndPosters;

    this.initDisabledOptions();
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

  private onCreateConferenceClick() {
    this.router.navigate(['/conference-create']);
  }

  private onManageScheduleClick() {
    this.router.navigate(['/conference/' + this.conference.id + '/schedule']);
  }

  private onCancelConferenceClick() {
    this.toastr.warning('This functionality is not implemented yet. We are working on that.');
  }

  private onAddPosterClick() {
    this.router.navigate(['/conference/' + this.conference.id + '/add-poster']);
  }

  private onAddPresentationClick() {
    this.router.navigate(['/conference/' + this.conference.id + '/add-presentation']);
  }

  private onPlaceReservationClick() {
    this.toastr.warning('This functionality is not implemented yet. We are working on that.');
  }

  private onPlaceCancellationClick() {
    this.toastr.warning('This functionality is not implemented yet. We are working on that.');
  }

  private onRoomReservationClick() {
    this.toastr.warning('This functionality is not implemented yet. We are working on that.');
  }

  private onRoomCancellationClick() {
    this.toastr.warning('This functionality is not implemented yet. We are working on that.');
  }

  private initDisabledOptions() {
    this.loggedAsConferenceOrganiser = this.isCurrentOrganiserLoogedIn();
  }

  private isCurrentOrganiserLoogedIn(): boolean {
    let currentEmail = this.authStorageService.getUserEmail();
    let emails = this.conference.organisers.map(organiser => organiser.email);

    for (const email of emails) {
      if (email === currentEmail) {
        return true;
      }
    }

    return false;
  }
}
