package com.springboot.reviewCS.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.reviewCS.model.CustomerProduct;

public interface CustomerProductRepository extends JpaRepository<CustomerProduct, Integer>{

	@Query("select cp from CustomerProduct cp where cp.customer.id=?1 and cp.product.id=?2")
	Optional<CustomerProduct> getPurchaseByCustomerProductID(int customerId, int productId);

}
