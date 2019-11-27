package com.dream.repositories;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dream.models.ProductionHistory;

public interface ProductionHistoryRepository extends JpaRepository<ProductionHistory, Long> {
	
	Page<ProductionHistory> findByProductSizeProductNameContainingAndProductSizeSize(
			 String productName,int size, Pageable pageable);

	Page<ProductionHistory> findByProductSizeProductNameContainingAndProductSizeSizeAndRegisteredDateBetween(String productName,
			int productSize, Date from, Date to, Pageable page);

	Page<ProductionHistory> findByProductSizeSize(int productSize, Pageable page);

	Page<ProductionHistory> findByProductSizeProductNameContainingAndRegisteredDateBetween(String productName,
			Date from, Date to, Pageable page);

}
