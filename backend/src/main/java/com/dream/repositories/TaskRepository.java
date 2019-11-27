package com.dream.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dream.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
