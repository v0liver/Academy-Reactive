import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly _URL: string = 'http://localhost:8080/auth/';

  constructor(private http: HttpClient) {
  }

  login(username: string, password: string): Observable<any> {
    return this.http.post<any>(this._URL + 'accedi', { username, password })
  }

  register(username: string, password: string): Observable<any> {
    return this.http.post<any>(this._URL + 'registrati', { username, password })
  }



}
