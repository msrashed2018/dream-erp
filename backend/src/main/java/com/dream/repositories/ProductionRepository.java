package com.dream.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dream.models.Production;

public interface ProductionRepository extends JpaRepository<Production, Long> {

	Page<Production> findByProductSizeProductNameContaining(
			 String productName, Pageable pageable);
	
	Page<Production> findByProductSizeProductNameContainingAndProductSizeSize(
			 String productName,int size, Pageable pageable);
	
	
	boolean existsByProductSizeId(long productSizeId);

	Optional<Production> findByProductSizeId(Long productSizeId);
}
