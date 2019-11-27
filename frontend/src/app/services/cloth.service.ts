import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Cloth } from '../model/cloth.model';
import { API_URL } from '../app.constants';

@Injectable({
  providedIn: 'root'
})
export class ClothService {

  constructor(
    private http:HttpClient
  ) { }

  retrieveCloths(clothTypeId, dateFrom, dateTo,colorId, page,size,sort) {
    if (dateFrom != "" && dateTo != "") {
      return this.http.get<Cloth[]>(`${API_URL}/cloths?clothTypeId=${clothTypeId}&dateFrom=${dateFrom}&dateTo=${dateTo}&colorId=${colorId}&page=${page}&size=${size}&sort=${sort}`);
    }else{
      return this.http.get<Cloth[]>(`${API_URL}/cloths?clothTypeId=${clothTypeId}&colorId=${colorId}&page=${page}&size=${size}&sort=${sort}`);
    }
  }

  deleteCloth(clothId){
    return this.http.delete(`${API_URL}/cloths/${clothId}`);
  }

  retrieveCloth(clothId){
    return this.http.get<Cloth>(`${API_URL}/cloths/${clothId}`);
  }

  updateCloth(clothId, cloth){
    return this.http.put(
          `${API_URL}/cloths/${clothId}`
                , cloth);
  }

  createCloth(cloth){
    return this.http.post(
              `${API_URL}/cloths`
                , cloth);
  }
}
