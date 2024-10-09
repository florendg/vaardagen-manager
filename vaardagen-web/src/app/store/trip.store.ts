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
      async loadTrips() {
        patchState(store, (state) => {
          return {...state, loading: true}
        });
        const response = await fetch('http://localhost:8080/calculator-service/vaardagen/trips', {
          method: "GET",
          mode: "no-cors",
          headers: {
            "Accept": "application/json",
          }
        });
        if (response.ok) {
          const data: Trip[] = await response.json();
          patchState(store, (_state) => {
            return {trips: data, loading: false}
          });
        }
      }
    })
  ), withHooks({
    onInit(store) {
      store.loadTrips();
    }
  }));



