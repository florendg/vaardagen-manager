import {Routes} from '@angular/router';

export const routes: Routes = [
  {
    path: 'add-trip',
    loadComponent: () => import('./add-trip/add-trip.component').then(comp => comp.AddTripComponent),
    pathMatch: "prefix"
  },
  {
    path: 'trip-overview',
    loadComponent: () => import('./trip-overview/trip-overview.component').then(comp => comp.TripOverviewComponent),
    pathMatch: "prefix"
  },
  {
    path: '',
    pathMatch: "full",
    redirectTo: 'trip-overview',
  },
];
