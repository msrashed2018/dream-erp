package com.dream.repositories;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dream.models.StoreProductHistory;

public interface StoreProductHistoryRepository extends JpaRepository<StoreProductHistory, Long> {
	
	Page<StoreProductHistory> findByStoreIdAndProductSizeSizeAndProductSizeProductNameContainingAndStudyLevelContaining(long storeId, int size, String name,
			String studyLevel, Pageable pageable);
	
	Page<StoreProductHistory> findByStoreIdAndProductSizeProductNameContainingAndStudyLevelContaining(long storeId, int size, String name,
			String studyLevel, Pageable pageable);
	
	
	Page<StoreProductHistory> findByStoreIdAndProductSizeProductNameContainingAndRegisteredDateBetweenAndStudyLevelContaining(long storeId, int size, String name, Date registeredDateFrom, Date registeredDateTo,
			String studyLevel, Pageable pageable);
	
	Page<StoreProductHistory> findByStoreIdAndProductSizeSizeAndProductSizeProductNameContainingAndRegisteredDateBetweenAndStudyLevelContaining(long storeId, int size, String name, Date registeredDateFrom, Date registeredDateTo,
			String studyLevel, Pageable pageable);
}
