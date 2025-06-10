package com.springboot.reviewCS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.reviewCS.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
