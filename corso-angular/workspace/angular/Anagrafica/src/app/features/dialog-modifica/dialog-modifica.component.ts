import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { PersonaService } from '../../core/service/persona/persona.service';
import { Persona } from '../../shared/model/persona';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dialog-modifica',
  templateUrl: './dialog-modifica.component.html',
  styleUrls: ['./dialog-modifica.component.scss']
})
export class DialogModificaComponent implements OnInit {

  persona!: Persona;

  constructor(public dialogRef: MatDialogRef<DialogModificaComponent>,@Inject(MAT_DIALOG_DATA) public data: { persona: Persona },private personaService:PersonaService,private router:Router) {
    if (data) {
      this.persona = data.persona;
    }
   
  }

  ngOnInit() {
    
  }

 delete(){
  this.personaService.delete(this.persona).subscribe({
    next: resp => {
      alert('eliminato');
      this.dialogRef.close();
      this.router.navigate(['/homepage'])
    },
    error: err => {
      alert('errore nell eliminazione');
    }
  })
 }

 annulla(){
  this.dialogRef.close();
 }

}


