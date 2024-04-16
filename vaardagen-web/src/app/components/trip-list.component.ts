import {Component, input, effect} from "@angular/core";
import {Trip} from "../model/trip";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-trip-list',
  standalone: true,
  imports: [
    DatePipe
  ],
  templateUrl: './trip-list.component.html',
  styleUrl: './trip-list.component.scss'
})
export class TripListComponent {

  trips = input<Trip[]>();
  totalDaysAtSea: number | undefined = 0;

  constructor() {
    effect(() => {
      this.totalDaysAtSea = this.trips()?.map(trip => trip.daysAtSea ? trip.daysAtSea : 0)
        .reduce((acc, days) => acc + days, 0);
    });
  }
}
