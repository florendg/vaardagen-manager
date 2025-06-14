import {ApplicationConfig, isDevMode} from '@angular/core';
import {provideRouter, withDebugTracing} from '@angular/router';

import {routes} from './app.routes';
import {TripStore} from "./store/trip.store";
import {provideHttpClient, withFetch } from "@angular/common/http";
import {TripService} from "./services/TripService";
import {provideStoreDevtools} from "@ngrx/store-devtools";

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes,withDebugTracing()),
    provideHttpClient(
      withFetch()
    ),
    provideStoreDevtools({
      maxAge: 25, // Retains last 25 states
      logOnly: !isDevMode(), // Restrict extension to log-only mode
      autoPause: true, // Pauses recording actions and state changes when the extension window is not open
      trace: false, //  If set to true, will include stack trace for every dispatched action, so you can see it in trace tab jumping directly to that part of code
      traceLimit: 75, // maximum stack trace frames to be stored (in case trace option was provided as true)
      connectInZone: true // If set to true, the connection is established within the Angular zone
    }),
    TripService,
    TripStore
  ],
};
