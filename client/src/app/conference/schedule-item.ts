import {Poster} from "./poster";
import {Presentation} from "./presentation";

export class ScheduleItem {
  id: number;
  topic: string;
  description: string;
  remarks: string;
  localizationDetail: string;
  startDate: string;
  endDate: string;
  posters: Poster[];
  presentation: Presentation;
}
