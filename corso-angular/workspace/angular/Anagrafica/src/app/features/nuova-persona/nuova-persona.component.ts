import { Component, OnInit } from '@angular/core';
import { Persona } from '../../shared/model/persona';
import { FormControl, FormGroup } from '@angular/forms';
import { PersonaService } from '../../core/service/persona/persona.service';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import {provideNativeDateAdapter} from '@angular/material/core';

@Component({
  selector: 'app-nuova-persona',
  templateUrl: './nuova-persona.component.html',
  styleUrls: ['./nuova-persona.component.scss'],
  providers: [provideNativeDateAdapter()],
})
export class NuovaPersonaComponent implements OnInit {

 
  formCreazione: FormGroup;


  constructor(private personaService: PersonaService, private router: Router,private dialog: MatDialog) {
    this.formCreazione = new FormGroup({
      nome: new FormControl(''),
      cognome: new FormControl(''),
      citta: new FormControl(''),
      dataNascita: new FormControl('')
    });
  }


  ngOnInit() {

  }

  

  onSubmit() {
    const persona: Persona = new Persona(this.formCreazione.get('nome')?.value, this.formCreazione.get('cognome')?.value,
      this.formCreazione.get('citta')?.value, this.formCreazione.get('dataNascita')?.value);

    this.personaService.crea(persona, ).subscribe({
      next: resp => {
        alert('persona creata');
        this.router.navigate(['/homepage']);

      },
      error: err => {
        console.log('errore nella creazione');

      }
    })
  }

  
    
    
}
