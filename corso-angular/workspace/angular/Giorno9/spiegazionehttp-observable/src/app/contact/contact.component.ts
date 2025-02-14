import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { PersonaService } from '../services/persona.service';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrl: './contact.component.css'
})
export class ContactComponent implements OnInit {

  counter: number = 0;

  constructor(private route: ActivatedRoute, private personaService: PersonaService){}

  ngOnInit(): void {
    console.log('param passato in input obbligatorio: ', this.route.snapshot.paramMap.get('title'));
    console.log('queryparam passato in input opzionale: ', this.route.snapshot.queryParamMap.get('param1'));
    
    
    //const obs: Observable<string> = of('ciao');
//Observable canale in sola lettura, sola ricezione dati
//Observer definizione di come comportarsi nella gestione della response interna all'observable
//Subscription oggetto restituito dalla .subscribe
//Subject observable con capacità di lettura E scrittura

//BehaviourSubject scambio dati in realtime senza tenere uno storico
//ReplaySubject scambio dati in (quasi) realtime tenendo in memoria TUTTO lo storico
//AsyncSubject scambio dati in (quasi) realtime tenendo in memoria SOLO l'ultimo dato che ha transitato.

    /*obs.subscribe({
      next: (resp) => {//entro nella next SOLO e SOLTANTO se la chiamata è andata bene
        console.log(resp);
      },
      error: (err) => {//entro nella error SOLO e SOLTANTO se la chiamata è andata male
        console.error(err);
      },
      complete: () => {//entro sempre, a prescindere dall'esito della chiamata
        console.log('complete');
      }
    });


    obs.subscribe(resp => {
      console.log(resp);
    }, err => {
      console.log(err);
    }, () => {
      console.log('complete');
    });

    obs.subscribe(resp => {
      console.log(resp);
    });*/

  }
}