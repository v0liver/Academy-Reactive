import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {
  @Input() nomePersona: string = '';
   id: number;

  constructor(private router: Router,) {
    this.id = 10;

  }

  @Output() outputString: EventEmitter<string> = new EventEmitter<string>;


  onClickpassaStringa(): void {
    this.outputString.emit('stringa di uscita');

  }
}
