import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { combineLatest, EMPTY, forkJoin, map, Observable } from 'rxjs';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent implements OnInit{

  searchForm: FormGroup = new FormGroup({
    nome: new FormControl<string>('', [Validators.required, Validators.minLength(3)]),
    cognome: new FormControl<string>('', [Validators.required, Validators.minLength(3)]),
  });

  get nome() {
    return this.searchForm.get('nome');
  }

  get cognome() {
    return this.searchForm.get('cognome');
  }


  ngOnInit(): void {
    /*this.nome?.valueChanges.subscribe( value => {
      console.log('nome: ', value);
    });

    this.cognome?.valueChanges.subscribe( value => {
      console.log('cognome: ', value);
    });*/


    const obs1: Observable<any>[] = [
      this.nome?.valueChanges || EMPTY,
      this.cognome?.valueChanges || EMPTY
    ]

    combineLatest(obs1)
    .subscribe(() => {
      console.log('nome: ', this.nome?.value);
      console.log('cognome: ', this.cognome?.value);
    });
  }


}
