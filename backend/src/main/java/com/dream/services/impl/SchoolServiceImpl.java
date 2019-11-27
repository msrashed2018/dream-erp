/**
 * 
 */
package com.dream.services.impl;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.dream.models.School;
import com.dream.models.SchoolProduct;
import com.dream.models.SchoolProductHistory;
import com.dream.models.SchoolSales;
import com.dream.repositories.ProductSizeRepository;
import com.dream.repositories.SchoolProductHistoryRepository;
import com.dream.repositories.SchoolRepository;
import com.dream.repositories.SchoolSalesRepository;
import com.dream.services.SchoolService;
import com.dream.utils.DateUtils;

/**
 * @author mohamedsalah
 *
 */
@Service
public class SchoolServiceImpl implements SchoolService {
	@Autowired
	private SchoolRepository schoolRepository;

	@Autowired
	private SchoolProductHistoryRepository schoolProductRepository;

	@Autowired
	private SchoolSalesRepository schoolSalesRepository;

	@Override
	public Page<School> getSchools(String nameConatining, Pageable pageable) {
		return schoolRepository.findByNameContaining(nameConatining, pageable);
	}

	@Override
	public School getSchoolById(long id) {
		Optional<School> school = schoolRepository.findById(id);
		if (!school.isPresent()) {
			throw new ResourceNotFoundException("SchoolId " + id + " not found");
		}
		return school.get();
	}

	@Override
	public School createSchool(School school) {
		return schoolRepository.save(school);
	}

	@Override
	public School updateSchool(long id, School school) {
		Optional<School> existingSchool = schoolRepository.findById(id);
		if (!existingSchool.isPresent()) {
			throw new ResourceNotFoundException("SchoolId " + id + " not found");
		}
		existingSchool.get().setAddress(school.getAddress());
		existingSchool.get().setMobile(school.getMobile());
		existingSchool.get().setName(school.getName());
		existingSchool.get().setOwner(school.getOwner());
		existingSchool.get().setNotes(school.getNotes());
		return schoolRepository.save(existingSchool.get());
	}

	@Override
	public void deleteSchool(long id) {
		if (!schoolRepository.existsById(id)) {
			throw new ResourceNotFoundException("SchoolId " + id + " not found");
		}
		schoolRepository.deleteById(id);

	}

	@Override
	public Page<SchoolProductHistory> getSchoolProducts(long schoolId, String productNameContaining, int productSize,
			String dateFrom, String dateTo, String studyLevel, Pageable pageable) throws ParseException {

		if (productSize != 0) {
			if (dateFrom != null && dateTo != null) {
				Calendar start = Calendar.getInstance();
				Calendar end = Calendar.getInstance();
				DateUtils.formatDates(start, end, dateFrom, dateTo);
				return schoolProductRepository
						.findBySchoolIdAndProductSizeSizeAndProductSizeProductNameContainingAndRegisteredDateBetweenAndStudyLevelContaining(
								schoolId, productSize, productNameContaining, start.getTime(), end.getTime(),
								studyLevel, pageable);

			} else {
				return schoolProductRepository.findBySchoolIdAndProductSizeSizeAndProductSizeProductNameContainingAndStudyLevelContaining(
						schoolId, productSize, productNameContaining, studyLevel, pageable);
			}
		} else {
			if (dateFrom != null && dateTo != null) {
				Calendar start = Calendar.getInstance();
				Calendar end = Calendar.getInstance();
				DateUtils.formatDates(start, end, dateFrom, dateTo);
				return schoolProductRepository
						.findBySchoolIdAndProductSizeProductNameContainingAndRegisteredDateBetweenAndStudyLevelContaining(schoolId,
								productNameContaining, start.getTime(), end.getTime(), studyLevel, pageable);

			} else {
				return schoolProductRepository.findBySchoolIdAndProductSizeProductNameContainingAndStudyLevelContaining(schoolId,
						productNameContaining, studyLevel, pageable);
			}
		}
	}

	@Override
	public Page<SchoolSales> getSchoolSales(long schoolId, String productNameContaining, int productSize,
			String dateFrom, String dateTo, String studyLevel, Pageable pageable) throws ParseException {

		if (productSize != 0) {
			if (dateFrom != null && dateTo != null) {
				Calendar start = Calendar.getInstance();
				Calendar end = Calendar.getInstance();
				DateUtils.formatDates(start, end, dateFrom, dateTo);
				return schoolSalesRepository
						.findBySchoolIdAndProductSizeSizeAndProductSizeProductNameContainingAndRegisteredDateBetween(
								schoolId, productSize, productNameContaining, start.getTime(), end.getTime(), pageable);

			} else {
				return schoolSalesRepository.findBySchoolIdAndProductSizeSizeAndProductSizeProductNameContaining(
						schoolId, productSize, productNameContaining, pageable);
			}
		} else {
			if (dateFrom != null && dateTo != null) {
				Calendar start = Calendar.getInstance();
				Calendar end = Calendar.getInstance();
				DateUtils.formatDates(start, end, dateFrom, dateTo);
				return schoolSalesRepository
						.findBySchoolIdAndProductSizeProductNameContainingAndRegisteredDateBetween(schoolId,
								productNameContaining, start.getTime(), end.getTime(), pageable);

			} else {
				return schoolSalesRepository.findBySchoolIdAndProductSizeProductNameContaining(schoolId,
						productNameContaining, pageable);
			}
		}
	}

	@Override
	public SchoolProductHistory addSchoolProduct(long schoolId, SchoolProductHistory schoolProduct) {

		Optional<School> school = schoolRepository.findById(schoolId);
		if (!school.isPresent()) {
			throw new ResourceNotFoundException("SchoolId " + schoolId + " not found");
		}

//		ProductSize productSize = productSizeRepository
//				.findByProductIdAndSize(schoolProduct.getProduct().getId(), schoolProduct.getSize());
//		if (productSize == null) {
//			throw new ResourceNotFoundException("Product " + schoolProduct.getProduct().getId() + " not found");
//		}

//		productDetails.setAmount(productDetails.getAmount() - schoolProduct.getAmount());
//		productSizeRepository.save(productSize);

		schoolProduct.setRegisteredDate(new Date());
		schoolProduct.setSchool(school.get());
		return schoolProductRepository.save(schoolProduct);

	}

	@Override
	public SchoolSales addSchoolSales(long schoolId, SchoolSales schoolSales) {

		Optional<School> school = schoolRepository.findById(schoolId);
		if (!school.isPresent()) {
			throw new ResourceNotFoundException("SchoolId " + schoolId + " not found");
		}
		schoolSales.setRegisteredDate(new Date());
		schoolSales.setSchool(school.get());
		return schoolSalesRepository.save(schoolSales);
	}

//	@Override
//	public SchoolProductHistory updateSchoolProduct(long schoolProductId, SchoolProductHistory schoolProduct) {
//		Optional<SchoolProductHistory> existingSchoolProduct = schoolProductRepository.findById(schoolProductId);
//		if (!existingSchoolProduct.isPresent()) {
//			throw new ResourceNotFoundException("schoolProductId " + schoolProductId + " not found");
//		}
//		existingSchoolProduct.get().setAmount(schoolProduct.getAmount());
////		existingSchoolProduct.get().setSize(schoolProduct.getSize());
////		existingSchoolProduct.get().setProduct(schoolProduct.getProduct());
//		existingSchoolProduct.get().setSpecialPrice(schoolProduct.getSpecialPrice());
//		existingSchoolProduct.get().setStudyLevel(schoolProduct.getStudyLevel());
//		return schoolProductRepository.save(existingSchoolProduct.get());
//
//	}

//	@Override
//	public SchoolSales updateSchoolSales(long schoolSalesId, SchoolSales schoolSales) {
//		Optional<SchoolSales> existingSchoolSales = schoolSalesRepository.findById(schoolSalesId);
//		if (!existingSchoolSales.isPresent()) {
//			throw new ResourceNotFoundException("schoolSalesId " + schoolSalesId + " not found");
//		}
//		existingSchoolSales.get().setAmount(schoolSales.getAmount());
//		existingSchoolSales.get().setSize(schoolSales.getSize());
//		existingSchoolSales.get().setProduct(schoolSales.getProduct());
//		existingSchoolSales.get().setSpecialPrice(schoolSales.getSpecialPrice());
//		existingSchoolSales.get().setStudyLevel(schoolSales.getStudyLevel());
//		return schoolSalesRepository.save(existingSchoolSales.get());
//	}

	@Override
	public void deleteSchoolProduct(long schoolProductId) {
		if (!schoolProductRepository.existsById(schoolProductId)) {
			throw new ResourceNotFoundException("schoolProductId " + schoolProductId + " not found");
		}
		schoolProductRepository.deleteById(schoolProductId);
	}

	@Override
	public void deleteSchoolSales(long schoolSalesId) {
		if (!schoolSalesRepository.existsById(schoolSalesId)) {
			throw new ResourceNotFoundException("schoolSalesId " + schoolSalesId + " not found");
		}
		schoolSalesRepository.deleteById(schoolSalesId);
	}

	@Override
	public Long getSchoolProductsTotalPrices(long schoolId, String productNameContaining, int productSize,
			String dateFrom, String dateTo, String studyLevel) throws ParseException {
		if (productSize != 0) {
			if (dateFrom != null && dateTo != null) {
				Calendar start = Calendar.getInstance();
				Calendar end = Calendar.getInstance();
				DateUtils.formatDates(start, end, dateFrom, dateTo);
				return schoolProductRepository
						.totalPriceBySchoolIdAndSizeAndProductNameContainingAndStudyLevelContainingAndRegisteredDateBetween(
								schoolId, productSize, productNameContaining, studyLevel, start.getTime(),
								end.getTime());

			} else {
				return schoolProductRepository
						.totalPriceBySchoolIdAndSizeAndProductNameContainingAndStudyLevelContaining(schoolId,
								productSize, productNameContaining, studyLevel);
			}
		} else {
			if (dateFrom != null && dateTo != null) {
				Calendar start = Calendar.getInstance();
				Calendar end = Calendar.getInstance();
				DateUtils.formatDates(start, end, dateFrom, dateTo);
				return schoolProductRepository
						.totalPriceBySchoolIdAndProductNameContainingAndStudyLevelContainingAndRegisteredDateBetween(
								schoolId, productNameContaining, studyLevel, start.getTime(), end.getTime());

			} else {
				return schoolProductRepository.totalPriceBySchoolIdAndProductNameContainingAndStudyLevelContaining(
						schoolId, productNameContaining, studyLevel);
			}
		}
	}

	@Override
	public Long getSchoolSalesTotalPrices(long schoolId, String productNameContaining, int productSize, String dateFrom,
			String dateTo, String studyLevel) throws ParseException {
		if (productSize != 0) {
			if (dateFrom != null && dateTo != null) {
				Calendar start = Calendar.getInstance();
				Calendar end = Calendar.getInstance();
				DateUtils.formatDates(start, end, dateFrom, dateTo);
				return schoolSalesRepository
						.totalPriceBySchoolIdAndProductSizeSizeAndProductSizeProductNameContainingAndRegisteredDateBetween(
								schoolId, productSize, productNameContaining, start.getTime(),
								end.getTime());

			} else {
				return schoolSalesRepository.totalPriceBySchoolIdAndSizeAndProductNameContaining(
						schoolId, productSize, productNameContaining);
			}
		} else {
			if (dateFrom != null && dateTo != null) {
				Calendar start = Calendar.getInstance();
				Calendar end = Calendar.getInstance();
				DateUtils.formatDates(start, end, dateFrom, dateTo);
				return schoolSalesRepository
						.totalPriceBySchoolIdAndProductSizeProductNameContainingAndRegisteredDateBetween(
								schoolId, productNameContaining, start.getTime(), end.getTime());

			} else {
				return schoolSalesRepository.totalPriceBySchoolIdAndProductSizeProductNameContaining(
						schoolId, productNameContaining);
			}
		}
	}

	@Override
	public Page<SchoolProduct> getSchoolAvailableProducts(long schoolId, String productNameContaining,
			Pageable pageable) {
		// TODO get all products 
		return null;
	}

}
