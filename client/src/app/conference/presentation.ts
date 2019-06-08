import {Author} from "./author";

export class Presentation {
  id: number;
  title: string;
  description: string;
  url: string;
  fileAttachingDate: string;
  authors: Author[];
}
