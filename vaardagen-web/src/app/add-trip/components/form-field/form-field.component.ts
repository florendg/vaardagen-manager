import {Component, ContentChild} from '@angular/core';
import {NgControl} from "@angular/forms";

@Component({
  selector: 'vw-form-field',
  standalone: true,
  imports: [],
  templateUrl: './form-field.component.html',
  styleUrl: './form-field.component.scss'
})
export class FormFieldComponent {

  @ContentChild(NgControl, {static: true, descendants: false}) private ngControl?: NgControl;

  get control() {
    return this.ngControl?.control;
  }

  get errors() {
    return this.control?.errors ?? {};
  }

  hasErrors() {
    return this.control?.invalid === true /* && !isNull(this.control.errors) */;
  }

}
