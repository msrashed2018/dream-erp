package com.dream.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dream.models.Product;
import com.dream.models.ProductSize;

public interface ProductService {
	
	Page<Product> getProducts(Pageable pageable);
	
	Product getProductById(long id);
	
	Product createProduct(Product Product);
	
	Product updateProduct(long id, Product Product);
	
	void deleteProduct(long id);

	List<ProductSize> getProductDetails(long productId);
	
	ProductSize addProductDetails(long productId, ProductSize productDetails);
	
	ProductSize updateProductDetails(long productDetailsId, ProductSize productDetails);
	
	void deleteProductDetails(long productDetailsId);

	List<Product> getAllProducts();

}
