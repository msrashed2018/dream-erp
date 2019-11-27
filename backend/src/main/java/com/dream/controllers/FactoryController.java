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

import com.dream.models.Factory;
import com.dream.services.FactoryService;

@CrossOrigin(origins = "*")
@RestController
public class FactoryController {
	@Autowired
	private FactoryService factoryService;

	@GetMapping("/factorys")
	public Page<Factory> retrieveAllFactories(Pageable pageable) {
		return factoryService.getFactories(pageable);
	}

	@GetMapping("/factorys/{factoryId}")
	public Factory retrieveFactoryById(@PathVariable long factoryId) {
		return factoryService.getFactoryById(factoryId);
	}

	@DeleteMapping("/factorys/{factoryId}")
	public void deleteFactory(@PathVariable long factoryId) {
		factoryService.deleteFactory(factoryId);
	}

	@PostMapping("/factorys")
	public ResponseEntity<Factory> createFactory(@Valid @RequestBody Factory factory) {

		Factory createdFactory = factoryService.createFactory(factory);
		return new ResponseEntity<Factory>(createdFactory, HttpStatus.OK);

	}

	@PutMapping("/factorys/{factoryId}")
	public ResponseEntity<Factory> updateFactory(@PathVariable long factoryId, @Valid @RequestBody Factory factory) {
		Factory updatedFactory = factoryService.updateFactory(factoryId, factory);
		return new ResponseEntity<Factory>(updatedFactory, HttpStatus.OK);
	}
}
