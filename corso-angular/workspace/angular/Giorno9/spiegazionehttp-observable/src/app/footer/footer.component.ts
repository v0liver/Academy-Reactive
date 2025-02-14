import { Component, OnInit } from '@angular/core';
import { PersonaService } from '../services/persona.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.css'
})
export class FooterComponent implements OnInit {

  constructor(private personaService: PersonaService){}
  
  ngOnInit(): void {
    console.log('valore nel footer: ', this.personaService.txt);
  }
}
