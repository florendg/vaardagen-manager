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
});
