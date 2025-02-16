import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './features/authentication/login/login.component';
import { HomepageComponent } from './features/homepage/homepage.component';
import { ListaUtentiComponent } from './features/lista-utenti/lista-utenti.component';

const routes: Routes = [
  { path: 'lista-utenti', component: ListaUtentiComponent },
  { path: 'homepage', component: HomepageComponent },
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
