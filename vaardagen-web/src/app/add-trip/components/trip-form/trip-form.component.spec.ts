import {TripFormComponent} from './trip-form.component';
import {createComponentFactory, Spectator} from "@ngneat/spectator/vitest";
import {FormFieldComponent} from "../form-field/form-field.component";
import { describe, it, expect} from "vitest";

describe('TripFormComponent', () => {
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
