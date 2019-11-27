import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Store } from '../model/store.model';
import { API_URL } from '../app.constants';
import { StoreProduct } from '../model/store-product.model';
import { StoreSales } from '../model/store-sales.model';

@Injectable({
  providedIn: 'root'
})
export class StoreService {

  constructor(
    private http: HttpClient
  ) { }

  retrieveStores(name, page, size, sort) {
    return this.http.get<Store[]>(`${API_URL}/stores?name=${name}&page=${page}&size=${size}&sort=${sort}`);
  }

  deleteStore(storeId) {
    return this.http.delete(`${API_URL}/stores/${storeId}`);
  }

  retrieveStore(storeId) {
    return this.http.get<Store>(`${API_URL}/stores/${storeId}`);
  }

  updateStore(storeId, store) {
    return this.http.put(
      `${API_URL}/stores/${storeId}`
      , store);
  }

  createStore(store) {
    return this.http.post(
      `${API_URL}/stores`
      , store);
  }

  retrieveStoreProducts(storeId, productName, productSize, dateFrom, dateTo, studyLevel, page, size, sort) {
    if (dateFrom != "" && dateTo != "") {
      return this.http.get<StoreProduct[]>(`${API_URL}/stores/${storeId}/products?productName=${productName}&productSize=${productSize}&dateFrom=${dateFrom}&dateTo=${dateTo}&studyLevel=${studyLevel}&page=${page}&size=${size}&sort=${sort}`);
    } else {
      return this.http.get<StoreProduct[]>(`${API_URL}/stores/${storeId}/products?productName=${productName}&productSize=${productSize}&studyLevel=${studyLevel}&page=${page}&size=${size}&sort=${sort}`);
    }

  }

  addStoreProduct(storeId, storeProduct) {
    return this.http.post(
      `${API_URL}/stores/${storeId}/products`
      , storeProduct);
  }

  updateStoreProduct(storeProductId, storeProduct) {
    return this.http.put(
      `${API_URL}/stores/products/${storeProductId}`
      , storeProduct);
  }
  deleteStoreProduct(storeProductId) {
    return this.http.delete(`${API_URL}/stores/products/${storeProductId}`);
  }

  retrieveStoreSales(storeId, productName, productSize, dateFrom, dateTo, studyLevel, page, size, sort) {
    if (dateFrom != "" && dateTo != "") {
      return this.http.get<StoreSales[]>(`${API_URL}/stores/${storeId}/sales?productName=${productName}&productSize=${productSize}&dateFrom=${dateFrom}&dateTo=${dateTo}&studyLevel=${studyLevel}&page=${page}&size=${size}&sort=${sort}`);
    } else {
      return this.http.get<StoreSales[]>(`${API_URL}/stores/${storeId}/sales?productName=${productName}&productSize=${productSize}&studyLevel=${studyLevel}&page=${page}&size=${size}&sort=${sort}`);
    }

  }

  addStoreSales(storeId, storeSales) {
    return this.http.post(
      `${API_URL}/stores/${storeId}/sales`
      , storeSales);
  }

  updateStoreSales(storeSalesId, storeSales) {
    return this.http.put(
      `${API_URL}/stores/sales/${storeSalesId}`
      , storeSales);
  }
  deleteStoreSales(storeSalesId) {
    return this.http.delete(`${API_URL}/stores/sales/${storeSalesId}`);
  }
  
}
