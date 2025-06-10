package com.springboot.reviewCS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ReviewCsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewCsApplication.class, args);
	}

}
