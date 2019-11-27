package com.dream.repositories;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dream.models.StoreSales;

public interface StoreSalesRepository extends JpaRepository<StoreSales, Long> {

	Page<StoreSales> findByStoreIdAndProductSizeSizeAndProductSizeProductNameContainingAndStudyLevelContaining(long storeId, int size,
			String name, String studyLevel, Pageable pageable);

	Page<StoreSales> findByStoreIdAndProductSizeProductNameContainingAndStudyLevelContaining(long storeId, int size, String name,
			String studyLevel, Pageable pageable);

	Page<StoreSales> findByStoreIdAndProductSizeProductNameContainingAndRegisteredDateBetweenAndStudyLevelContaining(long storeId,
			int size, String name, Date registeredDateFrom, Date registeredDateTo, String studyLevel,
			Pageable pageable);

	Page<StoreSales> findByStoreIdAndProductSizeSizeAndProductSizeProductNameContainingAndRegisteredDateBetweenAndStudyLevelContaining(
			long storeId, int size, String name, Date registeredDateFrom, Date registeredDateTo, String studyLevel,
			Pageable pageable);
}
