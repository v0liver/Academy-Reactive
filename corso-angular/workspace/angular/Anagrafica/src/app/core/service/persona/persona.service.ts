import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Persona } from '../../../shared/model/persona';
import { SearchPersona } from '../../../shared/model/search-persona';

@Injectable({
  providedIn: 'root'
})
export class PersonaService {
  private readonly _URL: string = 'http://localhost:8080/persone';

  constructor(private http: HttpClient) { }

  // getUser2(nome?: string, cognome?: string, citta?: string, dataNascitaMin?: string, dataNascitaMax?: string,): Observable<any> {
  //   let headers: HttpHeaders = new HttpHeaders;
  //   headers = headers.append('Authorization', 'Bearer' + sessionStorage.getItem('token'));

  //   let params: HttpParams = new HttpParams();
  //   if (!!nome) {
  //     params = params.append('nome', nome);
  //   } if (!!cognome) {
  //     params = params.append('cognome', cognome);
  //   } if (!!citta) {
  //     params = params.append('citta', citta);
  //   } if (!!dataNascitaMin) {
  //     params = params.append('dataNascitaMin', dataNascitaMin);
  //   } if (!!dataNascitaMax) {
  //     params = params.append('dataNascitaMax', dataNascitaMax);
  //   }
  //   return this.http.get<any>(this._URL,{
  //     headers:headers
  //   })
  // } 

  getUser(searchPersona?: SearchPersona): Observable<any> {
    let headers: HttpHeaders = new HttpHeaders;
    headers = headers.append('Authorization', 'Bearer' + sessionStorage.getItem('token'));
    let params: HttpParams = new HttpParams();
    if (!!searchPersona) {
      for (let key in searchPersona) {
        const formattedKey = key.replace(/^_/, '');
        params = params.append(formattedKey, (searchPersona as any)[key])
      }
    }
    return this.http.get<any>(this._URL, { params })
  }

  edit(persona: Persona, id: number): Observable<any> {
    const personaModificata: any = {};
    let params: HttpParams = new HttpParams();
    for (const key in persona) {
      const newKey = key.replace(/^_/, ''); // Rimuove "_" solo all'inizio del nome della proprietà
      personaModificata[newKey] = (persona as any)[key];
    }
    return this.http.put<any>(this._URL + '/' + id, personaModificata);

  }

  delete(persona:Persona): Observable<any>{
    const personaModificata: any = {};
    let params: HttpParams = new HttpParams();
    for (const key in persona) {
      const newKey = key.replace(/^_/, ''); // Rimuove "_" solo all'inizio del nome della proprietà
      personaModificata[newKey] = (persona as any)[key];
    }
    return this.http.delete<any>(this._URL + '/' + persona.id, personaModificata);
  }

  crea(persona:Persona): Observable<any>{
    const personaModificata: any = {};
    let params: HttpParams = new HttpParams();
    for (const key in persona) {
      const newKey = key.replace(/^_/, ''); // Rimuove "_" solo all'inizio del nome della proprietà
      personaModificata[newKey] = (persona as any)[key];
    }
    return this.http.post<any>(this._URL, personaModificata);

  }

}
