import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Employee } from '../model/employee.model';
import { API_URL } from '../app.constants';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(
    private http:HttpClient
  ) { }

  retrieveEmployees(occupationId, salaryType, name, page,size,sort) {
    if(salaryType != null){
      return this.http.get<Employee[]>(`${API_URL}/employees?occupationId=${occupationId}&salaryType=${salaryType}&name=${name}&page=${page}&size=${size}&sort=${sort}`);
    }else{
      return this.http.get<Employee[]>(`${API_URL}/employees?occupationId=${occupationId}&name=${name}&page=${page}&size=${size}&sort=${sort}`);
    }
  }

  deleteEmployee(employeeId){
    return this.http.delete(`${API_URL}/employees/${employeeId}`);
  }

  retrieveEmployee(employeeId){
    return this.http.get<Employee>(`${API_URL}/employees/${employeeId}`);
  }

  updateEmployee(employeeId, employee){
    return this.http.put(
          `${API_URL}/employees/${employeeId}`
                , employee);
  }

  createEmployee(employee){
    return this.http.post(
              `${API_URL}/employees`
                , employee);
  }

  retrieveEmployeeTasks(employeeId, dateFrom, dateTo, page,size,sort) {
    if (dateFrom != "" && dateTo != "") {
      return this.http.get<Employee[]>(`${API_URL}/employees/tasks?employeeId=${employeeId}&dateFrom=${dateFrom}&dateTo=${dateTo}&page=${page}&size=${size}&sort=${sort}`);
    }else{
      return this.http.get<Employee[]>(`${API_URL}/employees/tasks?employeeId=${employeeId}&page=${page}&size=${size}&sort=${sort}`);
    }
  }

  addEmployeeTask(employeeId, employeeTask){
    return this.http.post(
              `${API_URL}/employees/${employeeId}/tasks`
                , employeeTask);
  }

  updateEmployeeTask(employeeTaskId, employeeTask){
    return this.http.put(
          `${API_URL}/employees/tasks/${employeeTaskId}`
                , employeeTask);
  }
  deleteEmployeeTask(employeeTaskId){
    return this.http.delete(`${API_URL}/employees/tasks/${employeeTaskId}`);
  }
}
