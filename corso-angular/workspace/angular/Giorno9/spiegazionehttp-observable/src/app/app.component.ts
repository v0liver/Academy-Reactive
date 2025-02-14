import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'title sito web';


  onClickPulsantePluto(datiDiInput: any): void {
    console.log(typeof datiDiInput);
    console.log('ciao, è stato clickato pluto, valore: ', datiDiInput);
  }
}
