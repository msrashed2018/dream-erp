package com.dream.services;

import org.springframework.data.domain.Pageable;

import java.text.ParseException;

import org.springframework.data.domain.Page;

import com.dream.models.Cloth;

public interface ClothService {
	
	Page<Cloth> getCloths(long clothTypeId,String dateFrom, String dateTo,long colorId, Pageable pageable) throws ParseException;
	
	Cloth getClothById(long id);
	
	Cloth createCloth(Cloth Cloth);
	
	Cloth updateCloth(long id, Cloth Cloth);
	
	void deleteCloth(long id);
}
