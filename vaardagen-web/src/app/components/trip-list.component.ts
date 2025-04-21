import {Component, input, computed} from "@angular/core";
import {Trip} from "../model/trip";

@Component({
  selector: 'vw-trip-list',
  templateUrl: './trip-list.component.html',
  styleUrl: './trip-list.component.scss'
})
export class TripListComponent {

  trips = input<Trip[]>();
  totalDaysAtSea = computed(() => this.trips()?.map(trip => trip.daysAtSea ? trip.daysAtSea : 0)
    .reduce((acc, days) => acc + days, 0));

}
