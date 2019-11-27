package com.dream.controllers;

import java.text.ParseException;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dream.models.Cloth;
import com.dream.services.ClothService;

@CrossOrigin(origins = "*")
@RestController
public class ClothController {
	@Autowired
	private ClothService clothService;

	@GetMapping("/cloths")
	public Page<Cloth> retrieveAllCloths(@RequestParam(name = "clothTypeId", defaultValue = "0",required = false)  long clothTypeId,
			@RequestParam(name = "dateFrom", required = false) String dateFrom,
			@RequestParam(name = "dateTo", required = false) String dateTo,
			@RequestParam(name = "colorId", defaultValue = "0", required = false) long colorId,
			Pageable pageable) {
		try {
			System.out.println("\n\n dateFrom = "+dateFrom);
			System.out.println("\n\n dateTo = "+dateTo);
			return clothService.getCloths(clothTypeId, dateFrom, dateTo, colorId, pageable);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	@GetMapping("/cloths/{clothId}")
	public Cloth retrieveClothById(@PathVariable long clothId) {
		return clothService.getClothById(clothId);
	}

	@DeleteMapping("/cloths/{clothId}")
	public void deleteCloth(@PathVariable long clothId) {
		clothService.deleteCloth(clothId);
	}

	@PostMapping("/cloths")
	public ResponseEntity<Cloth> createCloth(@Valid @RequestBody Cloth cloth) {

		Cloth createdCloth = clothService.createCloth(cloth);
		return new ResponseEntity<Cloth>(createdCloth, HttpStatus.OK);

	}

	@PutMapping("/cloths/{clothId}")
	public ResponseEntity<Cloth> updateCloth(@PathVariable long clothId, @Valid @RequestBody Cloth cloth) {
		Cloth updatedCloth = clothService.updateCloth(clothId, cloth);
		return new ResponseEntity<Cloth>(updatedCloth, HttpStatus.OK);
	}
}
