package com.springboot.reviewCS.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.reviewCS.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	@Query("select c from Customer c where c.user.username=?1")
	Optional<Customer> getCustomerByUsername(String username);
}
