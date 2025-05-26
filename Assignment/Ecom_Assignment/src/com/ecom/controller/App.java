package com.ecom.controller;

import java.util.List;
import java.util.Scanner;

import com.ecom.exception.InvalidCouponException;
import com.ecom.exception.InvalidIdException;
import com.ecom.exception.InvalidInputException;
import com.ecom.model.Category;
import com.ecom.model.Customer;
import com.ecom.model.Product;
import com.ecom.service.CategoryService;
import com.ecom.service.CustomerService;
import com.ecom.service.ProductService;
import com.ecom.service.PurchaseService;

public class App {
	
	public static void main(String[] args) throws InvalidIdException, InvalidCouponException {
		
		Scanner sc = new Scanner(System.in);
		CategoryService categoryService = new CategoryService();
		CustomerService customerService = new CustomerService();
		ProductService productService = new ProductService();
		PurchaseService purchaseService = new PurchaseService();
		Product product = new Product();
		Customer customer = new Customer();
		
		while(true) {
			System.out.println("1. Add Product");					// Asper the assignment
			System.out.println("2. Get Product by Category Id");	// these 3 are the required
			System.out.println("3. Purchase Product");				// Operations to perform 
			System.out.println("4. Get Customer by Id");
			System.out.println("5. Get Product by Id");
			System.out.println("6. Add Customer");
			System.out.println("7. Get all Customer");
			System.out.println("8. Get all Category");
			System.out.println("9. Get all product");
			System.out.println("10. Add Category");
			System.out.println("0. Exit.....");
			int choice = sc.nextInt();
			if(choice==0) {
				System.out.println("Exiting......Thank You");
				break;
			}
			switch(choice) {
			case 1:
				sc.nextLine();
				System.out.println("Enter Product Name");
				product.setName(sc.nextLine());
				System.out.println("Enter Product Price");
				product.setPrice(sc.nextDouble());
				System.out.println("Enter Category ID");
				int categoryId =  sc.nextInt();
				try {
					productService.insert(product, categoryId);
					System.out.println("Product Inserted");
				} catch (InvalidInputException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				System.out.println("Enter Category ID");
				try {
					productService.getByCategoryId(sc.nextInt()).stream().forEach(p->{
						System.out.println(p.getId() + "\t"
								+ p.getName() + "\t"
								+ p.getPrice() + "\t"
								+ p.getCategory().getId() + "\t"
								+ p.getCategory().getName());
					});
					/*
					productService.getByCategoryId(sc.nextInt()).stream().forEach(p->{
						System.out.println(p);
					});
					*/
				} catch (InvalidIdException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				System.out.println("Enter Customer ID");
				int customerId = sc.nextInt();
				System.out.println("Enter Product ID");
				int productId = sc.nextInt();
				purchaseService.insert(customerId, productId, sc);
				System.out.println("Purchase done by Customer");
				break;
			case 4:
				System.out.println("Enter Customer Id");
				int idC = sc.nextInt();
				customer = customerService.getById(idC);
				System.out.println(customer);
				break;
			case 5:
				System.out.println("Enter Product Id");
				int idP = sc.nextInt();
				product = productService.getById(idP);
				System.out.println(product);
				break;
			case 6:
				System.out.println("Enter name:");
				customer.setName(sc.nextLine());
				System.out.println("Enter city: ");
				customer.setCity(sc.nextLine());
				try {
					customerService.insert(customer);	
				}
				catch(InvalidInputException e) {
					System.out.println(e.getMessage());
				}
				System.out.println("Record Added....");
				break;
			case 7:
				List<Customer> list =customerService.getAll();
				list.stream().forEach(c-> System.out.println(c));
				break;
			case 8:
				List<Category> list1 =categoryService.getAll();
				list1.stream().forEach(l-> System.out.println(l));
				break;
			case 9:
				List<Product> list2 = productService.getAll();
				list2.stream().forEach(p-> System.out.println(p));
				break;
			case 10:
				break;
			default:
				System.out.println("Invalid Input");
			}
		}
	}
}
