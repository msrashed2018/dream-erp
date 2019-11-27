import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Task } from '../../model/task.model';
import { API_URL } from '../../app.constants';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(
    private http:HttpClient
  ) { }

  retrieveAllTasks(page,size,sort) {
    return this.http.get<Task[]>(`${API_URL}/tasks?page=${page}&size=${size}&sort=${sort}`);
  }

  deleteTask(taskId){
    return this.http.delete(`${API_URL}/tasks/${taskId}`);
  }

  retrieveTask(taskId){
    return this.http.get<Task>(`${API_URL}/tasks/${taskId}`);
  }

  updateTask(taskId, task){
    return this.http.put(
          `${API_URL}/tasks/${taskId}`
                , task);
  }

  createTask(task){
    return this.http.post(
              `${API_URL}/tasks`
                , task);
  }
}
