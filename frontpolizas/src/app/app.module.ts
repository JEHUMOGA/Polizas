import { Component, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PolizasComponent } from './polizas/polizas.component';
import { DetallePolizaComponent } from './detalle-poliza/detalle-poliza.component';
import { RouterModule } from '@angular/router';


@NgModule({
  declarations: [
    AppComponent,
    PolizasComponent,
    DetallePolizaComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule.forRoot([
      {path: 'polizas', component:PolizasComponent},
      {path: 'detalleArticulo', component:DetallePolizaComponent},
      {path: '', component:PolizasComponent},      
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
