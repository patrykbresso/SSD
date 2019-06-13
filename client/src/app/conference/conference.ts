import {ConferenceInformation} from "./conference-information";
import {Address} from "./address";
import {Schedule} from "./schedule";

//TODO: add other fields
export class Conference {
  id: number;
  startDate: string;
  endDate: string;
  registrationDate: string;
  cancelled: boolean;
  conferenceInformation: ConferenceInformation;
  schedule: Schedule;
  address: Address;
}
