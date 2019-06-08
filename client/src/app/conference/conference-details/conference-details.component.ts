import { Component, OnInit } from '@angular/core';
import {Conference} from "../conference";
import {ActivatedRoute} from "@angular/router";

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
