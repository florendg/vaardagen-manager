import {Component, effect, inject} from '@angular/core';
import {TripFormComponent} from "../add-trip/components/trip-form/trip-form.component";
import {CommonModule} from "@angular/common";
import {TripStore} from "../store/trip.store";

@Component({
  selector: 'app-trip-overview',
  standalone: true,
  imports: [
    CommonModule,
    TripFormComponent
  ],
  templateUrl: './trip-overview.component.html',
  styleUrl: './trip-overview.component.scss'
})
export class TripOverviewComponent  {

  store = inject(TripStore);
  trips = this.store.trips;

  totalDaysAtSea: number = this.store.trips()
    .map(trip => trip.daysAtSea ? trip.daysAtSea : 0)
    .reduce((a, b) => a + b, 0);
}
