package com.dream.repositories;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dream.models.SchoolProduct;
import com.dream.models.SchoolProductHistory;

public interface SchoolProductHistoryRepository extends JpaRepository<SchoolProductHistory, Long> {
	Page<SchoolProductHistory> findBySchoolIdAndProductSizeSizeAndProductSizeProductNameContainingAndStudyLevelContaining(long schoolId, int size,
			String productName, String studyLevel, Pageable pageable);

	Page<SchoolProductHistory> findBySchoolIdAndProductSizeProductNameContainingAndStudyLevelContaining(long schoolId, String name,
			String studyLevel, Pageable pageable);

	Page<SchoolProductHistory> findBySchoolIdAndProductSizeProductNameContainingAndRegisteredDateBetweenAndStudyLevelContaining(
			long schoolId, String productName, Date registeredDateFrom, Date registeredDateTo, String studyLevel,
			Pageable pageable);

	Page<SchoolProductHistory> findBySchoolIdAndProductSizeSizeAndProductSizeProductNameContainingAndRegisteredDateBetweenAndStudyLevelContaining(
			long storeId, int size, String productName, Date registeredDateFrom, Date registeredDateTo,
			String studyLevel, Pageable pageable);

	@Query(value = "SELECT sum(s.amount * s.specialPrice) FROM SchoolProductHistory s Where s.school.id=:schoolId AND s.productSize.size=:size AND s.productSize.product.name LIKE CONCAT('%', :productName, '%')  AND s.studyLevel LIKE CONCAT('%', :studyLevel, '%')")
	public Long totalPriceBySchoolIdAndSizeAndProductNameContainingAndStudyLevelContaining(long schoolId, int size,
			String productName, String studyLevel);

	@Query(value = "SELECT sum(s.amount * s.specialPrice) FROM SchoolProductHistory s Where s.school.id=:schoolId AND s.productSize.product.name LIKE CONCAT('%', :productName, '%')  AND s.studyLevel LIKE CONCAT('%', :studyLevel, '%')")
	public Long totalPriceBySchoolIdAndProductNameContainingAndStudyLevelContaining(long schoolId, String productName,
			String studyLevel);

	@Query(value = "SELECT sum(s.amount * s.specialPrice) FROM SchoolProductHistory s Where s.school.id=:schoolId AND s.productSize.size=:size AND s.productSize.product.name LIKE CONCAT('%', :productName, '%')  AND s.studyLevel LIKE CONCAT('%', :studyLevel, '%')  AND s.registeredDate > :registeredDateFrom AND  s.registeredDate < :registeredDateTo")
	public Long totalPriceBySchoolIdAndSizeAndProductNameContainingAndStudyLevelContainingAndRegisteredDateBetween(
			long schoolId, int size, String productName, String studyLevel, Date registeredDateFrom,
			Date registeredDateTo);
	
	@Query(value = "SELECT sum(s.amount * s.specialPrice) FROM SchoolProductHistory s Where s.school.id=:schoolId AND s.productSize.product.name LIKE CONCAT('%', :productName, '%')  AND s.studyLevel LIKE CONCAT('%', :studyLevel, '%')  AND s.registeredDate > :registeredDateFrom AND  s.registeredDate < :registeredDateTo")
	public Long totalPriceBySchoolIdAndProductNameContainingAndStudyLevelContainingAndRegisteredDateBetween(
			long schoolId, String productName, String studyLevel, Date registeredDateFrom,
			Date registeredDateTo);
	


}
