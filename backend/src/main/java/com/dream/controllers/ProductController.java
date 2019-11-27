package com.dream.controllers;

import java.util.List;

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

import com.dream.models.Product;
import com.dream.models.ProductSize;
import com.dream.services.ProductService;

@CrossOrigin(origins = "*")
@RestController
public class ProductController {
	@Autowired
	private ProductService productService;

	
	@GetMapping("/products/all")
	public List<Product> retrieveAllProducts() {
		return productService.getAllProducts();
	}
	@GetMapping("/products")
	public Page<Product> retrieveProducts(Pageable pageable) {
		return productService.getProducts(pageable);
	}

	@GetMapping("/products/{productId}")
	public Product retrieveProductById(@PathVariable long productId) {
		return productService.getProductById(productId);
	}

	@DeleteMapping("/products/{productId}")
	public void deleteProduct(@PathVariable long productId) {
		productService.deleteProduct(productId);
	}

	@PostMapping("/products")
	public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {

		Product createdProduct = productService.createProduct(product);
		return new ResponseEntity<Product>(createdProduct, HttpStatus.OK);

	}

	@PutMapping("/products/{productId}")
	public ResponseEntity<Product> updateProduct(@PathVariable long productId, @Valid @RequestBody Product product) {
		Product updatedProduct = productService.updateProduct(productId, product);
		return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
	}
	
	@GetMapping("/products/{productId}/details")
	public List<ProductSize> retrieveProductDetails(@PathVariable long productId) {
		return productService.getProductDetails(productId);
	}
	
	@PostMapping("/products/{productId}/details")
	public ResponseEntity<ProductSize> addProductDetails(@PathVariable long productId, @Valid @RequestBody ProductSize productDetails) {
		ProductSize createdProductDetails = productService.addProductDetails(productId, productDetails);
		return new ResponseEntity<ProductSize>(createdProductDetails, HttpStatus.OK);

	}
	
	@PutMapping("/products/details/{productDetailsId}")
	public ResponseEntity<ProductSize> updateProductDetails(@PathVariable long productDetailsId, @Valid @RequestBody ProductSize productDetails) {
		ProductSize updatedProductDetails = productService.updateProductDetails(productDetailsId, productDetails);
		return new ResponseEntity<ProductSize>(updatedProductDetails, HttpStatus.OK);
	}
	
	@DeleteMapping("/products/details/{productDetailsId}")
	public void deleteProductDetails(@PathVariable long productDetailsId) {
		productService.deleteProductDetails(productDetailsId);
	}
}
