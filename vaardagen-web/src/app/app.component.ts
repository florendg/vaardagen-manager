import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import {TripMenuComponent} from "./trip-menu/trip-menu.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [TripMenuComponent, CommonModule, RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'Vaardagenmanagement';
}
