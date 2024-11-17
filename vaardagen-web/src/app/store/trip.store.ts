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
  withMethods((store, tripService = inject(TripService)) => ({
      addTrip: rxMethod<Trip>(
        pipe(
          tap((state) => patchState(store, {...state, loading: true })),
          tap((trip) => console.log(JSON.stringify(trip))),
          exhaustMap((trip) => tripService.addTrip(trip).pipe(
            tapResponse({
              next: (trip) => console.log('next',),
              error: (err) => console.log('error:',err),
              finalize: () => console.log('done')
            })
          ))
        )
      ),
      loadTrips: rxMethod<void>(pipe(
        tap(() => patchState(store, {loading: true })),
        exhaustMap(() => tripService.getTrips().pipe(
          tapResponse({
            next: (trips) => patchState(store, {trips:trips, loading: false}),
            error: (err) => console.log('error:',err),
            finalize: () => console.log('done')
          })
        ))
      ))
    })
  ), withHooks({
    onInit(store) {
      store.loadTrips();
    }
  }));



