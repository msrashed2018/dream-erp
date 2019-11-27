import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Expense } from '../model/expense.model';
import { API_URL } from '../app.constants';

@Injectable({
  providedIn: 'root'
})
export class ExpenseService {

  constructor(
    private http:HttpClient
  ) { }


  retrieveExpensesTotalPrice(expenseTypeId, dateFrom, dateTo) {
    if (dateFrom != "" && dateTo != "") {
      return this.http.get<number>(`${API_URL}/expenses/totalPrice?expenseTypeId=${expenseTypeId}&dateFrom=${dateFrom}&dateTo=${dateTo}`);
    }else{
      return this.http.get<number>(`${API_URL}/expenses/totalPrice?expenseTypeId=${expenseTypeId}`);
    }
  }

  retrieveExpenses(expenseTypeId, dateFrom, dateTo, page,size,sort) {
    if (dateFrom != "" && dateTo != "") {
      return this.http.get<Expense[]>(`${API_URL}/expenses?expenseTypeId=${expenseTypeId}&dateFrom=${dateFrom}&dateTo=${dateTo}&page=${page}&size=${size}&sort=${sort}`);
    }else{
      return this.http.get<Expense[]>(`${API_URL}/expenses?expenseTypeId=${expenseTypeId}&page=${page}&size=${size}&sort=${sort}`);
    }
  }

  deleteExpense(expenseId){
    return this.http.delete(`${API_URL}/expenses/${expenseId}`);
  }

  retrieveExpense(expenseId){
    return this.http.get<Expense>(`${API_URL}/expenses/${expenseId}`);
  }

  updateExpense(expenseId, expense){
    return this.http.put(
          `${API_URL}/expenses/${expenseId}`
                , expense);
  }

  createExpense(expense){
    return this.http.post(
              `${API_URL}/expenses`
                , expense);
  }
}
