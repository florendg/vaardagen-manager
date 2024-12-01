import {patchState, signalStore, withHooks, withMethods, withState} from "@ngrx/signals";
import {Trip} from "../model/trip";
import {inject} from "@angular/core";
import {TripService} from "../services/TripService";
import {exhaustMap, pipe, tap} from "rxjs";
import {rxMethod} from "@ngrx/signals/rxjs-interop";
import{tapResponse} from "@ngrx/operators"

export interface TripState {
  loading: boolean;
  trips: Trip[];
}

const initialState: TripState = {
  loading: false,
  trips: []
};

export const TripStore = signalStore(
  {providedIn: 'root'},
  withState(initialState),
  withMethods((state, tripService = inject(TripService)) => ({
      addTrip: rxMethod<Trip>(
        pipe(
          tap(() => patchState(state, { loading: true })),
          exhaustMap((trip) => tripService.addTrip(trip).pipe(
            tapResponse({
              next: (trip) => patchState(state, {trips:[...state.trips(), trip]}),
              error: (err) => console.log('error:',err)
            })
          ))
        )
      ),
      loadTrips: rxMethod<void>(pipe(
        tap(() => patchState(state, {loading: true })),
        exhaustMap(() => tripService.getTrips().pipe(
          tapResponse({
            next: (trips) => patchState(state, {trips:trips, loading: false}),
            error: (err) => console.log('error:',err),
          })
        ))
      ))
    })
  ), withHooks({
    onInit(store) {
      store.loadTrips();
    }
  }));



