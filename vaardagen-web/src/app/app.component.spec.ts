import {AppComponent} from './app.component';
import {Spectator} from "@ngneat/spectator";
import {createComponentFactory} from "@ngneat/spectator";
import {provideRouter} from "@angular/router";

describe('AppComponent', () => {
  let spectator: Spectator<AppComponent>;
  const createComponent = createComponentFactory({
    component: AppComponent,
    providers: [
      provideRouter([])
    ],
  })


  it('should create the app', () => {
    spectator = createComponent()
    const app = spectator.component;
    expect(app).toBeTruthy();
  });
});
