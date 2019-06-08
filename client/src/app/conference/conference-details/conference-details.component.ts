import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {SimpleConference} from "../simple-conference";
import {Conference} from "../conference";

//TODO add all authors
@Component({
  selector: 'app-conference-details',
  templateUrl: './conference-details.component.html',
  styleUrls: ['./conference-details.component.css']
})
export class ConferenceDetailsComponent implements OnInit {

  data: any;
  conference: Conference;

  constructor(
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.data = this.route.snapshot.data;
    this.conference = this.data.details;
  }

  headElements = ['Presentation Title', 'Author', 'Start Date', 'Start Time', 'Duration', 'Location'];

}
