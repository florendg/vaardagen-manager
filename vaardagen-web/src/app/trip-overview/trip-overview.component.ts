import { Component } from '@angular/core';
import {TripFormComponent} from "../add-trip/components/trip-form/trip-form.component";
import {Trip} from "../add-trip/model/trip";
import {CommonModule} from "@angular/common";

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
export class TripOverviewComponent {
  trips: Trip[] = [
    {
      id: "1",
      departureDate: new Date(),
      departurePort: "Amsterdam",
      arrivalDate: new Date(),
      arrivalPort: "Rotterdam",
      daysAtSea: 1
    },
    {
      id: "2",
      departureDate: new Date(),
      departurePort: "Rotterdam",
      arrivalDate: new Date(),
      arrivalPort: "Amsterdam",
      daysAtSea: 1
    }
  ];
  totalDaysAtSea: number = this.trips.map(trip => trip.daysAtSea).reduce((a, b) => a + b, 0);
}
