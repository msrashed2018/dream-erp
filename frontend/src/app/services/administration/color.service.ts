import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Color } from '../../model/color.model';
import { API_URL } from '../../app.constants';

@Injectable({
  providedIn: 'root'
})
export class ColorService {

  constructor(
    private http:HttpClient
  ) { }

  retrieveAllColors(page,size,sort) {
    return this.http.get<Color[]>(`${API_URL}/colors?page=${page}&size=${size}&sort=${sort}`);
  }

  deleteColor(colorId){
    return this.http.delete(`${API_URL}/colors/${colorId}`);
  }

  retrieveColor(colorId){
    return this.http.get<Color>(`${API_URL}/colors/${colorId}`);
  }

  updateColor(colorId, color){
    return this.http.put(
          `${API_URL}/colors/${colorId}`
                , color);
  }

  createColor(color){
    return this.http.post(
              `${API_URL}/colors`
                , color);
  }
}
