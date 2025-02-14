import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { PersonaService } from '../services/persona.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit{
  @Input() pippo: string = '';
  @Output() pluto: EventEmitter<any> = new EventEmitter<any>();

  constructor(private personaService: PersonaService, private router: Router){}

  ngOnInit(): void {
    
    console.log('txt pre modifica: ', this.personaService.txt);
    this.personaService.txt = 'stringa modificata';
    console.log('txt post modifica: ', this.personaService.txt);
  }

  onClickPulsantePluto(): void {
    this.pluto.emit({
      a: 'valore di prova',
      b: 'valore b',
      c: 112,
      d: new Date()
    });
  }


  onClickHomepage(): void {
    this.router.navigate(['/'], {queryParams: {param1: this.pippo}});
  }

}
