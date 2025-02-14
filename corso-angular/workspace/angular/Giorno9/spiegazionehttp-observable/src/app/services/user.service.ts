import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { GenericResponseSingle } from '../model/generic-response-single';
import { catchError, Observable, switchMap, tap } from 'rxjs';
import { GenericResponseList } from '../model/generic-response-list';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) { }


  findById(id: number): Observable<GenericResponseSingle> {
    return this.http.get<GenericResponseSingle>('https://reqres.in/api/users/'+id);
  }

  
  search(nome: string, cognome: string, eta: number): Observable<GenericResponseList> {
    let headers: HttpHeaders = new HttpHeaders();
    headers = headers.append('Content-type','application/json');
    
    
    let params: HttpParams = new HttpParams();

    if(!!nome) params = params.append('nome',nome);
    if(!!cognome) params = params.append('cognome',cognome);
    if(!!eta) params = params.append('eta',eta);


    //tap, switchMap, takeUntil, combineLatest, forkJoin


    return this.http.get<GenericResponseList>('https://reqres.in/api/users',
      {
        headers: headers,
        params: params
      }
    );
  }


//viene restituito al chiamante SOLO la parte interna, findById(2)
  findCustom(): Observable<any> {
    return this.findById(1).pipe(
      
      tap(resFindById1 => {
        console.log(resFindById1);
      }),
      
      switchMap(resFindById1PostTap => {
        return this.findById(2);
      }),

      /*catchError((error) => {
        console.error(err);
      })*/
    );
  }
}
