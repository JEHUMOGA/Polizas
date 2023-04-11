import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { PolizasService } from './services/polizas/polizas.service'
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontpolizas';
  
}
