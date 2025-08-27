package com.test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ecom.exception.InvalidIdException;
import com.ecom.exception.InvalidInputException;
import com.ecom.model.Category;
import com.ecom.model.Product;
import com.ecom.service.ProductService;

public class ProductServiceTest {

	ProductService productService;

	@BeforeEach
	public void init() {
		productService = new ProductService();
	}

	@Test
	public void testInsertProduct() {
		// Use Case 1: Insert valid product - should not throw exception
		Category category = new Category(1, "Electronics");
		Product product = new Product(101, "Smartphone", 15000.0, category);
		assertDoesNotThrow(() -> productService.insert(product, category.getId()));

		// Use Case 2: Insert null product - should throw InvalidInputException
		InvalidInputException ex = assertThrows(InvalidInputException.class, () -> {
			productService.insert(null, 0);
		});
		assertEquals("Product should not be null", ex.getMessage());
	}

	@Test
	public void testGetAllProducts() {
		// Use Case 3: Get all products - should return a non-null list
		List<Product> products = productService.getAll();
		assertNotNull(products);
	}

	@Test
	public void testGetByCategoryId() {
		// Use Case 4: Valid category ID - should not throw
		Category category = new Category(2, "Appliances");
		assertDoesNotThrow(() -> productService.getByCategoryId(category.getId()));		
	}

	@Test
	public void testGetById() {
		// Use Case 5: Valid product ID - should not throw exception
		assertDoesNotThrow(() -> productService.getById(1));

		// Use Case 6: Negative product ID - should throw InvalidIdException
		assertThrows(InvalidIdException.class, () -> productService.getById(-10));
	}
}
