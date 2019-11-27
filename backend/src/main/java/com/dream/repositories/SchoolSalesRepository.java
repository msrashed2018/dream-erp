package com.dream.repositories;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dream.models.SchoolSales;

public interface SchoolSalesRepository extends JpaRepository<SchoolSales, Long> {
	Page<SchoolSales> findBySchoolIdAndProductSizeSizeAndProductSizeProductNameContaining(long schoolId,
			int size, String productName, Pageable pageable);

	Page<SchoolSales> findBySchoolIdAndProductSizeProductNameContaining(long schoolId, String productName,
			Pageable pageable);

	Page<SchoolSales> findBySchoolIdAndProductSizeProductNameContainingAndRegisteredDateBetween(long schoolId,
			String productName, Date registeredDateFrom, Date registeredDateTo, Pageable pageable);

	Page<SchoolSales> findBySchoolIdAndProductSizeSizeAndProductSizeProductNameContainingAndRegisteredDateBetween(
			long schoolId, int size, String productName, Date registeredDateFrom, Date registeredDateTo,
			Pageable pageable);

	@Query(value = "SELECT sum(s.amount * s.specialPrice) FROM SchoolSales s Where s.school.id=:schoolId AND s.productSize.size=:size AND s.productSize.product.name LIKE CONCAT('%', :productName, '%') ")
	public Long totalPriceBySchoolIdAndSizeAndProductNameContaining(long schoolId, int size, String productName);

	@Query(value = "SELECT sum(s.amount * s.specialPrice) FROM SchoolSales s Where s.school.id=:schoolId AND s.productSize.product.name LIKE CONCAT('%', :productName, '%')  ")
	public Long totalPriceBySchoolIdAndProductSizeProductNameContaining(long schoolId, String productName);

	@Query(value = "SELECT sum(s.amount * s.specialPrice) FROM SchoolSales s Where s.school.id=:schoolId AND s.productSize.size=:size AND s.productSize.product.name LIKE CONCAT('%', :productName, '%')   AND s.registeredDate > :registeredDateFrom AND  s.registeredDate < :registeredDateTo")
	public Long totalPriceBySchoolIdAndProductSizeSizeAndProductSizeProductNameContainingAndRegisteredDateBetween(
			long schoolId, int size, String productName, Date registeredDateFrom,
			Date registeredDateTo);

	@Query(value = "SELECT sum(s.amount * s.specialPrice) FROM SchoolSales s Where s.school.id=:schoolId AND s.productSize.product.name LIKE CONCAT('%', :productName, '%')  AND s.registeredDate > :registeredDateFrom AND  s.registeredDate < :registeredDateTo")
	public Long totalPriceBySchoolIdAndProductSizeProductNameContainingAndRegisteredDateBetween(long schoolId, String productName,
			Date registeredDateFrom, Date registeredDateTo);
}
