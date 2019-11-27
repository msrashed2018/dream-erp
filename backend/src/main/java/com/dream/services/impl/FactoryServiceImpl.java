/**
 * 
 */
package com.dream.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.dream.models.Factory;
import com.dream.repositories.FactoryRepository;
import com.dream.services.FactoryService;

/**
 * @author mohamedsalah
 *
 */
@Service
public class FactoryServiceImpl implements FactoryService {

	@Autowired
	private FactoryRepository factoryRepository;

	@Override
	public Page<Factory> getFactories(Pageable pageable) {
		return factoryRepository.findAll(pageable);
	}

	@Override
	public Factory getFactoryById(long id) {
		Optional<Factory> factory = factoryRepository.findById(id);
		if (!factory.isPresent()) {
			throw new ResourceNotFoundException("FactoryId " + id + " not found");
		}
		return factory.get();
	}

	@Override
	public Factory createFactory(Factory factory) {
		return factoryRepository.save(factory);
	}

	@Override
	public Factory updateFactory(long id, Factory factory) {
		Optional<Factory> existingFactory = factoryRepository.findById(id);
		if (!existingFactory.isPresent()) {
			throw new ResourceNotFoundException("FactoryId " + id + " not found");
		}
		existingFactory.get().setName(factory.getName());
		existingFactory.get().setAddress(factory.getAddress());
		existingFactory.get().setMobile1(factory.getMobile1());
		existingFactory.get().setMobile2(factory.getMobile2());
		existingFactory.get().setNotes(factory.getNotes());
		return factoryRepository.save(existingFactory.get());
	}

	@Override
	public void deleteFactory(long id) {
		if (!factoryRepository.existsById(id)) {
			throw new ResourceNotFoundException("FactoryId " + id + " not found");
		}
		factoryRepository.deleteById(id);

	}

}
