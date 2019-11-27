package com.dream.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dream.models.Task;
import com.dream.services.TaskService;

@CrossOrigin(origins = "*")
@RestController
public class TaskController {
	@Autowired
	private TaskService taskService;

	@GetMapping("/tasks")
	public Page<Task> retrieveAllTasks(Pageable pageable) {
		return taskService.getTasks(pageable);
	}

	@GetMapping("/tasks/{taskId}")
	public Task retrieveTaskById(@PathVariable long taskId) {
		return taskService.getTaskById(taskId);
	}

	@DeleteMapping("/tasks/{taskId}")
	public void deleteTask(@PathVariable long taskId) {
		taskService.deleteTask(taskId);
	}

	@PostMapping("/tasks")
	public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {

		Task createdTask = taskService.createTask(task);
		return new ResponseEntity<Task>(createdTask, HttpStatus.OK);

	}

	@PutMapping("/tasks/{taskId}")
	public ResponseEntity<Task> updateTask(@PathVariable int taskId, @Valid @RequestBody Task task) {
		Task updatedTask = taskService.updateTask(taskId, task);
		return new ResponseEntity<Task>(updatedTask, HttpStatus.OK);
	}
}
