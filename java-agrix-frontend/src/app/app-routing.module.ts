import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './modules/login/components/login/login.component';
import { FarmListComponent } from './modules/farm/components/farm-list/farm-list.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'farms', component: FarmListComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
