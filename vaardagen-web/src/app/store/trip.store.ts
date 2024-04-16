import {patchState, signalStore, withMethods, withState} from "@ngrx/signals";
import {Trip} from "../model/trip";

export interface TripState {
  trips: Trip[];
}

const initialState: TripState = {
  trips: [
    {
      id: '20240301',
      departureDate: new Date('2024-03-01'),
      departurePort: 'Hamburg',
      arrivalDate: new Date('2024-03-11'),
      arrivalPort: 'New York',
      daysAtSea: 10
    }
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

