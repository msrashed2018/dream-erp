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

import com.dream.models.ClothType;
import com.dream.repositories.ClothTypeRepository;
import com.dream.services.ClothTypeService;

/**
 * @author mohamedsalah
 *
 */
@Service
public class ClothTypeServiceImpl implements ClothTypeService {
	@Autowired
	private ClothTypeRepository clothTypeRepository;

	@Override
	public Page<ClothType> getClothTypes(Pageable pageable) {
		return clothTypeRepository.findAll(pageable);
	}

	@Override
	public ClothType getClothTypeById(long id) {
		Optional<ClothType> clothType = clothTypeRepository.findById(id);
		if (!clothType.isPresent()) {
			throw new ResourceNotFoundException("ClothTypeId " + id + " not found");
		}
		return clothType.get();
	}

	@Override
	public ClothType createClothType(ClothType clothType) {
		return clothTypeRepository.save(clothType);
	}

	@Override
	public ClothType updateClothType(long id, ClothType clothType) {
		Optional<ClothType> existingClothType = clothTypeRepository.findById(id);
		if (!existingClothType.isPresent()) {
			throw new ResourceNotFoundException("ClothTypeId " + id + " not found");
		}
		existingClothType.get().setName(clothType.getName());
		existingClothType.get().setNotes(clothType.getNotes());
		return clothTypeRepository.save(existingClothType.get());
	}

	@Override
	public void deleteClothType(long id) {
		if (!clothTypeRepository.existsById(id)) {
			throw new ResourceNotFoundException("ClothTypeId " + id + " not found");
		}
		clothTypeRepository.deleteById(id);

	}

}
