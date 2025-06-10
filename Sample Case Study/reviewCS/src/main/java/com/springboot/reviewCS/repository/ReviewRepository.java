package com.springboot.reviewCS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.reviewCS.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>{

	@Query("select r from Review r where r.product.id=?1")
	List<Review> getReviewsByProduct(int productId);
}
