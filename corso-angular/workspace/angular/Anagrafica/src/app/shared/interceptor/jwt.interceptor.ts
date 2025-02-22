import { HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { finalize, Observable } from "rxjs";


@Injectable()
export class JwInterceptor implements HttpInterceptor{
    

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let accessToken: string | null = sessionStorage.getItem('token');
        let newRequest = req;
        if (accessToken) {
          newRequest = req.clone({
            headers: req.headers.append('Authorization', 'Bearer ' + accessToken),
          });
        }
        return next.handle(newRequest);
    }
        
 
}