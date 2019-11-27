package com.dream.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dream.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
