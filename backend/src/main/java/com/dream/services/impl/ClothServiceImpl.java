/**
 * 
 */
package com.dream.services.impl;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.dream.models.Cloth;
import com.dream.repositories.ClothRepository;
import com.dream.services.ClothService;
import com.dream.utils.DateUtils;

/**
 * @author mohamedsalah
 *
 */
@Service
public class ClothServiceImpl implements ClothService {
	@Autowired
	private ClothRepository clothRepository;

	@Override
	public Page<Cloth> getCloths(long clothTypeId, String dateFrom, String dateTo, long colorId, Pageable pageable)
			throws ParseException {
		if (dateFrom != null && dateTo != null) {
			Calendar start = Calendar.getInstance();
			Calendar end = Calendar.getInstance();
			DateUtils.formatDates(start, end, dateFrom, dateTo);
			if (clothTypeId != 0 && colorId != 0) {
				System.out.println("\n\n  findByClothTypeIdAndColorIdAndRegisteredDateBetween  \n\n");
				return clothRepository.findByClothTypeIdAndColorIdAndRegisteredDateBetween(clothTypeId, colorId,
						start.getTime(), end.getTime(), pageable);
			} else if (clothTypeId != 0 && colorId == 0) {
				System.out.println("\n\n   findByClothTypeIdAndRegisteredDateBetween \n\n");
				return clothRepository.findByClothTypeIdAndRegisteredDateBetween(clothTypeId, start.getTime(),
						end.getTime(), pageable);
			} else if (clothTypeId == 0 && colorId != 0) {
				System.out.println("\n\n  findByColorIdAndRegisteredDateBetween  \n\n");
				return clothRepository.findByColorIdAndRegisteredDateBetween(colorId, start.getTime(), end.getTime(),
						pageable);
			} else {
				System.out.println("\n\n  findByRegisteredDateBetween  \n\n");
				return clothRepository.findByRegisteredDateBetween(start.getTime(), end.getTime(), pageable);
			}
		} else {
			if (clothTypeId != 0 && colorId != 0) {
				System.out.println("\n\n  findByClothTypeIdAndColorId  \n\n");
				return clothRepository.findByClothTypeIdAndColorId(clothTypeId, colorId, pageable);
			} else if (clothTypeId != 0 && colorId == 0) {
				System.out.println("\n\n  findByClothTypeId  \n\n");
				return clothRepository.findByClothTypeId(clothTypeId, pageable);
			} else if (clothTypeId == 0 && colorId != 0) {
				System.out.println("\n\n  findByColorId  \n\n");
				return clothRepository.findByColorId(colorId, pageable);
			} else {
				System.out.println("\n\n  findAll  \n\n");
				return clothRepository.findAll(pageable);
			}
		}

	}

	@Override
	public Cloth getClothById(long id) {
		Optional<Cloth> cloth = clothRepository.findById(id);
		if (!cloth.isPresent()) {
			throw new ResourceNotFoundException("ClothId " + id + " not found");
		}
		return cloth.get();
	}

	@Override
	public Cloth createCloth(Cloth cloth) {
		cloth.setRegisteredDate(new Date());
		return clothRepository.save(cloth);
	}

	@Override
	public Cloth updateCloth(long id, Cloth cloth) {
		Optional<Cloth> existingCloth = clothRepository.findById(id);
		if (!existingCloth.isPresent()) {
			throw new ResourceNotFoundException("ClothId " + id + " not found");
		}
		existingCloth.get().setAmount(cloth.getAmount());
		existingCloth.get().setClothType(cloth.getClothType());
		existingCloth.get().setColor(cloth.getColor());
		existingCloth.get().setNotes(cloth.getNotes());

		return clothRepository.save(existingCloth.get());
	}

	@Override
	public void deleteCloth(long id) {
		if (!clothRepository.existsById(id)) {
			throw new ResourceNotFoundException("ClothId " + id + " not found");
		}
		clothRepository.deleteById(id);

	}

}
