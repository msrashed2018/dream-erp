package com.dream.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dream.models.School;

public interface SchoolRepository extends JpaRepository<School, Long> {

	Page<School> findByNameContaining(String nameConatining, Pageable pageable);
}
