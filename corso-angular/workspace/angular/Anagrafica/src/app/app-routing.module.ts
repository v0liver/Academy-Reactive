import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './features/login/login.component';
import { HompepageCardComponent } from './features/homepage-card/hompepage-card.component';
import { EditComponent } from './features/edit/edit.component';
import { NuovaPersonaComponent } from './features/nuova-persona/nuova-persona.component';
import { HomepageTableComponent } from './features/homepage-table/homepage-table.component';

const routes: Routes = [
  
  {
    path: '', component: LoginComponent
  },
  {
    path: 'homepage', component: HomepageTableComponent
  },
  {
    path: 'edit', component: EditComponent
  },
  {
    path: 'nuova-persona', component: NuovaPersonaComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
