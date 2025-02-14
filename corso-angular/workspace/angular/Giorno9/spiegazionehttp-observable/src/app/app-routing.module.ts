import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ContactComponent } from './contact/contact.component';
import { HomepageComponent } from './homepage/homepage.component';
import { SearchComponent } from './search/search.component';

const routes: Routes = [
  {path: 'contact/:title', component: ContactComponent,
    //canActivate: function che determina con un booleano se ho i permessi per visualizzare questa pagina
    //data: dati statici associati a questa rotta
    //title: parametro generalmente usato per il titolo di pagina
    //resolve: function che aggiunge logiche prima della visualizzazione di pagina
  },
  {path: 'search', component: SearchComponent},
  {path: '', component: HomepageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
