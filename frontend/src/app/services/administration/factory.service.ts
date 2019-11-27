import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Factory } from '../../model/factory.model';
import { API_URL } from '../../app.constants';

@Injectable({
  providedIn: 'root'
})
export class FactoryService {

  constructor(
    private http:HttpClient
  ) { }

  retrieveAllFactories(page,size,sort) {
    return this.http.get<Factory[]>(`${API_URL}/factories?page=${page}&size=${size}&sort=${sort}`);
  }

  deleteFactory(factoryId){
    return this.http.delete(`${API_URL}/factories/${factoryId}`);
  }

  retrieveFactory(factoryId){
    return this.http.get<Factory>(`${API_URL}/factories/${factoryId}`);
  }

  updateFactory(factoryId, factory){
    return this.http.put(
          `${API_URL}/factories/${factoryId}`
                , factory);
  }

  createFactory(factory){
    return this.http.post(
              `${API_URL}/factories`
                , factory);
  }
}
