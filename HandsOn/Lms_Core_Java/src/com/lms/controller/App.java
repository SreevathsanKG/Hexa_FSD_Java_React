package com.lms.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.lms.exception.InvalidIdException;
import com.lms.exception.InvalidInputException;
import com.lms.exception.UserNotFoundException;
import com.lms.model.Course;
import com.lms.model.Learner;
import com.lms.model.Track;
import com.lms.model.User;
import com.lms.service.CourseService;
import com.lms.service.EnrollService;
import com.lms.service.LearnerService;
import com.lms.service.UserService;

public class App {
	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		LearnerService learnerService = new LearnerService();
		CourseService courseService = new CourseService();
		EnrollService enrollService = new EnrollService();
		UserService userService = new UserService();
		Learner learner = new  Learner();
		Track track = new Track();
		Course course = new Course();
		User user = new User();
		
		while(true) {
			System.out.println("..........AUTH..........");
			System.out.println("1. Login");
			System.out.println("2. SignUp");
			System.out.println("0. Exit");
			System.out.println("........................");
			int input = sc.nextInt();
			System.out.println("........................");
			if(input==0) {
				System.out.println("Exiting.....Thank You");
				break;
			}
			switch(input) {
			case 1:
				System.out.println("..........Login..........");
				System.out.println("Enter Username");
				String username = sc.next();
				System.out.println("Enter Password");
				String password = sc.next();
				try {
					user = userService.login(username,password);
					System.out.println("Welcome "+username+", You have logged in!!!");
				} catch (UserNotFoundException e) {
					System.out.println(e.getMessage());
					break;
				}
				System.out.println(".........................");
				break;
			case 2:
//				System.out.println(".........SignUp.........");
//				System.out.println("Enter Username");
//				String username = sc.next();
//				System.out.println("Enter Password");
//				String password = sc.next();
				break;
			default:
				System.out.println("Invalid Input");
				continue;
			}
			if(user!=null) {
				if(user.getRole().toString().equals("LEARNER")) {					
					System.out.println("********************MAIN LMS MENU****************");
					System.out.println("1. Add Learner");
					System.out.println("2. View All Learner");
					System.out.println("3. Delete Learner");
					System.out.println("4. Edit Learner");
					System.out.println("5. get Learner Info by ID");
					System.out.println("6. Add Track");
					System.out.println("7. Add Course");
					System.out.println("8. View All Course");
					System.out.println("9. Get Course by Track Id");
					System.out.println("10. Enroll Learner to Course");
					System.out.println("0. To Exit");
					System.out.println("********************-------------****************");
				}
				else {
					System.out.println("Here Instructor menu will go");
				}
				
			}
			input = sc.nextInt();
			if(input==0) {
				System.out.println("Exiting........ Thank You ");
				break;
			}
			switch(input) {
			case 1:
				System.out.println("Enter Name");
				sc.nextLine();
				learner.setName(sc.nextLine());
				System.out.println("Enter Email");
				learner.setEmail(sc.nextLine());
				try {
					learnerService.insert(learner);
					System.out.println("Record Inserted");					
				} catch (InvalidInputException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				List<Learner> list = learnerService.getAllLearner();
				list.stream().forEach(l->System.out.println(l));
				break;
			case 3:
				System.out.println("Enter Learner ID");
				try {
					learnerService.deleteById(sc.nextInt());
					System.out.println("Learner Deleted....");
				} catch (InvalidIdException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				System.out.println("Enter Learner ID");
				try {
					learner = learnerService.getById(sc.nextInt());
					System.out.println("Existing Record......");
					System.out.println(learner);
					System.out.println("Enter Name");
					sc.nextLine();
					String name=sc.nextLine();
					System.out.println("Enter Email");
					String email=sc.nextLine();
					learnerService.update(learner,name,email);
					System.out.println("Learner record Updated.....");
				} catch (InvalidIdException | InvalidInputException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				System.out.println("Enter Learner ID ");
				try {
					learner = learnerService.getById(sc.nextInt());
					System.out.println(learner);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 6:
				System.out.println("Enter Track name");
				sc.nextLine();
				track.setName(sc.nextLine());
				courseService.insertTrack(track);
				System.out.println("Track added in DB");
				break;
			case 7:
				System.out.println("Enter title");
				sc.nextLine();
				course.setTitle(sc.nextLine());
				System.out.println("Enter fee");
				course.setFee(sc.nextDouble());
				System.out.println("Enter discount");
				course.setDiscount(sc.nextDouble());
				System.out.println("Enter track id");
				int trackId = sc.nextInt();
				courseService.insertCourse(course,trackId);
				System.out.println("Course added in DB");
				break;
			case 8:
				courseService.getAllCourse().stream().forEach(c->{
					System.out.println(c.getId() + "\t" 
							+ c.getTitle() + "\t"
							+ c.getFee() + "\t" 
							+ c.getDiscount() + "\t"
							+ c.getTrack().getId() + "\t"
							+ c.getTrack().getName());}
						);
				break;
			case 9:
				System.out.println("Enter Track Id");
				courseService.getByTrackId(sc.nextInt()).stream().forEach(c->{
					System.out.println(c.getId() + "\t" 
							+ c.getTitle() + "\t"
							+ c.getFee() + "\t" 
							+ c.getDiscount() + "\t"
							+ c.getTrack().getId() + "\t"
							+ c.getTrack().getName());}
						);
				break;
			case 10:
				System.out.println("Enter Learner Id");
				int learnerId = sc.nextInt();
				System.out.println("Enter Course Id");
				int courseId = sc.nextInt();
				try {
					enrollService.enroll(learnerId, courseId,sc);
					System.out.println("Learner Enrolled in Course");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			default:
				System.out.println("Invalid Input");
			}
		}
		sc.close();
	}
}
