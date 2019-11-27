package com.dream.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dream.models.Occupation;

public interface OccupationRepository extends JpaRepository<Occupation, Long> {

}
