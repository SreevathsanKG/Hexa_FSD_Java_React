package com.springcore.main;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springcore.main.model.Address;
import com.springcore.main.model.Customer;
import com.springcore.main.model.PolicyHolder;
import com.springcore.main.service.CustomerService;
import com.springcore.main.service.PolicyHolderService;

public class App {

	public static void main(String[] args) {
		
		/* Reaching out to my Configuration class */
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		CustomerService cs = context.getBean(CustomerService.class);
		PolicyHolderService phs = context.getBean(PolicyHolderService.class);
		PolicyHolder ph = context.getBean(PolicyHolder.class);
		
		Scanner sc = new Scanner(System.in);
				
		while(true) {
			System.out.println("************** Customer menu **************");
			System.out.println("1. Add customer");
			System.out.println("2. Get all customers");
			System.out.println("3. Get customer by id");
			System.out.println("4. Update customer details");
			System.out.println("5. Delete customer");
			System.out.println("6. Create customer table");
			System.out.println("7. Create PolicyHolder account with Address");
			System.out.println("8. Get all PolicyHolder with Address Info");
			System.out.println("0. Exit");
			System.out.println("************** _____________ **************");
			int choice = sc.nextInt();
			if(choice==0) {
				System.out.println("Exiting..........Thank you");
				break;
			}
			switch(choice) {
				case 1:
					System.out.println("Enter your name");
					sc.nextLine();
					String addName=sc.nextLine();
					System.out.println("Enter your city");
					String addCity=sc.nextLine();
					cs.insertCustomer(addName, addCity);
					break;
				case 2:
					List<Customer> list = cs.getAllv2();
					list.stream().forEach(c->System.out.println(c));
					break;
				case 3:
					System.out.println("Enter Customer Id");
					try {
						Customer customer = cs.getById(sc.nextInt());
						System.out.println(customer);
					} catch (Exception e) {
						System.out.println("Invalid Id!! Could not fetch record");
					}
					break;
				case 4:
					System.out.println("Enter Customer Id to Update");
					try {
						Customer customer = cs.getById(sc.nextInt());
						System.out.println("Existing Customer: "+customer);
						System.out.println("Enter new name or 00 to skip");
						sc.nextLine();
						String nameVal = sc.nextLine();
						if(!nameVal.equals("00"))
							customer.setName(nameVal);
						System.out.println("Enter new city or 00 to skip");
						String cityVal = sc.next();
						if(!cityVal.equals("00"))
							customer.setCity(cityVal);
						cs.update(customer);
						System.out.println("Customer Record Updated");
					} catch (Exception e) {
						System.out.println("Invalid Id!! Could not fetch record");
					}
					break;
				case 5:
					break;
				case 6:
					cs.createCustomerTable();
					break;
				case 7:
					System.out.println("Enter name:");
					sc.nextLine();
					ph.setName(sc.nextLine());
					System.out.println("Enter Pan:");
					ph.setPanNo(sc.nextLine());
					Address address = new Address();
					System.out.println("Enter Street:");
					address.setStreet(sc.nextLine());
					System.out.println("Enter City:");
					address.setCity(sc.nextLine());
					System.out.println("Enter State:");
					address.setState(sc.nextLine());
					phs.insert(ph, address);
					System.out.println("Policy Holder record created...");
					break;
				case 8:
					List<PolicyHolder> listp = phs.getAllWithAddressv2();
					listp.stream().forEach(p->System.out.println(p));
					break;
				default:
					System.out.println("Invalid Input!!");
					break;
			}
			
		}
		
		
		sc.close();
		context.close();
	}

}