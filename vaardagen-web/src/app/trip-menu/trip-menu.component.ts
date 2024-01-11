import { Component } from '@angular/core';
import {RouterLink, RouterLinkActive} from "@angular/router";
import {MatToolbarModule} from "@angular/material/toolbar";

@Component({
  selector: 'trip-menu',
  standalone: true,
  imports: [
    RouterLink,
    RouterLinkActive,
    MatToolbarModule,
  ],
  templateUrl: './trip-menu.component.html',
  styleUrl: './trip-menu.component.scss'
})
export class TripMenuComponent {

}
