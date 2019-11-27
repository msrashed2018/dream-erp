package com.dream.services;

import org.springframework.data.domain.Pageable;

import java.text.ParseException;

import org.springframework.data.domain.Page;

import com.dream.models.Store;
import com.dream.models.StoreProductHistory;
import com.dream.models.StoreSales;

public interface StoreService {

	Page<Store> getStores(String nameConatining, Pageable pageable);

	Store getStoreById(long id);

	Store createStore(Store Store);

	Store updateStore(long id, Store Store);

	void deleteStore(long id);

	Page<StoreProductHistory> getStoreProducts(long storeId, String productNameContaining, int productSize, String dateFrom,
			String dateTo, String studyLevel, Pageable pageable) throws ParseException;

	Page<StoreSales> getStoreSales(long storeId, String productNameContaining, int productSize, String dateFrom,
			String dateTo, String studyLevel, Pageable pageable) throws ParseException;

	StoreProductHistory addStoreProduct(long StoreId, StoreProductHistory StoreProduct);

	StoreSales addStoreSales(long StoreId, StoreSales StoreSales);

//	StoreProductHistory updateStoreProduct(long StoreProductId, StoreProductHistory product);

//	StoreSales updateStoreSales(long storeSalesId, StoreSales storeSales);

	void deleteStoreProduct(long StoreProductId);

	void deleteStoreSales(long StoreSalesId);
}
