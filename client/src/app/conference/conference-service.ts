import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class ConferenceService {

  baseUrl: string = 'http://localhost:8080/api/conference/';

  constructor(private http: HttpClient) {}

  getConferences() {
    return this.http.get(this.baseUrl + 'all');
  }

  getConferenceDetails(id: string) {
    return this.http.get(this.baseUrl + id);
  }
}
