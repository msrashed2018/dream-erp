package com.dream.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dream.models.ProductSize;

public interface ProductSizeRepository extends JpaRepository<ProductSize, Long> {

	List<ProductSize> findByProductId(long productId);

	ProductSize findByProductIdAndSize(long id, int size);

	boolean existsByProductIdAndSize(long productId, int size);

}
