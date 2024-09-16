import {patchState, signalStore, withMethods, withState} from "@ngrx/signals";
import {Trip} from "../model/trip";

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
  withMethods((store) => ({
      addTrip(trip: Trip) {
        patchState(store, (state) => {
          return ({...state, trips: [...state.trips, trip]})
        })
      },
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
  ));



