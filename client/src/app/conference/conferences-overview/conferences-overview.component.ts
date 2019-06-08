import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Conference} from "../conference";

@Component({
  selector: 'app-conferences-overview',
  templateUrl: './conferences-overview.component.html',
  styleUrls: ['./conferences-overview.component.css']
})
export class ConferencesOverviewComponent implements OnInit {

  data: any;
  overview: Conference[] = [];
  constructor(
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.data = this.route.snapshot.data;
    this.overview = this.data.overview;
  }

  headElements = ['Conference Name', 'Start Date', 'End Date', 'Location', 'More Information'];

  onMoreInformationClick(id: number) {
    this.router.navigate(['/conference/' + id])
  }
}
