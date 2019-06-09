import {Injectable} from "@angular/core";
import {Resolve} from "@angular/router";
import {ConferenceService} from "../conference-service";

@Injectable()
export class ConferencesOverviewResolver implements Resolve<any> {

  constructor(private conferenceService: ConferenceService) {}

  resolve() {
    return this.conferenceService.getConferences();
  }
}
