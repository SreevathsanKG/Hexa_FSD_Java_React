package com.hibernate.main;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hibernate.main.dto.ContainerDto;
import com.hibernate.main.model.Learner;
import com.hibernate.main.service.CourseService;
import com.hibernate.main.service.LearnerService;

public class App {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		LearnerService learnerService = context.getBean(LearnerService.class);
		CourseService courseService = context.getBean(CourseService.class);
		
		while(true) {
			System.out.println(".............OPS..............");
			System.out.println("1. Insert learner");
			System.out.println("2. Get All Learner");
			System.out.println("3. Get Learner by ID");
			System.out.println("4. Edit Learner");
			System.out.println("5. Delete  Learner by ID");
			System.out.println("6. Get Course by ID");
			System.out.println("0. Exit");
			System.out.println("..............................");
			int choice = sc.nextInt();
			if(choice == 0) {
				System.out.println("Exiting............Thank You");
				break;
			}
			switch(choice) {
			case 1:
				System.out.println("Enter Name");
				sc.nextLine();
				String name = sc.nextLine();
				System.out.println("Enter Email");
				String email = sc.nextLine();
				System.out.println("Enter Contact");
				String contact = sc.nextLine();
				learnerService.insert(name, email, contact);				
				System.out.println("Record Inserted.....");
				break;
			case 2:
				List<Learner> list = learnerService.getAll();
				list.stream().forEach(l->System.out.println(l));
				break;
			case 3:
				System.out.println("Enter Learner ID");
				try {
					Learner learner = learnerService.getById(sc.nextInt());
					System.out.println(learner);
				} catch (RuntimeException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				System.out.println("Enter Learner ID to Update");
				try {
					Learner learner = learnerService.getById(sc.nextInt());
					System.out.println("Existing Learner: "+learner);
					System.out.println("Enter new Name or 00 to skip");
					sc.nextLine();
					String nameVal = sc.nextLine();
					if(!nameVal.equals("00"))
						learner.setName(nameVal);
					System.out.println("Enter new Email or 00 to skip");
					String emailVal = sc.nextLine();
					if(!emailVal.equals("00"))
						learner.setEmail(emailVal);
					System.out.println("Enter new Contact or 00 to skip");
					String contactVal = sc.nextLine();
					if(!contactVal.equals("00"))
						learner.setContact(contactVal);
					learnerService.update(learner);
					System.out.println("Learner Updated......");
				} catch (RuntimeException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				System.out.println("Enter Learner ID");
				try {
					learnerService.delete(sc.nextInt());
					System.out.println("Learner Deleted");
				} catch (RuntimeException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 6:
				System.out.println("Enter Course ID");
				ContainerDto dto = courseService.getById(sc.nextInt());
				System.out.println(dto);
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
