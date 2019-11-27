import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Bill } from '../model/bill.model';
import { API_URL } from '../app.constants';

@Injectable({
  providedIn: 'root'
})
export class BillService {

  constructor(
    private http: HttpClient
  ) { }

  retrieveBills(schoolId, dateFrom, dateTo, to, page, size, sort) {
    if (dateFrom != "" && dateTo != "") {
      return this.http.get<Bill[]>(`${API_URL}/bills?schoolId=${schoolId}&dateFrom=${dateFrom}&dateTo=${dateTo}&to=${to}&page=${page}&size=${size}&sort=${sort}`);
    } else {
      return this.http.get<Bill[]>(`${API_URL}/bills?schoolId=${schoolId}&to=${to}&page=${page}&size=${size}&sort=${sort}`);
    }
  }

  deleteBill(billId) {
    return this.http.delete(`${API_URL}/bills/${billId}`);
  }

  retrieveBill(billId) {
    return this.http.get<Bill>(`${API_URL}/bills/${billId}`);
  }

  updateBill(billId, bill) {
    return this.http.put(
      `${API_URL}/bills/${billId}`
      , bill);
  }

  createBill(bill) {
    return this.http.post(
      `${API_URL}/bills`
      , bill);
  }


  retrieveBillProducts(billId, productName, productSize, studyLevel, page, size, sort) {
      return this.http.get<Bill[]>(`${API_URL}/bills/${billId}/products?productName=${productName}&productSize=${productSize}&studyLevel=${studyLevel}&page=${page}&size=${size}&sort=${sort}`);
  }

  addBillProduct(billId, billProduct) {
    return this.http.post(
      `${API_URL}/bills/${billId}/products`
      , billProduct);
  }

  updateBillProduct(billProductId, billProduct) {
    return this.http.put(
      `${API_URL}/bills/products/${billProductId}`
      , billProduct);
  }
  deleteBillProduct(billProductId) {
    return this.http.delete(`${API_URL}/bills/products/${billProductId}`);
  }
}
