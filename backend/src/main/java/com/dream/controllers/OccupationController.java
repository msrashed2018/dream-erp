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

import com.dream.models.Occupation;
import com.dream.services.OccupationService;

@CrossOrigin(origins = "*")
@RestController
public class OccupationController {
	@Autowired
	private OccupationService occupationService;

	@GetMapping("/occupations")
	public Page<Occupation> retrieveAllOccupations(Pageable pageable) {
		return occupationService.getOccupations(pageable);
	}

	@GetMapping("/occupations/{occupationId}")
	public Occupation retrieveOccupationById(@PathVariable long occupationId) {
		return occupationService.getOccupationById(occupationId);
	}

	@DeleteMapping("/occupations/{occupationId}")
	public void deleteOccupation(@PathVariable long occupationId) {
		occupationService.deleteOccupation(occupationId);
	}

	@PostMapping("/occupations")
	public ResponseEntity<Occupation> createOccupation(@Valid @RequestBody Occupation occupation) {

		Occupation createdOccupation = occupationService.createOccupation(occupation);
		return new ResponseEntity<Occupation>(createdOccupation, HttpStatus.OK);

	}

	@PutMapping("/occupations/{occupationId}")
	public ResponseEntity<Occupation> updateOccupation(@PathVariable int occupationId, @Valid @RequestBody Occupation occupation) {
		Occupation updatedOccupation = occupationService.updateOccupation(occupationId, occupation);
		return new ResponseEntity<Occupation>(updatedOccupation, HttpStatus.OK);
	}
}
