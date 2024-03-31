import {patchState, signalStore, withMethods, withState} from "@ngrx/signals";
import {Trip} from "../model/trip";

export interface TripState {
  trips: Trip[];
}

const initialState: TripState = {
  trips: [
  ]
};

export const TripStore = signalStore(
  {providedIn: 'root'},
  withState(initialState),
  withMethods((store) => ({
      addTrip(trip: Trip) {
        patchState(store, (state) => {
          return ({...state, trips: [...state.trips, trip]})
        })
      }
    }),
  ));

