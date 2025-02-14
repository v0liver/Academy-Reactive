import { Component, OnInit } from '@angular/core';
import { Persona } from './model/persona';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  private persona: Persona = new Persona();
  title = 'hello-world-nostandalone';
  nome: string = '';
  constructor() {
  }
  ngOnInit(): void {
    this.persona.nome = 'Vito';
    this.nome = this.persona.nome;
  }

  RecuperaStringOutput(stringaOutput: string): void {
    alert(stringaOutput);
  } 
}
