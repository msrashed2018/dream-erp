package com.dream.controllers;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dream.models.School;
import com.dream.models.SchoolProductHistory;
import com.dream.models.SchoolSales;
import com.dream.services.SchoolService;

@CrossOrigin(origins = "*")
@RestController
public class SchoolController {
	@Autowired
	private SchoolService schoolService;

	@GetMapping("/schools")
	public Page<School> retrieveAllSchools(
			@RequestParam(name = "name", defaultValue = "", required = false) String nameConatining,
			Pageable pageable) {
		return schoolService.getSchools(nameConatining, pageable);
	}

	@GetMapping("/schools/{schoolId}")
	public School retrieveSchoolById(@PathVariable long schoolId) {
		return schoolService.getSchoolById(schoolId);
	}

	@DeleteMapping("/schools/{schoolId}")
	public void deleteSchool(@PathVariable long schoolId) {
		schoolService.deleteSchool(schoolId);
	}

	@PostMapping("/schools")
	public ResponseEntity<School> createSchool(@Valid @RequestBody School school) {

		School createdSchool = schoolService.createSchool(school);
		return new ResponseEntity<School>(createdSchool, HttpStatus.OK);

	}

	@PutMapping("/schools/{schoolId}")
	public ResponseEntity<School> updateSchool(@PathVariable long schoolId, @Valid @RequestBody School school) {
		School updatedSchool = schoolService.updateSchool(schoolId, school);
		return new ResponseEntity<School>(updatedSchool, HttpStatus.OK);
	}

	@GetMapping("/schools/{schoolId}/products")
	public Page<SchoolProductHistory> retrieveSchoolProducts(@PathVariable long schoolId,
			@RequestParam(name = "productName", defaultValue = "", required = false) String productNameContaining,
			@RequestParam(name = "productSize", defaultValue = "0", required = false) int productSize,
			@RequestParam(name = "dateFrom", required = false) String dateFrom,
			@RequestParam(name = "dateTo", required = false) String dateTo,
			@RequestParam(name = "studyLevel", defaultValue = "", required = false) String studyLevel,
			Pageable pageable) {
		try {
			return schoolService.getSchoolProducts(schoolId, productNameContaining, productSize, dateFrom, dateTo,
					studyLevel, pageable);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	@GetMapping("/schools/{schoolId}/products/totalPrice")
	public ResponseEntity<Long> getSchoolProductsTotalPrices(@PathVariable long schoolId,
			@RequestParam(name = "productName", defaultValue = "", required = false) String productNameContaining,
			@RequestParam(name = "productSize", defaultValue = "0", required = false) int productSize,
			@RequestParam(name = "dateFrom", required = false) String dateFrom,
			@RequestParam(name = "dateTo", required = false) String dateTo,
			@RequestParam(name = "studyLevel", defaultValue = "", required = false) String studyLevel) {


		try {
			return new ResponseEntity<Long>(schoolService.getSchoolProductsTotalPrices(schoolId, productNameContaining, productSize, dateFrom, dateTo,
					studyLevel), HttpStatus.OK);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		}
		
	}

	@GetMapping("/schools/{schoolId}/sales")
	public Page<SchoolSales> retrieveSchoolSales(@PathVariable long schoolId,
			@RequestParam(name = "productName", defaultValue = "", required = false) String productNameContaining,
			@RequestParam(name = "productSize", required = false) int productSize,
			@RequestParam(name = "dateFrom", required = false) String dateFrom,
			@RequestParam(name = "dateTo", required = false) String dateTo,
			@RequestParam(name = "studyLevel", defaultValue = "", required = false) String studyLevel,
			Pageable pageable) {
		try {
			return schoolService.getSchoolSales(schoolId, productNameContaining, productSize, dateFrom, dateTo,
					studyLevel, pageable);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	@GetMapping("/schools/{schoolId}/sales/totalPrice")
	public ResponseEntity<Long> getSchoolSalesTotalPrices(@PathVariable long schoolId,
			@RequestParam(name = "productName", defaultValue = "", required = false) String productNameContaining,
			@RequestParam(name = "productSize", defaultValue = "0", required = false) int productSize,
			@RequestParam(name = "dateFrom", required = false) String dateFrom,
			@RequestParam(name = "dateTo", required = false) String dateTo,
			@RequestParam(name = "studyLevel", defaultValue = "", required = false) String studyLevel) {


		try {
			return new ResponseEntity<Long>(schoolService.getSchoolSalesTotalPrices(schoolId, productNameContaining, productSize, dateFrom, dateTo,
					studyLevel), HttpStatus.OK);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		}
		
	}
	@PostMapping("/schools/{schoolId}/products")
	public ResponseEntity<SchoolProductHistory> addSchoolProduct(@PathVariable long schoolId,
			@Valid @RequestBody SchoolProductHistory schoolProduct) {

		SchoolProductHistory createdSchoolProduct = schoolService.addSchoolProduct(schoolId, schoolProduct);
		return new ResponseEntity<SchoolProductHistory>(createdSchoolProduct, HttpStatus.OK);

	}

	@PostMapping("/schools/{schoolId}/sales")
	public ResponseEntity<SchoolSales> addSchoolProduct(@PathVariable long schoolId,
			@Valid @RequestBody SchoolSales schoolSales) {

		SchoolSales createdSchoolSales = schoolService.addSchoolSales(schoolId, schoolSales);
		return new ResponseEntity<SchoolSales>(createdSchoolSales, HttpStatus.OK);

	}

//	@PutMapping("/schools/products/{productId}")
//	public ResponseEntity<SchoolProductHistory> updateSchoolProduct(@PathVariable long productId,
//			@Valid @RequestBody SchoolProductHistory schoolProduct) {
//		SchoolProductHistory updatedSchoolProduct = schoolService.updateSchoolProduct(productId, schoolProduct);
//		return new ResponseEntity<SchoolProductHistory>(updatedSchoolProduct, HttpStatus.OK);
//	}

//	@PutMapping("/schools/products/{salesId}")
//	public ResponseEntity<SchoolSales> updateSchoolSales(@PathVariable long salesId,
//			@Valid @RequestBody SchoolSales schoolSales) {
//		SchoolSales updatedSchoolSales = schoolService.updateSchoolSales(salesId, schoolSales);
//		return new ResponseEntity<SchoolSales>(updatedSchoolSales, HttpStatus.OK);
//	}

	@DeleteMapping("/schools/products/{productId}")
	public void deleteSchoolProduct(@PathVariable long productId) {
		schoolService.deleteSchoolProduct(productId);
	}

	@DeleteMapping("/schools/sales/{salesId}")
	public void deleteSchoolSales(@PathVariable long salesId) {
		schoolService.deleteSchoolSales(salesId);
	}

}
