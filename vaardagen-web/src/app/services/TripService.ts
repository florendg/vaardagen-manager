import {inject, Injectable} from "@angular/core";
import {Trip} from "../model/trip";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TripService {

  http = inject(HttpClient);

  addTrip(trip: Trip): Observable<void> {
    return this.http.post<void>('http://localhost:8080/calculator-service/vaardagen/trip', trip);
  }

  getTrips(): Observable<Trip[]> {
    return this.http.get<Trip[]>('/calculator-service/vaardagen/trip');
  }

}
