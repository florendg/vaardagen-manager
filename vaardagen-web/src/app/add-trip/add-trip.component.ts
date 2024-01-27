import { Component } from '@angular/core';
import {TripFormComponent} from "./components/trip-form/trip-form.component";
import {Trip} from "./model/trip";

@Component({
  selector: 'app-add-trip',
  standalone: true,
  imports: [TripFormComponent],
  templateUrl: './add-trip.component.html',
  styleUrl: './add-trip.component.scss'
})
export class AddTripComponent {

  handleTrip($event: Trip) {
    console.log($event);
  }
}
