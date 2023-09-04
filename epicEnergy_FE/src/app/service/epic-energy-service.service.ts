import { Injectable } from '@angular/core';
import { Cliente } from '../model/clienti.interface';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EpicEnergyServiceService {

  baseUrl = environment.baseURL;
  constructor(private http: HttpClient) {}

  getAllClienti() {
    return this.http.get<Cliente[]>(`${this.baseUrl}clienti`);
  }
}
