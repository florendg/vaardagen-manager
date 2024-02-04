import {inject, Injectable} from "@angular/core";
import {Trip} from "../add-trip/model/trip";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TripService {

  http = inject(HttpClient);

  constructor() { }

  addTrip(trip: Trip): Observable<number> {
    return this.http.post<number>('/calculator-service/vaardagen/trip', trip);
  }
}
