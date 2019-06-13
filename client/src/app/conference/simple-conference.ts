import {ConferenceInformation} from "./conference-information";
import {Address} from "./address";

export class SimpleConference {
  id: number;
  startDate: string;
  endDate: string;
  registrationDate: string;
  cancelled: boolean;
  conferenceInformation: ConferenceInformation;
  address: Address;
}
