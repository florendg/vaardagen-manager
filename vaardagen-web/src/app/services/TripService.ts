import {inject, Injectable} from "@angular/core";
import {Trip} from "../model/trip";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TripService {

  http = inject(HttpClient);

  addTrip(trip: Trip): Observable<Trip> {
    return this.http.post<Trip>('http://localhost:8080/vaardagen-service/vaardagen/trip', trip);
  }

  getTrips(): Observable<Trip[]> {
    return this.http.get<Trip[]>('/vaardagen-service/vaardagen/trips', {
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      }
    });
  }

}
