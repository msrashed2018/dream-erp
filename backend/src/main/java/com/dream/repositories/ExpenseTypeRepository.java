package com.dream.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dream.models.ExpenseType;

public interface ExpenseTypeRepository extends JpaRepository<ExpenseType, Long> {

}
