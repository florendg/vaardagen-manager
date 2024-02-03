import {Injectable} from "@angular/core";
import {Trip} from "../add-trip/model/trip";
import {fromPromise} from "rxjs/internal/observable/innerFrom";

@Injectable({
  providedIn: 'root'
})
export class TripService {

  constructor() { }

  addTrip(trip: Trip) {
    const url = new URL('http://localhost:8080/calculator-service/vaardagen/add');
    const response =  fetch(url,{
      method: 'POST',
      body: JSON.stringify(trip)
    });
    return fromPromise(response);
  }
}
