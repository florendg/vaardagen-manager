import {Component, EventEmitter, Output} from '@angular/core';
import {Trip} from "../../model/trip";
import {FormBuilder, ReactiveFormsModule, Validators} from "@angular/forms";

@Component({
  selector: 'app-trip-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './trip-form.component.html',
  styleUrl: './trip-form.component.scss'
})
export class TripFormComponent {

  @Output()
  private onSubmit = new EventEmitter<Trip>();

  tripForm = this.formBuilder.nonNullable.group({
    departureDate: [new Date(), Validators.required],
    departureHarbour: ['', Validators.required],
    arrivalDate: [new Date(), Validators.required],
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

}
