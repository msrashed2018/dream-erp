package com.dream.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dream.models.Color;

public interface ColorRepository extends JpaRepository<Color, Long> {

}
