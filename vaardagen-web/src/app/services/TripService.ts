import {inject, Injectable} from "@angular/core";
import {Trip} from "../model/trip";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TripService {

  http = inject(HttpClient);

  addTrip(trip: Trip): Observable<number> {
    return this.http.post<number>('/calculator-service/vaardagen/trip', trip);
  }

getTrips(): Observable<Trip[]> {
    return this.http.get<Trip[]>('/calculator-service/vaardagen/trip');
  }

}
