package com.dream.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dream.models.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {

	
	Page<Store> findByNameContaining(String name, Pageable pageable);
}
