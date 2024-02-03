import {Component, inject} from '@angular/core';
import {TripFormComponent} from "./components/trip-form/trip-form.component";
import {Trip} from "./model/trip";
import {TripService} from "../services/TripService";
import {take} from "rxjs";

@Component({
  selector: 'app-add-trip',
  standalone: true,
  imports: [TripFormComponent],
  templateUrl: './add-trip.component.html',
  styleUrl: './add-trip.component.scss'
})
export class AddTripComponent {

  tripService = inject(TripService);

  handleTrip($event: Trip) {
    this.tripService.addTrip($event).subscribe((x => console.log(x)));
  }
}
