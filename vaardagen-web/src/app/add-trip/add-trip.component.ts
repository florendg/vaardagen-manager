import {Component, inject} from '@angular/core';
import {TripFormComponent} from "./components/trip-form/trip-form.component";
import {Trip} from "../model/trip";

import {TripStore} from "../store/trip.store";

@Component({
  selector: 'app-add-trip',
  standalone: true,
  imports: [TripFormComponent],
  templateUrl: './add-trip.component.html',
  styleUrl: './add-trip.component.scss'
})
export class AddTripComponent {

  store = inject(TripStore);

  handleTrip(trip: Trip) {
    this.store.addTrip(trip);
  }
}
