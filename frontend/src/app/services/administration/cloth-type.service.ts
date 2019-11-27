import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ClothType } from '../../model/cloth-type.model';
import { API_URL } from '../../app.constants';

@Injectable({
  providedIn: 'root'
})
export class ClothTypeService {

  constructor(
    private http:HttpClient
  ) { }

  retrieveAllClothTypes(page,size,sort) {
    return this.http.get<ClothType[]>(`${API_URL}/clothTypes?page=${page}&size=${size}&sort=${sort}`);
  }

  deleteClothType(clothTypeId){
    return this.http.delete(`${API_URL}/clothTypes/${clothTypeId}`);
  }

  retrieveClothType(clothTypeId){
    return this.http.get<ClothType>(`${API_URL}/clothTypes/${clothTypeId}`);
  }

  updateClothType(clothTypeId, clothType){
    return this.http.put(
          `${API_URL}/clothTypes/${clothTypeId}`
                , clothType);
  }

  createClothType(clothType){
    return this.http.post(
              `${API_URL}/clothTypes`
                , clothType);
  }
}
