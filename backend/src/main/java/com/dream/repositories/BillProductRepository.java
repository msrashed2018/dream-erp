package com.dream.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dream.models.BillProduct;

public interface BillProductRepository extends JpaRepository<BillProduct, Long> {

	Page<BillProduct> findByBillIdAndProductSizeProductNameContainingAndProductSizeSize(long billId,
			String productNameContaining, int productSize, Pageable pageable);

	Page<BillProduct> findByBillIdAndProductSizeProductNameContaining(long billId,
			String productNameContaining, Pageable pageable);

}
