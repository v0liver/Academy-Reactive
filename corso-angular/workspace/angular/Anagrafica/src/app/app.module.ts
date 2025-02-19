import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

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


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    FiltersComponent,
    HeaderComponent,
    FooterComponent,
    HompepageCardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    FlexLayoutModule,
    HttpClientModule,
    ...materiaImport

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
