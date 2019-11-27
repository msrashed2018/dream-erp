package com.dream.services;

import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;

import com.dream.models.Factory;

public interface FactoryService {
	
	Page<Factory> getFactories(Pageable pageable);
	
	Factory getFactoryById(long id);
	
	Factory createFactory(Factory Factory);
	
	Factory updateFactory(long id, Factory Factory);
	
	void deleteFactory(long id);
}
