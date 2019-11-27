package com.dream.controllers;

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
import org.springframework.web.bind.annotation.RestController;

import com.dream.models.ClothType;
import com.dream.services.ClothTypeService;

@CrossOrigin(origins = "*")
@RestController
public class ClothTypeController {
	@Autowired
	private ClothTypeService clothTypeService;

	@GetMapping("/clothTypes")
	public Page<ClothType> retrieveAllClothTypes(Pageable pageable) {
		return clothTypeService.getClothTypes(pageable);
	}

	@GetMapping("/clothTypes/{clothTypeId}")
	public ClothType retrieveClothTypeById(@PathVariable long clothTypeId) {
		return clothTypeService.getClothTypeById(clothTypeId);
	}

	@DeleteMapping("/clothTypes/{clothTypeId}")
	public void deleteClothType(@PathVariable int clothTypeId) {
		clothTypeService.deleteClothType(clothTypeId);
	}

	@PostMapping("/clothTypes")
	public ResponseEntity<ClothType> createClothType(@Valid @RequestBody ClothType clothType) {

		ClothType createdClothType = clothTypeService.createClothType(clothType);
		return new ResponseEntity<ClothType>(createdClothType, HttpStatus.OK);

	}

	@PutMapping("/clothTypes/{clothTypeId}")
	public ResponseEntity<ClothType> updateClothType(@PathVariable int clothTypeId, @Valid @RequestBody ClothType clothType) {
		ClothType updatedClothType = clothTypeService.updateClothType(clothTypeId, clothType);
		return new ResponseEntity<ClothType>(updatedClothType, HttpStatus.OK);
	}
}
