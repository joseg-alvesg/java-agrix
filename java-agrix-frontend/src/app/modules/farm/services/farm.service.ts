import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IFarm } from '../interfaces/farm';

@Injectable({
  providedIn: 'root',
})
export class FarmService {
  constructor(private http: HttpClient) {}

  private getToken() {
    const token = localStorage.getItem('token');
    return token ? JSON.parse(token) : null;
  }

  newFarm(farm: IFarm) {
    return this.http.post('http://localhost:8080/farms', farm, {
      headers: { Authorization: this.getToken().token },
    });
  }

  getFarms() {
    return this.http.get<any>('http://localhost:8080/farms');
  }
}
