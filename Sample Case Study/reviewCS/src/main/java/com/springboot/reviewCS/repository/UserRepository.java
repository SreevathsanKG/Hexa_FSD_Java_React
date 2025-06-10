package com.springboot.reviewCS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.reviewCS.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	@Query("select u from User u where u.username=?1")
	User getUserByUsername(String username);
}
