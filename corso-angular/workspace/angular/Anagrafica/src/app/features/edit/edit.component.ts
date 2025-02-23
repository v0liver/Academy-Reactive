import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Persona } from '../../shared/model/persona';
import { FormControl, FormControlName, FormGroup } from '@angular/forms';
import { PersonaService } from '../../core/service/persona/persona.service';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { DialogModificaComponent } from '../dialog-modifica/dialog-modifica.component';
import { MAT_DATE_FORMATS, provideNativeDateAdapter } from '@angular/material/core';
import { DatePipe } from '@angular/common';

export const MY_DATE_FORMATS = {
  display: {
    dateInput: 'dd/MM/yyyy',
    monthYearLabel: 'MMM yyyy',
    dateA11yLabel: 'DD/MM/YYYY',
    monthYearA11yLabel: 'MMMM YYYY'
  }
};

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.scss'],
  providers: [provideNativeDateAdapter(),
    DatePipe,
    { provide: MAT_DATE_FORMATS, useValue: MY_DATE_FORMATS }
  ]
})
export class EditComponent implements OnInit {

  isDisable: boolean;
  persona: Persona;
  formModifica: FormGroup;


  constructor(private personaService: PersonaService, private router: Router,private dialog: MatDialog,private datePipe: DatePipe) {
    const state = history.state;
    this.persona = state.persona;
    this.isDisable = state.isDisable
    console.log(state);

    // const formattedDate = this.datePipe.transform(this.persona.dataNascita, 'dd/MM/yyyy');
    this.formModifica = new FormGroup({
      nome: new FormControl({ value: this.persona.nome, disabled: this.isDisable }),
      cognome: new FormControl({ value: this.persona.cognome, disabled: this.isDisable }),
      citta: new FormControl({ value: this.persona.citta, disabled: this.isDisable }),
      dataNascita: new FormControl({ value: new Date(this.persona.dataNascita), disabled: this.isDisable })
    });
  }


  ngOnInit() {

  }

  modifica(persona: Persona) {
    this.isDisable=false;
    this.formModifica.controls['nome'].enable();
    this.formModifica.controls['cognome'].enable();
    this.formModifica.controls['citta'].enable();
    this.formModifica.controls['dataNascita'].enable();
  }

  onSubmit() {
    const personaEdit: Persona = new Persona(this.formModifica.get('nome')?.value, this.formModifica.get('cognome')?.value,
      this.formModifica.get('citta')?.value, this.formModifica.get('dataNascita')?.value);

    this.personaService.edit(personaEdit, this.persona.id!).subscribe({
      next: resp => {
        alert('persona modificata');
        this.router.navigate(['/homepage']);

      },
      error: err => {
        console.log('errore nella modifica');

      }
    })
  }

  delete(persona: Persona) {
    this.dialog.open(DialogModificaComponent, {
      width: '250px',
      enterAnimationDuration:0,
      exitAnimationDuration:0,
      data:{persona:persona}
    });
    
    
  }
}
