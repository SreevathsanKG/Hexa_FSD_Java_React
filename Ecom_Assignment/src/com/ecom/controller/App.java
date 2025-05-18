package com.ecom.controller;

import java.util.Scanner;

import com.ecom.exception.InvalidCouponException;
import com.ecom.exception.InvalidIdException;
import com.ecom.exception.InvalidInputException;
import com.ecom.model.Product;
import com.ecom.service.ProductService;
import com.ecom.service.PurchaseService;

public class App {
	
	public static void main(String[] args) throws InvalidIdException, InvalidCouponException {
		
		Scanner sc = new Scanner(System.in);
		ProductService productService = new ProductService();
		PurchaseService purchaseService = new PurchaseService();
		Product product = new Product();
		
		while(true) {
			System.out.println("1. Add Product");
			System.out.println("2. Get Product by Category Id");
			System.out.println("3. Add Purchase");
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
			default:
				System.out.println("Invalid Input");
			}
		}
	}
}
