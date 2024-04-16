import {Component, inject} from '@angular/core';
import {TripFormComponent} from "./components/trip-form/trip-form.component";
import {Trip} from "../model/trip";

import {TripStore} from "../store/trip.store";
import {TripListComponent} from "../components/trip-list.component";

@Component({
  selector: 'app-add-trip',
  standalone: true,
  imports: [TripFormComponent, TripListComponent],
  templateUrl: './add-trip.component.html',
  styleUrl: './add-trip.component.scss'
})
export class AddTripComponent {

  store = inject(TripStore);
  trips = this.store.trips;

  handleTrip(trip: Trip) {
    this.store.addTrip(trip);
  }
}
