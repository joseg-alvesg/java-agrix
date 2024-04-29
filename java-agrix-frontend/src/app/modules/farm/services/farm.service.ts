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
    return this.http.post('http://localhost:8080/farms', farm);
  }

  getFarms() {
    return this.http.get<any>('http://localhost:8080/farms');
  }

  getFarmById(id: any) {
    return this.http.get<any>('http://localhost:8080/farms/' + id);
  }

  getCropsByFarmId(id: any) {
    return this.http.get<any>('http://localhost:8080/farms/' + id + '/crops');
  }

  newCrop(id: any, crop: any) {
    return this.http.post(`http://localhost:8080/farms/${id}/crops`, crop);
  }

  getFertilizersByCropId(id: any) {
    return this.http.get<any>(`http://localhost:8080/crops/${id}/fertilizers`);
  }
}
