import {Component, EventEmitter, Output} from '@angular/core';
import {Trip} from "../../../model/trip";
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {FormFieldComponent} from "../form-field/form-field.component";
import {DateInputConverter} from "./date-input-converter.directive";


interface TripForm {
  departureDate: FormControl<string>;
  departureHarbour: FormControl<string | undefined>;
  arrivalDate: FormControl<string>;
  arrivalHarbour: FormControl<string | undefined>;
}

interface TripFormValueType {
  departureDate: Date;
  departureHarbour: string | undefined;
  arrivalDate: Date;
  arrivalHarbour: string | undefined;
}

@Component({
  selector: 'app-trip-form',
  standalone: true,
  imports: [
    DateInputConverter,
    FormFieldComponent,
    ReactiveFormsModule
  ],
  templateUrl: './trip-form.component.html',
  styleUrl: './trip-form.component.scss'
})
export class TripFormComponent {

  @Output()
  private onSubmit = new EventEmitter<Trip>();

  tripForm = this.formBuilder.group({
    departureDate: new FormControl<Date>(
      {value: new Date(), disabled: false},
      {validators: Validators.required, updateOn: 'blur', nonNullable: true}
    ),
    departureHarbour: new FormControl<string | undefined>(
      {value: undefined, disabled: false},
      {validators: [Validators.required], updateOn: 'blur', nonNullable: true}
    ),
    arrivalDate: new FormControl<Date>(
      {value: new Date(), disabled: false},
      {validators: [Validators.required], updateOn: 'blur', nonNullable: true}
    ),
    arrivalHarbour: new FormControl<string | undefined>(
      {value: undefined, disabled: false},
      {validators: [Validators.required], updateOn: 'blur', nonNullable: true}
    )
  });

  constructor(private formBuilder: FormBuilder) {
  }

  submit() {
    if (this.tripForm.valid) {
      const value = this.sanatizeValue(this.tripForm.value);
      const trip: Trip = {
        id: value.departureDate ? this.createTripId(value.departureDate) : '',
        departureDate: value.departureDate ? value.departureDate : new Date(),
        departurePort: value.departureHarbour ? value.departureHarbour : "",
        arrivalDate: value.arrivalDate ? value.arrivalDate : new Date(),
        arrivalPort: value.arrivalHarbour ? value.arrivalHarbour : "",
        daysAtSea: this.calculateDaysAtSea(value.departureDate, value.arrivalDate)
      }
      this.onSubmit.emit(trip);
    }
  }

  private sanatizeValue(value: any): TripFormValueType {
    return {
      departureDate: value.departureDate ? value.departureDate : new Date(),
      departureHarbour: value.departureHarbour ? value.departureHarbour : "",
      arrivalDate: value.arrivalDate ? value.arrivalDate : new Date(),
      arrivalHarbour: value.arrivalHarbour ? value.arrivalHarbour : ""
    }
  }

  private calculateDaysAtSea(departureDate: Date, arrivalDate: Date): number {
    const diff = arrivalDate.getTime() - departureDate.getTime();
    return Math.ceil(diff / (1000 * 3600 * 24));
  }

  private createTripId(depatureDate: Date) {
    console.log(depatureDate);
    return depatureDate.getFullYear() + "" +
      (depatureDate.getMonth() + 1).toString().padStart(2,'0')  +
      depatureDate.getDate().toString().padStart(2,'0')
  }

}
