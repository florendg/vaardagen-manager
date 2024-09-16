import {AfterViewInit, Component, effect, inject} from '@angular/core';
import {TripFormComponent} from "../add-trip/components/trip-form/trip-form.component";
import {CommonModule} from "@angular/common";
import {TripStore} from "../store/trip.store";
import {TripListComponent} from "../components/trip-list.component";

@Component({
  selector: 'app-trip-overview',
  standalone: true,
  imports: [
    CommonModule,
    TripFormComponent,
    TripListComponent
  ],
  templateUrl: './trip-overview.component.html',
  styleUrl: './trip-overview.component.scss',
})
export class TripOverviewComponent  implements AfterViewInit{

  store = inject(TripStore);
  trips = this.store.trips;

  constructor() {
    effect(() => {
      console.log('number of trips', this.trips().length)
    });
  }

  ngAfterViewInit(): void {
    this.store.loadTrips();
  }


}
