import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {provideNativeDateAdapter} from '@angular/material/core';
import { MatAccordion } from '@angular/material/expansion';
import { Persona } from '../../model/persona';
import { SearchPersona } from '../../model/search-persona';

@Component({
  selector: 'app-filters',
  templateUrl: './filters.component.html',
  styleUrls: ['./filters.component.scss'],
  providers: [provideNativeDateAdapter()]
})
export class FiltersComponent implements OnInit {
  @ViewChild(MatAccordion) accordion?: MatAccordion;

  @Output() inviaDati: EventEmitter<SearchPersona>= new EventEmitter();

  filterForm = new FormGroup({
    nome: new FormControl<string >('',[Validators.minLength(3)]),
    cognome: new FormControl<string>('',[Validators.minLength(3)]),
    citta: new FormControl<string >('',[Validators.minLength(3)]),
    dataNascitaMin: new FormControl<string >(''),
    dataNascitaMax: new FormControl<string >(''),
    page: new FormControl<number>(1),
    pageSize: new FormControl<number>(5),
    sortBy: new FormControl<string>(''),
    sortDirection: new FormControl<string>(''),
  });


  constructor() { }

  onSubmit(): void{
    const formValues = this.filterForm.value;
    const formattedData: any = {
      ...formValues,
      dataNascitaMin: this.formatDate(formValues.dataNascitaMin!),
      dataNascitaMax: this.formatDate(formValues.dataNascitaMax!),
    };
    
    this.inviaDati.emit((formattedData as SearchPersona ));

  }
  formatDate(data: any) {
    if (data instanceof Date) {
      return data.toISOString().slice(0, 10);  
    }
   return data.slice(0,10);
  }

  ngOnInit() {
  }

}
