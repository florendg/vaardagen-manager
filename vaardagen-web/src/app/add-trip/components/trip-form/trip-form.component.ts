import {Component, EventEmitter, inject, Output} from '@angular/core';
import {Trip} from "../../../model/trip";
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {FormFieldComponent} from "../form-field/form-field.component";
import dayjs from "dayjs";


type TripForm = FormGroup<{
  departureDate: FormControl<Date>;
  departureHarbour: FormControl<string>;
  arrivalDate: FormControl<Date>;
  arrivalHarbour: FormControl<string>;
}>;

interface TripFormValueType {
  departureDate: Date;
  departureHarbour: string;
  arrivalDate: Date;
  arrivalHarbour: string;
}

@Component({
  selector: 'vw-trip-form',
  standalone: true,
  imports: [
    FormFieldComponent,
    ReactiveFormsModule
  ],
  templateUrl: './trip-form.component.html',
  styleUrl: './trip-form.component.scss'
})
export class TripFormComponent {

  formBuilder: FormBuilder = inject(FormBuilder);

  @Output()
  private submitForm = new EventEmitter<Trip>();

  tripForm: TripForm = this.formBuilder.group({
    departureDate: new FormControl<Date>(
      {value: new Date(), disabled: false},
      {validators: Validators.required, updateOn: 'blur', nonNullable: true}
    ),
    departureHarbour: new FormControl<string>(
      {value: '', disabled: false},
      {validators: [Validators.required, Validators.minLength(1)], updateOn: 'blur', nonNullable: true}
    ),
    arrivalDate: new FormControl<Date>(
      {value: new Date(), disabled: false},
      {validators: [Validators.required,], updateOn: 'blur', nonNullable: true}
    ),
    arrivalHarbour: new FormControl<string>(
      {value: '', disabled: false},
      {validators: [Validators.required, Validators.minLength(1)], updateOn: 'blur', nonNullable: true}
    )
  });

  submit() {
    if (this.tripForm.valid) {
      const value = this.sanitiseValue(this.tripForm);
      console.log('...V....',value);
      const trip: Trip = {
        id: this.createTripId(value.departureDate),
        departureDate: value.departureDate.toString(),
        departurePort: value.departureHarbour,
        arrivalDate: value.arrivalDate.toString(),
        arrivalPort: value.arrivalHarbour,
        daysAtSea: this.calculateDaysAtSea(value.departureDate, value.arrivalDate)
      }
      this.submitForm.emit(trip);
    }
  }

  private sanitiseValue(form: TripForm): TripFormValueType {

    return {
      departureDate:  form.controls.departureDate.value,
      departureHarbour: form.controls.departureHarbour.value,
      arrivalDate: form.controls.arrivalDate.value,
      arrivalHarbour: form.controls.arrivalHarbour.value
    }
  }

  calculateDaysAtSea(departureDate: Date, arrivalDate: Date): number {
    const diff = dayjs(arrivalDate).diff(departureDate, 'day');
    return diff + 1;
  }

  createTripId(departureDate: Date) {
    const startDate = dayjs(departureDate).add(1, 'day').toDate();
    return startDate.getFullYear().toString().substring(2) +
      (startDate.getMonth() + 1).toString().padStart(2,'0')  +
      startDate.getDate().toString().padStart(2,'0')
  }

}
