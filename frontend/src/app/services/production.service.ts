import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Production } from '../model/production.model';
import { API_URL } from '../app.constants';
import { ProductionHistory } from '../model/production-history.model';

@Injectable({
  providedIn: 'root'
})
export class ProductionService {

  constructor(
    private http:HttpClient
  ) { }

  retrieveProductions(productName, productSize ,page,size,sort) {
      return this.http.get<Production[]>(`${API_URL}/productions?productName=${productName}&productSize=${productSize}&page=${page}&size=${size}&sort=${sort}`);
  }

  retrieveProductionsHistory(productName, productSize , dateFrom, dateTo,page,size,sort) {
    if (dateFrom != "" && dateTo != "") {
      return this.http.get<ProductionHistory[]>(`${API_URL}/productions?productName=${productName}&productSize=${productSize}&dateFrom=${dateFrom}&dateTo=${dateTo}&page=${page}&size=${size}&sort=${sort}`);
    }else{
      return this.http.get<ProductionHistory[]>(`${API_URL}/productions?productName=${productName}&productSize=${productSize}&page=${page}&size=${size}&sort=${sort}`);
    }
  }

  deleteProduction(productionId){
    return this.http.delete(`${API_URL}/productions/${productionId}`);
  }

  retrieveProduction(productionId){
    return this.http.get<Production>(`${API_URL}/productions/${productionId}`);
  }

  // updateProduction(productionId, production){
  //   return this.http.put(
  //         `${API_URL}/productions/${productionId}`
  //               , production);
  // }

  createProduction(production){
    return this.http.post(
              `${API_URL}/productions`
                , production);
  }
}
