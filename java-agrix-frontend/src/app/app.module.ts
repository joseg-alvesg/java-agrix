import { NgModule } from '@angular/core';
import {
  BrowserModule,
  provideClientHydration,
} from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {
  HttpClientModule,
  provideHttpClient,
  withInterceptors,
} from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './modules/login/components/login/login.component';
import { FarmListComponent } from './modules/farm/components/farm-list/farm-list.component';
import { FarmCropsComponent } from './modules/farm/components/farm-crops/farm-crops.component';
import { FarmDetailsComponent } from './modules/farm/components/farm-details/farm-details.component';
import { authInterceptor } from './modules/auth.interceptor';
import { SvgIconsComponent } from './modules/svg-icons/svg-icons.component';
import { UpdateCropModalComponent } from './modules/farm/modals/update-crop-modal/update-crop-modal.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    FarmListComponent,
    FarmCropsComponent,
    FarmDetailsComponent,
    SvgIconsComponent,
    UpdateCropModalComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [
    provideClientHydration(),
    provideHttpClient(withInterceptors([authInterceptor])),
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
