import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { School } from '../model/school.model';
import { API_URL } from '../app.constants';
import { SchoolProduct } from '../model/school-product.model';
import { SchoolSales } from '../model/school-sales.model';

@Injectable({
  providedIn: 'root'
})
export class SchoolService {

  constructor(
    private http: HttpClient
  ) { }

  retrieveSchools(name, page, size, sort) {
    return this.http.get<School[]>(`${API_URL}/schools?name=${name}&page=${page}&size=${size}&sort=${sort}`);
  }

  deleteSchool(schoolId) {
    return this.http.delete(`${API_URL}/schools/${schoolId}`);
  }

  retrieveSchool(schoolId) {
    return this.http.get<School>(`${API_URL}/schools/${schoolId}`);
  }

  updateSchool(schoolId, school) {
    return this.http.put(
      `${API_URL}/schools/${schoolId}`
      , school);
  }

  createSchool(school) {
    return this.http.post(
      `${API_URL}/schools`
      , school);
  }

  retrieveSchoolProducts(schoolId, productName, productSize, dateFrom, dateTo, studyLevel, page, size, sort) {
    if (dateFrom != "" && dateTo != "") {
      return this.http.get<SchoolProduct[]>(`${API_URL}/schools/${schoolId}/products?productName=${productName}&productSize=${productSize}&dateFrom=${dateFrom}&dateTo=${dateTo}&studyLevel=${studyLevel}&page=${page}&size=${size}&sort=${sort}`);
    } else {
      return this.http.get<SchoolProduct[]>(`${API_URL}/schools/${schoolId}/products?productName=${productName}&productSize=${productSize}&studyLevel=${studyLevel}&page=${page}&size=${size}&sort=${sort}`);
    }

  }

  getSchoolProductsTotalPrice(schoolId, productName, productSize, dateFrom, dateTo, studyLevel) {

    if (dateFrom != "" && dateTo != "") {
      return this.http.get<number>(`${API_URL}/schools/${schoolId}/products/totalPrice?productName=${productName}&productSize=${productSize}&dateFrom=${dateFrom}&dateTo=${dateTo}&studyLevel=${studyLevel}`);
    } else {
      return this.http.get<number>(`${API_URL}/schools/${schoolId}/products/totalPrice?productName=${productName}&productSize=${productSize}&studyLevel=${studyLevel}`);
    }
  }

  getSchoolSalesTotalPrice(schoolId, productName, productSize, dateFrom, dateTo, studyLevel) {

    if (dateFrom != "" && dateTo != "") {
      return this.http.get<number>(`${API_URL}/schools/${schoolId}/sales/totalPrice?productName=${productName}&productSize=${productSize}&dateFrom=${dateFrom}&dateTo=${dateTo}&studyLevel=${studyLevel}`);
    } else {
      return this.http.get<number>(`${API_URL}/schools/${schoolId}/sales/totalPrice?productName=${productName}&productSize=${productSize}&studyLevel=${studyLevel}`);
    }
  }

  addSchoolProduct(schoolId, schoolProduct) {
    return this.http.post(
      `${API_URL}/schools/${schoolId}/products`
      , schoolProduct);
  }

  updateSchoolProduct(schoolProductId, schoolProduct) {
    return this.http.put(
      `${API_URL}/schools/products/${schoolProductId}`
      , schoolProduct);
  }
  deleteSchoolProduct(schoolProductId) {
    return this.http.delete(`${API_URL}/schools/products/${schoolProductId}`);
  }

  retrieveSchoolSales(schoolId, productName, productSize, dateFrom, dateTo, studyLevel, page, size, sort) {
    if (dateFrom != "" && dateTo != "") {
      return this.http.get<SchoolSales[]>(`${API_URL}/schools/${schoolId}/sales?productName=${productName}&productSize=${productSize}&dateFrom=${dateFrom}&dateTo=${dateTo}&studyLevel=${studyLevel}&page=${page}&size=${size}&sort=${sort}`);
    } else {
      return this.http.get<SchoolSales[]>(`${API_URL}/schools/${schoolId}/sales?productName=${productName}&productSize=${productSize}&studyLevel=${studyLevel}&page=${page}&size=${size}&sort=${sort}`);
    }

  }

  addSchoolSales(schoolId, schoolSales) {
    return this.http.post(
      `${API_URL}/schools/${schoolId}/sales`
      , schoolSales);
  }

  updateSchoolSales(schoolSalesId, schoolSales) {
    return this.http.put(
      `${API_URL}/schools/sales/${schoolSalesId}`
      , schoolSales);
  }
  deleteSchoolSales(schoolSalesId) {
    return this.http.delete(`${API_URL}/schools/sales/${schoolSalesId}`);
  }
  
}
