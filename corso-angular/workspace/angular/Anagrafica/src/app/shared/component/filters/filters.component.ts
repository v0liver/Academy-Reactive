import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {provideNativeDateAdapter} from '@angular/material/core';
import { MatAccordion } from '@angular/material/expansion';

@Component({
  selector: 'app-filters',
  templateUrl: './filters.component.html',
  styleUrls: ['./filters.component.scss'],
  providers: [provideNativeDateAdapter()]
})
export class FiltersComponent implements OnInit {
  @ViewChild(MatAccordion) accordion?: MatAccordion;

  filterForm = new FormGroup({
    nome: new FormControl<string>('',[Validators.minLength(3)]),
    cognome: new FormControl<string>('',[Validators.minLength(3)]),
    email: new FormControl<string>('',[Validators.minLength(3)]),
    start: new FormControl<Date | null>(null),
    end: new FormControl<Date | null>(null),
  });


  constructor() { }

  ngOnInit() {
  }

}
