import {TripStore} from "./trip.store";
import {Trip} from "../model/trip";
import {createServiceFactory, mockProvider, SpectatorService} from "@ngneat/spectator";
import {TripService} from "../services/TripService";

xdescribe('TripStore', () => {

  let spectator: SpectatorService<any>;

  const serviceFactory = createServiceFactory({
    service: TripStore,
    providers: [
      mockProvider(TripService)
    ]
  });

  it('should be initialized with an empty list of trips', () => {
    // Arrange
    spectator = serviceFactory();
    const store = spectator.service;
    expect(store.trips()).toEqual([]);
  });

  it('should be able to add a trip', async () => {

    // Arrange
    spectator = serviceFactory();
    const store = spectator.service;
    const trip: Trip = {
      uuid: 'uuid-123',
      tripNumber: '240330',
      departureDate: new Date('2020-03-30').toISOString().substring(0,10),
      departurePort: 'Rotterdam',
      arrivalDate: new Date('2020-04-01').toISOString().substring(0,10),
      arrivalPort: 'Amsterdam',
      daysAtSea: undefined,
    }
    // Act
    await store.addTrip(trip);
    // Assert
    expect(store.trips()).toEqual([trip]);
  });
});
