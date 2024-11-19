import {TripFormComponent} from './trip-form.component';
import {createComponentFactory, Spectator} from "@ngneat/spectator";
import {FormFieldComponent} from "../form-field/form-field.component";

describe('TripFormComponent', () => {
  let component: TripFormComponent;
  let spectator: Spectator<TripFormComponent>;
  const createComponent = createComponentFactory({
    component: TripFormComponent,
    imports: [FormFieldComponent]
  });

  it('should create', () => {
    spectator = createComponent();
    expect(spectator.component).toBeTruthy();
  });

  describe('createTripId', () => {
    const scenarios: { departureDate: Date, id: string }[] = [
      {
        departureDate: new Date('2021-12-01'),
        id: '211202'
      },
      {
        departureDate: new Date('2023-02-28'),
        id: '230301'
      },
      {
        departureDate: new Date('2022-12-31'),
        id: '230101'
      }
    ];
    scenarios.forEach(({departureDate, id}) => {
      it(`should create the proper trip id for ${departureDate.toString()}`, () => {
        spectator = createComponent();
        expect(spectator.component.createTripId(departureDate)).toEqual(id);
      });
    });
  });
  describe('calculateDaysAtSea', () => {
    const scenarios: { departureDate: Date, arrivalDate: Date, daysAtSea: number }[] = [
      {
        departureDate: new Date('2021-12-01'),
        arrivalDate: new Date('2021-12-01'),
        daysAtSea: 1
      },
      {
        departureDate: new Date('2021-12-01'),
        arrivalDate: new Date('2021-12-02'),
        daysAtSea: 2
      },
      {
        departureDate: new Date('2021-12-01'),
        arrivalDate: new Date('2021-12-03'),
        daysAtSea: 3
      },
      {
        departureDate: new Date('2021-12-30'),
        arrivalDate: new Date('2022-01-01'),
        daysAtSea: 3
      }
    ]
    scenarios.forEach(({departureDate, arrivalDate, daysAtSea}) => {
      it(`should calculate the days at sea for ${departureDate.toString()} to ${arrivalDate.toString()}`, () => {
        spectator = createComponent();
        expect(spectator.component.calculateDaysAtSea(departureDate, arrivalDate)).toEqual(daysAtSea);
      });
    });
  });
});
