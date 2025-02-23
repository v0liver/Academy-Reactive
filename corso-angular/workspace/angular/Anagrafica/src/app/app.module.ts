import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './features/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import { FlexLayoutModule } from '@angular/flex-layout';
import { materiaImport } from './material-import';
import { FiltersComponent } from './shared/component/filters/filters.component';
import { HeaderComponent } from './core/header/header.component';
import { FooterComponent } from './core/footer/footer.component';
import { HompepageCardComponent } from './features/homepage-card/hompepage-card.component';
import { NotificationComponent } from './shared/notification/notification.component';
import { JwInterceptor } from './shared/interceptor/jwt.interceptor';
import { DialogModificaComponent } from './features/dialog-modifica/dialog-modifica.component';
import { EditComponent } from './features/edit/edit.component';
import { NuovaPersonaComponent } from './features/nuova-persona/nuova-persona.component';
import { MAT_DATE_LOCALE } from '@angular/material/core';
import { HomepageTableComponent } from './features/homepage-table/homepage-table.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    FiltersComponent,
    HeaderComponent,
    FooterComponent,
    HompepageCardComponent,
    NotificationComponent,
    DialogModificaComponent,
    EditComponent,
    NuovaPersonaComponent,
    HomepageTableComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    FlexLayoutModule,
    HttpClientModule,
    ...materiaImport,

  ],
  providers: [{
    provide: HTTP_INTERCEPTORS, useClass: JwInterceptor, multi: true
  },
  {
    provide: MAT_DATE_LOCALE, useValue: 'en-GB'
  }
],
  bootstrap: [AppComponent]
})
export class AppModule { }
