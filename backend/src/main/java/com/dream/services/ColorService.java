package com.dream.services;

import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;

import com.dream.models.Color;

public interface ColorService {
	
	Page<Color> getColors(Pageable pageable);
	
	Color getColorById(long id);
	
	Color createColor(Color color);
	
	Color updateColor(long id, Color color);
	
	void deleteColor(long id);
}
