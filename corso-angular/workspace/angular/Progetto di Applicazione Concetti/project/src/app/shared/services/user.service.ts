import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../model/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
 headers: HttpHeaders = new HttpHeaders();
  
  constructor(private http: HttpClient) { 
    this.headers = this.headers.append('Content-type', 'application/json');
  }

  login(user: User): Observable<User> {
    
   
    return this.http.post<User>('https://reqres.in/api/login', user,
      {
        headers: this.headers
      }
    )
  }

  search(page:number):Observable<any>{

    return this.http.get<any>(`https://reqres.in/api/users?page=${page}`,
      {
        headers: this.headers
      }
    )
  }
}
