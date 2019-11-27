package com.dream.services;

import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;

import com.dream.models.ClothType;

public interface ClothTypeService {
	
	Page<ClothType> getClothTypes(Pageable pageable);
	
	ClothType getClothTypeById(long id);
	
	ClothType createClothType(ClothType ClothType);
	
	ClothType updateClothType(long id, ClothType ClothType);
	
	void deleteClothType(long id);
}
