import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './features/login/login.component';
import { HompepageCardComponent } from './features/homepage-card/hompepage-card.component';

const routes: Routes = [
  {
    path:'',component:LoginComponent
  },
  {
    path:'homepage', component:HompepageCardComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
