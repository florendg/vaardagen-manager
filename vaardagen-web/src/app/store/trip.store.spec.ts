import {TripStore} from "./trip.store";
import {Trip} from "../model/trip";
import {createServiceFactory, SpectatorService} from "@ngneat/spectator/vitest";
import {TripService} from "../services/TripService";
import {beforeEach, vi, expect, describe, it, afterEach} from "vitest";
import {Observable, of} from "rxjs";


describe('TripStore', () => {

  let spectator: SpectatorService<any>;

  const mockTripService = {
    getTrips: vi.fn(() :Observable<Trip[]> => of([])),
    addTrip: vi.fn((trip: Trip): Observable<Trip> => of(trip))
  };

  const serviceFactory = createServiceFactory({
    service: TripStore,
    providers: [
      {
        provide: TripService,
        useValue: mockTripService
      }
    ]
  });

  beforeEach(() => {
    spectator = serviceFactory();
  })

  afterEach(() => {
    vi.resetAllMocks();
  })

  it('should be initialized with an empty list of trips', () => {
    // Arrange
    const store = spectator.service;
    expect(store.trips()).toEqual([]);
  });

  it('should be able to add a trip', async () => {

    const store = spectator.service;
    const trip: Trip = {
      uuid: 'uuid-123',
      tripNumber: '240330',
      departureDate: new Date('2020-03-30').toISOString().substring(0, 10),
      departurePort: 'Rotterdam',
      arrivalDate: new Date('2020-04-01').toISOString().substring(0, 10),
      arrivalPort: 'Amsterdam',
      daysAtSea: undefined,
    };
    // Act
    await store.addTrip(trip);
    // Assert
    expect(store.trips()).toEqual([trip]);
  });
});
