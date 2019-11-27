/**
 * 
 */
package com.dream.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.dream.models.ExpenseType;
import com.dream.repositories.ExpenseTypeRepository;
import com.dream.services.ExpenseTypeService;

/**
 * @author mohamedsalah
 *
 */
@Service
public class ExpenseTypeServiceImpl implements ExpenseTypeService {
	@Autowired
	private ExpenseTypeRepository expenseTypeRepository;

	
	@Override
	public List<ExpenseType> getAllExpenseTypes() {
		return expenseTypeRepository.findAll(Sort.by("name"));
	}

	@Override
	public Page<ExpenseType> getExpenseTypes(Pageable pageable) {
		return expenseTypeRepository.findAll(pageable);
	}

	@Override
	public ExpenseType getExpenseTypeById(long id) {
		Optional<ExpenseType> expenseType = expenseTypeRepository.findById(id);
		if (!expenseType.isPresent()) {
			throw new ResourceNotFoundException("ExpenseTypeId " + id + " not found");
		}
		return expenseType.get();
	}

	@Override
	public ExpenseType createExpenseType(ExpenseType expenseType) {
		return expenseTypeRepository.save(expenseType);
	}

	@Override
	public ExpenseType updateExpenseType(long id, ExpenseType expenseType) {
		Optional<ExpenseType> existingExpenseType = expenseTypeRepository.findById(id);
		if (!existingExpenseType.isPresent()) {
			throw new ResourceNotFoundException("ExpenseTypeId " + id + " not found");
		}
		existingExpenseType.get().setName(expenseType.getName());
		existingExpenseType.get().setNotes(expenseType.getNotes());
		return expenseTypeRepository.save(existingExpenseType.get());
	}

	@Override
	public void deleteExpenseType(long id) {
		if (!expenseTypeRepository.existsById(id)) {
			throw new ResourceNotFoundException("ExpenseTypeId " + id + " not found");
		}
		expenseTypeRepository.deleteById(id);

	}

}
