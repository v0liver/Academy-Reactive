import { Component, OnInit } from '@angular/core';
import { Persona } from '../../shared/model/persona';
import { SearchPersona } from '../../shared/model/search-persona';
import { PageEvent } from '@angular/material/paginator';
import { PersonaService } from '../../core/service/persona/persona.service';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table'; // Importa MatTableDataSource

@Component({
  selector: 'app-homepage-table',
  templateUrl: './homepage-table.component.html',
  styleUrls: ['./homepage-table.component.scss']
})
export class HomepageTableComponent implements OnInit {

  pageSize = 0;
  totalElement = 0;
  persone: Persona[] = [];
  colonneVisualizzate: string[] = ['nome', 'cognome', 'azioni']; 

  // Usa MatTableDataSource per gestire i dati della tabella
  dataSource = new MatTableDataSource<Persona>(this.persone);

  constructor(
    private personaService: PersonaService, 
    public dialog: MatDialog,
    private router: Router
  ) {}

  ngOnInit() {
    this.callPersone(new SearchPersona());
  }

  callPersone(event: SearchPersona) {
    let searchPersona: SearchPersona = event;
    this.personaService.getUser(searchPersona).subscribe({
      next: resp => {
        console.log(resp);
        this.pageSize = resp.size;
        this.totalElement = resp.totalElements;
        this.persone = resp.content;
        this.dataSource = new MatTableDataSource(this.persone); // Imposta i dati nella dataSource
      }
    });
  }

  modifica(persona: Persona) {
    const isDisable = false;
    this.router.navigate(['/edit'], { state: { persona, isDisable: isDisable } });
  }

  dettagli(persona: Persona) {
    const isDisable = true;
    this.router.navigate(['/edit'], { state: { persona, isDisable: isDisable } });
  }

  changePage(event: PageEvent) {
    let searchPersona: SearchPersona = new SearchPersona();
    searchPersona.page = event.pageIndex;
    searchPersona.pageSize = event.pageSize;
    this.callPersone(searchPersona);
  }
}
