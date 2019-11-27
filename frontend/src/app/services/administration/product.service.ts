import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product } from '../../model/product.model';
import { API_URL } from '../../app.constants';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(
    private http:HttpClient
  ) { }

  retrieveAllProducts() {
    return this.http.get<Product[]>(`${API_URL}/products/all`);
  }
  retrieveProducts(page,size,sort) {
    return this.http.get<Product[]>(`${API_URL}/products?page=${page}&size=${size}&sort=${sort}`);
  }

  deleteProduct(productId){
    return this.http.delete(`${API_URL}/products/${productId}`);
  }

  retrieveProduct(productId){
    return this.http.get<Product>(`${API_URL}/products/${productId}`);
  }

  updateProduct(productId, product){
    return this.http.put(
          `${API_URL}/products/${productId}`
                , product);
  }

  createProduct(product){
    return this.http.post(
              `${API_URL}/products`
                , product);
  }


  retrieveProductDetails(productId) {
    return this.http.get<Product[]>(`${API_URL}/products/${productId}/details`);
  }

  addProductDetails(productId, productDetails){
    return this.http.post(
              `${API_URL}/products/${productId}/details`
                , productDetails);
  }

  updateProductDetails(productDetailsId, productDetails){
    return this.http.put(
          `${API_URL}/products/details/${productDetailsId}`
                , productDetails);
  }

  deleteProductDetails(productDetailsId){
    return this.http.delete(`${API_URL}/products/details/${productDetailsId}`);
  }
}
