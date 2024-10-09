import {ApplicationConfig} from '@angular/core';
import {provideRouter} from '@angular/router';

import {routes} from './app.routes';
import {TripStore} from "./store/trip.store";
import {provideHttpClient} from "@angular/common/http";
import {TripService} from "./services/TripService";

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideHttpClient(),
    TripService,
    TripStore
  ],
};
