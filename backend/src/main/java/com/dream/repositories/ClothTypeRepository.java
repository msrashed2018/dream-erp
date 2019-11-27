package com.dream.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dream.models.ClothType;

public interface ClothTypeRepository extends JpaRepository<ClothType, Long> {

}
