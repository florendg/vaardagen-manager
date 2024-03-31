import {TripStore} from "./trip.store";
import {Trip} from "../model/trip";

describe('TripStore', () => {
  it('should be initialized with an empty list of trips', () => {
    // Arrange
    const store = new TripStore();
    expect(store.trips()).toEqual([]);
  });

  it('should be able to add a trip', () => {
    // Arrange
    const store = new TripStore();
    const trip: Trip = {
      id: '240330',
      departureDate: new Date('2020-03-30'),
      departurePort: 'Rotterdam',
      arrivalDate: new Date('2020-04-01'),
      arrivalPort: 'Amsterdam',
      daysAtSea: undefined,
    }
    // Act
    store.addTrip(trip);
    // Assert
    expect(store.trips()).toEqual([trip]);
  });
});
