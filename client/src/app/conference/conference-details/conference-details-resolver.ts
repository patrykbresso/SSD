import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, Resolve} from "@angular/router";
import {ConferenceService} from "../conference-service";

@Injectable()
export class ConferenceDetailsResolver implements Resolve<any> {

  constructor(private conferenceService: ConferenceService) {}

  resolve(route: ActivatedRouteSnapshot) {
    return this.conferenceService.getConferenceDetails(route.paramMap.get('id'));
  }
}
