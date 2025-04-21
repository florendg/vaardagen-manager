import {ChangeDetectionStrategy, Component, inject} from '@angular/core';
import {CommonModule} from "@angular/common";
import {TripStore} from "../store/trip.store";
import {TripListComponent} from "../components/trip-list.component";

@Component({
    selector: 'vw-trip-overview',
    imports: [
        CommonModule,
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
