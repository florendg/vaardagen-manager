import {ChangeDetectionStrategy, Component, inject} from '@angular/core';

import {TripStore} from "../store/trip.store";
import {TripListComponent} from "../components/trip-list.component";

@Component({
    selector: 'vw-trip-overview',
    imports: [
    TripListComponent
],
    templateUrl: './trip-overview.component.html',
    styleUrl: './trip-overview.component.scss',
    changeDetection: ChangeDetectionStrategy.OnPush
})
export class TripOverviewComponent {

  store = inject(TripStore);
  trips = this.store.trips;

}
