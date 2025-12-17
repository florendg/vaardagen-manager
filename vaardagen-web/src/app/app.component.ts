import { Component } from '@angular/core';

import { RouterOutlet } from '@angular/router';
import {TripMenuComponent} from "./trip-menu/trip-menu.component";

@Component({
    selector: 'vw-root',
    imports: [TripMenuComponent, RouterOutlet],
    templateUrl: './app.component.html',
    styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'Vaardagenmanagement';
}
