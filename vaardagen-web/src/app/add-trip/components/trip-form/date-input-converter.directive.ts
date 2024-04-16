import {ControlValueAccessor, NG_VALUE_ACCESSOR} from "@angular/forms";
import {Directive, ElementRef, forwardRef, HostListener, Renderer2} from "@angular/core";

@Directive({
  selector: 'input[type=date][ngModel], input[type=date][formControlName], input[type=date][formControl]',
  providers: [{
    provide: NG_VALUE_ACCESSOR,
    useExisting: forwardRef(() => DateInputConverter),
    multi: true
  }],
  standalone: true
})
export class DateInputConverter implements ControlValueAccessor {
  @HostListener('blur', []) onTouched: any = () => {};
  @HostListener('input', ['$event']) onChange: any = () => {};

  private valueType: 'value' | 'valueAsDate' = 'value';

  constructor(
    private renderer: Renderer2,
    private elementRef: ElementRef
  ) {
  }

  registerOnChange(fn: (value: any)=>void): void {
    this.onChange = (event: any) => fn(event.target[this.valueType]);
  }

  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }

  setDisabledState(_isDisabled: boolean): void {
  }

  writeValue(value: Date | string) {
    this.valueType = typeof value === 'string' ? 'value' : 'valueAsDate';
    this.renderer.setProperty(this.elementRef.nativeElement, this.valueType, value);
  }
}
