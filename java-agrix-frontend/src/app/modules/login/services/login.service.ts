import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ILogin, IRegister } from '../interfaces/login';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  constructor(private http: HttpClient) {}

  login(login: ILogin): Observable<any> {
    return this.http.post<any>('http://localhost:8080/auth/login', login);
  }

  register(login: IRegister): Observable<any> {
    return this.http.post<any>('http://localhost:8080/persons', login);
  }
}
