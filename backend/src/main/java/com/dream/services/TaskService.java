package com.dream.services;

import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;

import com.dream.models.Task;

public interface TaskService {
	
	Page<Task> getTasks(Pageable pageable);
	
	Task getTaskById(long id);
	
	Task createTask(Task Task);
	
	Task updateTask(long id, Task Task);
	
	void deleteTask(long id);
}
