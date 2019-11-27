import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ExpenseType } from '../../model/expense-type.model';
import { API_URL } from '../../app.constants';

@Injectable({
  providedIn: 'root'
})
export class ExpenseTypeService {

  constructor(
    private http: HttpClient
  ) { }

  retrieveAllExpenseTypes() {
    return this.http.get<ExpenseType[]>(`${API_URL}/expenseTypes/all`);
  }
  retrieveExpenseTypes(page, size, sort) {
    return this.http.get<ExpenseType[]>(`${API_URL}/expenseTypes?page=${page}&size=${size}&sort=${sort}`);
  }

  deleteExpenseType(expenseTypeId) {
    return this.http.delete(`${API_URL}/expenseTypes/${expenseTypeId}`);
  }

  retrieveExpenseType(expenseTypeId) {
    return this.http.get<ExpenseType>(`${API_URL}/expenseTypes/${expenseTypeId}`);
  }

  updateExpenseType(expenseTypeId, expenseType) {
    return this.http.put(
      `${API_URL}/expenseTypes/${expenseTypeId}`
      , expenseType);
  }

  createExpenseType(expenseType) {
    return this.http.post(
      `${API_URL}/expenseTypes`
      , expenseType);
  }
}
