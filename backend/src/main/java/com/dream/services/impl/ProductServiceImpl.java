/**
 * 
 */
package com.dream.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.dream.exceptions.ResourceAlreadyExistException;
import com.dream.models.Product;
import com.dream.models.ProductSize;
import com.dream.repositories.ProductRepository;
import com.dream.repositories.ProductSizeRepository;
import com.dream.services.ProductService;

/**
 * @author mohamedsalah
 *
 */
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductSizeRepository productSizeRepository;

	@Override
	public Page<Product> getProducts(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@Override
	public Product getProductById(long id) {
		Optional<Product> product = productRepository.findById(id);
		if (!product.isPresent()) {
			throw new ResourceNotFoundException("ProductId " + id + " not found");
		}
		return product.get();
	}

	@Override
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(long id, Product product) {
		Optional<Product> existingProduct = productRepository.findById(id);
		if (!existingProduct.isPresent()) {
			throw new ResourceNotFoundException("ProductId " + id + " not found");
		}
		existingProduct.get().setName(product.getName());
		existingProduct.get().setNotes(product.getNotes());
		return productRepository.save(existingProduct.get());
	}

	@Override
	public void deleteProduct(long id) {
		if (!productRepository.existsById(id)) {
			throw new ResourceNotFoundException("ProductId " + id + " not found");
		}
		productRepository.deleteById(id);
	}

	@Override
	public List<ProductSize> getProductDetails(long productId) {

		return productSizeRepository.findByProductId(productId);
	}

	@Override
	public ProductSize addProductDetails(long productId, ProductSize productDetails) {
		Optional<Product> product = productRepository.findById(productId);
		if (!product.isPresent()) {
			throw new ResourceNotFoundException("ProductId " + productId + " not found");
		}
		if(productSizeRepository.existsByProductIdAndSize(productId, productDetails.getSize())) {
			throw new ResourceAlreadyExistException(new Date(), "هذا المقاس يوجد بالفعل", "تم اضافة هذا المقاس من قبل");
		}
		productDetails.setProduct(product.get());
		return productSizeRepository.save(productDetails);
	}

	@Override
	public ProductSize updateProductDetails(long productDetailsId, ProductSize productSize) {
		Optional<ProductSize> existingProductSize = productSizeRepository.findById(productDetailsId);
		if (!existingProductSize.isPresent()) {
			throw new ResourceNotFoundException("ProductDetails " + productDetailsId + " not found");
		}
//		existingProductDetails.get().setAmount(productSize.getAmount());
		existingProductSize.get().setSize(productSize.getSize());
		existingProductSize.get().setPrice(productSize.getPrice());
		existingProductSize.get().setWholesalePrice(productSize.getWholesalePrice());
		existingProductSize.get().setNotes(productSize.getNotes());
		return productSizeRepository.save(existingProductSize.get());
	}

	@Override
	public void deleteProductDetails(long productDetailsId) {
		if (!productSizeRepository.existsById(productDetailsId)) {
			throw new ResourceNotFoundException("productDetailsId " + productDetailsId + " not found");
		}
		productSizeRepository.deleteById(productDetailsId);
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll(Sort.by("name"));
	}

}
