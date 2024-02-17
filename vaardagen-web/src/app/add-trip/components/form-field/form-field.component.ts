import {Component, ContentChild, ContentChildren, QueryList, ViewEncapsulation} from '@angular/core';
import {ChangeDetectionStrategy} from "@angular/compiler";
import {LabelDirective} from "./directives/label-directive";
import {NgControl} from "@angular/forms";
import {HintDirective} from "./directives/hint.directive";

@Component({
  selector: 'app-form-field',
  standalone: true,
  imports: [],
  templateUrl: './form-field.component.html',
  styleUrl: './form-field.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
  encapsulation: ViewEncapsulation.None
})
export class FormFieldComponent {

  @ContentChild(LabelDirective) private label?: LabelDirective;
  @ContentChild(NgControl, {static: true, descendants: false}) private ngControl?: NgControl;
  @ContentChildren(HintDirective) private hints?: QueryList<HintDirective>;

  formFieldLabelId: string = 'form-field-id';

  get control() {
    return this.ngControl?.control;
  }

  get errors() {
    return this.control?.errors ?? {};
  }

  hasLabel() {
    return this.label !== undefined;
  }

  hasErrors() {
    return this.control?.invalid === true /* && !isNull(this.control.errors) */;
  }

}
