import {Component, inject, signal} from '@angular/core';
import {TripFormComponent} from "./components/trip-form/trip-form.component";
import {Trip} from "./model/trip";
import {TripService} from "../services/TripService";
import {take} from "rxjs";
import {HttpClientModule} from "@angular/common/http";

@Component({
  selector: 'app-add-trip',
  standalone: true,
  imports: [TripFormComponent],
  providers:  [HttpClientModule],
  templateUrl: './add-trip.component.html',
  styleUrl: './add-trip.component.scss'
})
export class AddTripComponent {

  tripService = inject(TripService);

  tripLength = signal(0);

  handleTrip($event: Trip) {
    this.tripService.addTrip($event).subscribe(x => this.tripLength.set(x));
  }
}
