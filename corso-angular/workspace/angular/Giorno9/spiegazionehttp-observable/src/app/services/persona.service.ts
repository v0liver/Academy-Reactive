import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable()
export class PersonaService {
  txt: string = 'stringa di prova';
  private _persona$: BehaviorSubject<any> = new BehaviorSubject<any>(null);

  clearTitle(): void {
    this._persona$.next(null);
  }

  updateSubject(idx: number) {
    console.log('idx: ', idx);
    this._persona$.next(idx);
  }

  getSubject() {
    return this._persona$.asObservable();
  }

  constructor() { }
}
