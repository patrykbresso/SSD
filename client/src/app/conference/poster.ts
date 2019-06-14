import {Author} from "./author";

export class Poster {
  id: number;
  title: string;
  description: string;
  url: string;
  fileAttachingDate: string;
  authors: Author[];
}
