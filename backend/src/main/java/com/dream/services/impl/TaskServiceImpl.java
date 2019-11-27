/**
 * 
 */
package com.dream.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.dream.models.Task;
import com.dream.repositories.TaskRepository;
import com.dream.services.TaskService;

/**
 * @author mohamedsalah
 *
 */
@Service
public class TaskServiceImpl implements TaskService {
	@Autowired
	private TaskRepository taskRepository;

	@Override
	public Page<Task> getTasks(Pageable pageable) {
		return taskRepository.findAll(pageable);
	}

	@Override
	public Task getTaskById(long id) {
		Optional<Task> task = taskRepository.findById(id);
		if (!task.isPresent()) {
			throw new ResourceNotFoundException("TaskId " + id + " not found");
		}
		return task.get();
	}

	@Override
	public Task createTask(Task task) {
		return taskRepository.save(task);
	}

	@Override
	public Task updateTask(long id, Task task) {
		Optional<Task> existingTask = taskRepository.findById(id);
		if (!existingTask.isPresent()) {
			throw new ResourceNotFoundException("TaskId " + id + " not found");
		}
		existingTask.get().setName(task.getName());
		existingTask.get().setNotes(task.getNotes());
		return taskRepository.save(existingTask.get());
	}

	@Override
	public void deleteTask(long id) {
		if (!taskRepository.existsById(id)) {
			throw new ResourceNotFoundException("TaskId " + id + " not found");
		}
		taskRepository.deleteById(id);

	}

}
