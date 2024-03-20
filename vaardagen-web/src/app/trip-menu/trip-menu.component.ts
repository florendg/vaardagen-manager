import { Component } from '@angular/core';
import {RouterLink, RouterLinkActive} from "@angular/router";

@Component({
  selector: 'trip-menu',
  standalone: true,
  imports: [
    RouterLink,
    RouterLinkActive,
  ],
  templateUrl: './trip-menu.component.html',
  styleUrl: './trip-menu.component.scss'
})
export class TripMenuComponent {

}
