import {Component, EventEmitter, Output} from '@angular/core';
import {Trip} from "../../../model/trip";
import {FormBuilder, FormControl, ReactiveFormsModule, Validators} from "@angular/forms";
import {FormFieldComponent} from "../form-field/form-field.component";

@Component({
  selector: 'app-trip-form',
  standalone: true,
  imports: [FormFieldComponent, ReactiveFormsModule],
  templateUrl: './trip-form.component.html',
  styleUrl: './trip-form.component.scss'
})
export class TripFormComponent {

  @Output()
  private onSubmit = new EventEmitter<Trip>();

  tripForm = this.formBuilder.group({
    departureDate: new FormControl<Date | undefined>(
      {value: undefined, disabled: false},
      Validators.required
    ),
    departureHarbour: new FormControl<string | undefined>({value: undefined, disabled: false},
      {validators:[Validators.required], updateOn: "blur"}),
    arrivalDate: [{value: undefined, disabled: false}, Validators.required],
    arrivalHarbour: ['', Validators.required]
  });

  constructor(private formBuilder: FormBuilder) {
  }

  submit() {
    if (this.tripForm.valid) {
      const value = this.tripForm.value;
      const trip: Trip = {
        id: "",
        departureDate: value.departureDate ? value.departureDate : new Date(),
        departurePort: value.departureHarbour ? value.departureHarbour : "",
        arrivalDate: value.arrivalDate ? value.arrivalDate : new Date(),
        arrivalPort: value.arrivalHarbour ? value.arrivalHarbour : "",
        daysAtSea: 0
      }
      this.onSubmit.emit(trip);
    }
  }

  isDirty() {
    if(this.tripForm.controls.departureHarbour.value === '' ) {
      this.tripForm.controls.departureHarbour.reset();
    }
    return this.tripForm.controls.departureHarbour.dirty;
  }

  isTouched() {
    return this.tripForm.controls.departureHarbour.touched;
  }

  isPristine() {
    return this.tripForm.controls.departureHarbour.pristine;
  }


}
