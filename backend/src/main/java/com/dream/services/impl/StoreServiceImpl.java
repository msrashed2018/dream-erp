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

import com.dream.models.Store;
import com.dream.models.StoreProductHistory;
import com.dream.models.StoreSales;
import com.dream.repositories.StoreProductHistoryRepository;
import com.dream.repositories.StoreRepository;
import com.dream.repositories.StoreSalesRepository;
import com.dream.services.StoreService;
import com.dream.utils.DateUtils;

/**
 * @author mohamedsalah
 *
 */
@Service
public class StoreServiceImpl implements StoreService {
	@Autowired
	private StoreRepository storeRepository;

	@Autowired
	private StoreProductHistoryRepository storeProductRepository;

	@Autowired
	private StoreSalesRepository storeSalesRepository;

	@Override
	public Page<Store> getStores(String nameConatining, Pageable pageable) {
		return storeRepository.findByNameContaining(nameConatining, pageable);
	}

	@Override
	public Store getStoreById(long id) {
		Optional<Store> store = storeRepository.findById(id);
		if (!store.isPresent()) {
			throw new ResourceNotFoundException("StoreId " + id + " not found");
		}
		return store.get();
	}

	@Override
	public Store createStore(Store store) {
		return storeRepository.save(store);
	}

	@Override
	public Store updateStore(long id, Store store) {
		Optional<Store> existingStore = storeRepository.findById(id);
		if (!existingStore.isPresent()) {
			throw new ResourceNotFoundException("StoreId " + id + " not found");
		}
		existingStore.get().setAddress(store.getAddress());
		existingStore.get().setEmail(store.getEmail());
		existingStore.get().setMobile(store.getMobile());
		existingStore.get().setName(store.getName());
		existingStore.get().setNotes(store.getNotes());
		return storeRepository.save(existingStore.get());
	}

	@Override
	public void deleteStore(long id) {
		if (!storeRepository.existsById(id)) {
			throw new ResourceNotFoundException("StoreId " + id + " not found");
		}
		storeRepository.deleteById(id);

	}

	@Override
	public Page<StoreProductHistory> getStoreProducts(long storeId, String productNameContaining, int productSize,
			String dateFrom, String dateTo, String studyLevel, Pageable pageable) throws ParseException {

		if (productSize != 0) {
			if (dateFrom != null && dateTo != null) {
				Calendar start = Calendar.getInstance();
				Calendar end = Calendar.getInstance();
				DateUtils.formatDates(start, end, dateFrom, dateTo);
				return storeProductRepository
						.findByStoreIdAndProductSizeSizeAndProductSizeProductNameContainingAndRegisteredDateBetweenAndStudyLevelContaining(
								storeId, productSize, productNameContaining, start.getTime(), end.getTime(), studyLevel,
								pageable);

			} else {
				return storeProductRepository.findByStoreIdAndProductSizeSizeAndProductSizeProductNameContainingAndStudyLevelContaining(
						storeId, productSize, productNameContaining, studyLevel, pageable);
			}
		} else {
			if (dateFrom != null && dateTo != null) {
				Calendar start = Calendar.getInstance();
				Calendar end = Calendar.getInstance();
				DateUtils.formatDates(start, end, dateFrom, dateTo);
				return storeProductRepository
						.findByStoreIdAndProductSizeProductNameContainingAndRegisteredDateBetweenAndStudyLevelContaining(storeId,
								productSize, productNameContaining, start.getTime(), end.getTime(), studyLevel,
								pageable);

			} else {
				return storeProductRepository.findByStoreIdAndProductSizeProductNameContainingAndStudyLevelContaining(storeId,
						productSize, productNameContaining, studyLevel, pageable);
			}
		}
	}

	@Override
	public Page<StoreSales> getStoreSales(long storeId, String productNameContaining, int productSize, String dateFrom,
			String dateTo, String studyLevel, Pageable pageable) throws ParseException {

		if (productSize != 0) {
			if (dateFrom != null && dateTo != null) {
				Calendar start = Calendar.getInstance();
				Calendar end = Calendar.getInstance();
				DateUtils.formatDates(start, end, dateFrom, dateTo);
				return storeSalesRepository
						.findByStoreIdAndProductSizeSizeAndProductSizeProductNameContainingAndRegisteredDateBetweenAndStudyLevelContaining(
								storeId, productSize, productNameContaining, start.getTime(), end.getTime(), studyLevel,
								pageable);

			} else {
				return storeSalesRepository.findByStoreIdAndProductSizeSizeAndProductSizeProductNameContainingAndStudyLevelContaining(storeId,
						productSize, productNameContaining, studyLevel, pageable);
			}
		} else {
			if (dateFrom != null && dateTo != null) {
				Calendar start = Calendar.getInstance();
				Calendar end = Calendar.getInstance();
				DateUtils.formatDates(start, end, dateFrom, dateTo);
				return storeSalesRepository
						.findByStoreIdAndProductSizeProductNameContainingAndRegisteredDateBetweenAndStudyLevelContaining(storeId,
								productSize, productNameContaining, start.getTime(), end.getTime(), studyLevel,
								pageable);

			} else {
				return storeSalesRepository.findByStoreIdAndProductSizeProductNameContainingAndStudyLevelContaining(storeId,
						productSize, productNameContaining, studyLevel, pageable);
			}
		}
	}

	@Override
	public StoreProductHistory addStoreProduct(long storeId, StoreProductHistory storeProduct) {
		Optional<Store> store = storeRepository.findById(storeId);
		if (!store.isPresent()) {
			throw new ResourceNotFoundException("StoreId " + storeId + " not found");
		}
		storeProduct.setRegisteredDate(new Date());
		storeProduct.setStore(store.get());
		return storeProductRepository.save(storeProduct);

	}

	@Override
	public StoreSales addStoreSales(long storeId, StoreSales storeSales) {
		Optional<Store> store = storeRepository.findById(storeId);
		if (!store.isPresent()) {
			throw new ResourceNotFoundException("StoreId " + storeId + " not found");
		}
		storeSales.setRegisteredDate(new Date());
		storeSales.setStore(store.get());
		return storeSalesRepository.save(storeSales);
	}

//	@Override
//	public StoreProductHistory updateStoreProduct(long storeProductId, StoreProductHistory storeProduct) {
//		Optional<StoreProductHistory> existingStoreProduct = storeProductRepository.findById(storeProductId);
//		if (!existingStoreProduct.isPresent()) {
//			throw new ResourceNotFoundException("StoreProductId " + storeProductId + " not found");
//		}
//		existingStoreProduct.get().setAmount(storeProduct.getAmount());
////		existingStoreProduct.get().setSize(storeProduct.getSize());
////		existingStoreProduct.get().setProduct(storeProduct.getProduct());
//		existingStoreProduct.get().setStudyLevel(storeProduct.getStudyLevel());
//		existingStoreProduct.get().setSpecialPrice(storeProduct.getSpecialPrice());
//		return storeProductRepository.save(existingStoreProduct.get());
//
//	}

//	@Override
//	public StoreSales updateStoreSales(long storeSalesId, StoreSales storeSales) {
//		Optional<StoreSales> existingStoreSales = storeSalesRepository.findById(storeSalesId);
//		if (!existingStoreSales.isPresent()) {
//			throw new ResourceNotFoundException("storeSalesId " + storeSalesId + " not found");
//		}
//		existingStoreSales.get().setAmount(storeSales.getAmount());
//		existingStoreSales.get().setSize(storeSales.getSize());
//		existingStoreSales.get().setProduct(storeSales.getProduct());
//		existingStoreSales.get().setSpecialPrice(storeSales.getSpecialPrice());
//		existingStoreSales.get().setStudyLevel(storeSales.getStudyLevel());
//		return storeSalesRepository.save(existingStoreSales.get());
//	}

	@Override
	public void deleteStoreProduct(long storeProductId) {
		if (!storeProductRepository.existsById(storeProductId)) {
			throw new ResourceNotFoundException("storeProductId " + storeProductId + " not found");
		}
		storeProductRepository.deleteById(storeProductId);
	}

	@Override
	public void deleteStoreSales(long storeSalesId) {
		if (!storeSalesRepository.existsById(storeSalesId)) {
			throw new ResourceNotFoundException("storeSalesId " + storeSalesId + " not found");
		}
		storeSalesRepository.deleteById(storeSalesId);
	}

}
