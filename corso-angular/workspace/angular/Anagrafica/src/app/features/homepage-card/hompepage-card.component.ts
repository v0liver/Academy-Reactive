import { Component, OnInit } from '@angular/core';
import { Persona } from '../../shared/model/persona';
import { PersonaService } from '../../core/service/persona/persona.service';
import { SearchPersona } from '../../shared/model/search-persona';
import { DialogModificaComponent } from '../dialog-modifica/dialog-modifica.component';
import { MatDialog } from '@angular/material/dialog';
import { Route, Router } from '@angular/router';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-hompepage-card',
  templateUrl: './hompepage-card.component.html',
  styleUrls: ['./hompepage-card.component.scss']
})
export class HompepageCardComponent implements OnInit {
  pageSize=0;
  totalElement=0;

  persone: Persona[] = [];
  
  constructor(private personaService: PersonaService, public dialog: MatDialog,private router:Router) {

  }

  ngOnInit() {
    this.callPersone(new SearchPersona());
  }

  callPersone(event: SearchPersona) {

    let searchPersona: SearchPersona = event;
    this.personaService.getUser(searchPersona).subscribe({
      next: resp => {
        console.log(resp);
        this.pageSize=resp.size;
        this.totalElement=resp.totalElements;
        this.persone = resp.content;
      }
    });
  }

  // openDialog(persona:Persona){
  //   const dialogRef = this.dialog.open(DialogModificaComponent, {
  //     // autoFocus: true,// per aria-hidden??
  //     width: '260px',
  //   });

  //   dialogRef.afterClosed().subscribe(result => {
  //     console.log('Dialog was closed');
  //   });

  // }

  modifica(persona:Persona){
    const isDisable = false;
    this.router.navigate(['/edit'],{state:{persona,isDisable: isDisable}})
  }  
  
  dettagli(persona:Persona){
    const isDisable = true;
    this.router.navigate(['/edit'],{state:{persona,isDisable: isDisable}})
  }

  changePage(event:PageEvent){
    let searchPersona:SearchPersona= new SearchPersona();
    searchPersona.page=event.pageIndex;
    searchPersona.pageSize=event.pageSize;

    
    this.callPersone(searchPersona);

  }

}
