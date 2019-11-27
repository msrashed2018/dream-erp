package com.dream.services;

import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;

import com.dream.models.Occupation;

public interface OccupationService {
	
	Page<Occupation> getOccupations(Pageable pageable);
	
	Occupation getOccupationById(long id);
	
	Occupation createOccupation(Occupation Occupation);
	
	Occupation updateOccupation(long id, Occupation Occupation);
	
	void deleteOccupation(long id);
}
