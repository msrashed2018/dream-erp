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

import com.dream.models.Occupation;
import com.dream.repositories.OccupationRepository;
import com.dream.services.OccupationService;

/**
 * @author mohamedsalah
 *
 */
@Service
public class OccupationServiceImpl implements OccupationService {
	@Autowired
	private OccupationRepository occupationRepository;

	@Override
	public Page<Occupation> getOccupations(Pageable pageable) {
		return occupationRepository.findAll(pageable);
	}

	@Override
	public Occupation getOccupationById(long id) {
		Optional<Occupation> occupation = occupationRepository.findById(id);
		if (!occupation.isPresent()) {
			throw new ResourceNotFoundException("OccupationId " + id + " not found");
		}
		return occupation.get();
	}

	@Override
	public Occupation createOccupation(Occupation occupation) {
		return occupationRepository.save(occupation);
	}

	@Override
	public Occupation updateOccupation(long id, Occupation occupation) {
		Optional<Occupation> existingOccupation = occupationRepository.findById(id);
		if (!existingOccupation.isPresent()) {
			throw new ResourceNotFoundException("OccupationId " + id + " not found");
		}
		existingOccupation.get().setDescription(occupation.getDescription());
		existingOccupation.get().setName(occupation.getName());
		return occupationRepository.save(existingOccupation.get());
	}

	@Override
	public void deleteOccupation(long id) {
		if(!occupationRepository.existsById(id)){
			throw new ResourceNotFoundException("OccupationId " + id + " not found");
		}
		occupationRepository.deleteById(id);
	}

}
