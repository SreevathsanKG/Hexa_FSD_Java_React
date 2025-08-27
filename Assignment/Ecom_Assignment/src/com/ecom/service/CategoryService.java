package com.ecom.service;

import java.util.List;

import com.ecom.dao.CategoryDao;
import com.ecom.dao.impl.CategoryDaoImpl;
import com.ecom.exception.InvalidInputException;
import com.ecom.model.Category;

public class CategoryService {

	CategoryDao categoryDao = new CategoryDaoImpl();

	public void insert(Category category) throws InvalidInputException {
		if (category == null) {
			throw new InvalidInputException("Category should not be null");
		}
		categoryDao.insert(category);

	}

	public List<Category> getAll() {
		return categoryDao.getAll();
	}
}
