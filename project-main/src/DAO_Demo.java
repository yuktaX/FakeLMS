//STEP 1. Import required packages
import java.sql.*;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner6;

public class DAO_Demo {
	public static DAO_Factory daoFactory;
	public static void main(String[] args) {
		try{
			daoFactory = new DAO_Factory();

			int userlogin;
			try (Scanner scanner = new Scanner(System.in)) {
				System.out.println("*********WELCOME TO FakeLMS*********\n");
				System.out.println("Whom would you like to log in as? \n 1) Student \n 2)Professor");
				userlogin = scanner.nextInt();

				String userID;

				if(userlogin == 1)
				{
					System.out.println("Enter username(Student_ID): ");
					userID = scanner.next();
					if(verifyStudentLogin(userID))
					{
						int choice;
						System.out.println("Login Successful!\n What would you like to do?\n1)View eligible courses to enroll \n2)View my courses \n3)Enroll for a course \n4)View Courses offered by a Professor \n5)View Grades");
						choice = scanner.nextInt();
						while(true)
						{
							if(choice == 1)
							{

							}
							else if(choice == 2)
							{

							}
							else if(choice == 3)
							{

							}
							else if(choice == 4)
							{

							}
							else if(choice == 5)
							{

							}
							else 
								break;
						}
						
					}
					else
					{
						System.out.println("Invalid login. Please try again\n");
					}
				}
				if(userlogin == 2)
				{
					int choice;
					System.out.println("Login Successful!\n What would you like to do?\n1)VAdd a course \n2)Add student performance \n3)View all students in a course");
					choice = scanner.nextInt();
					while(true)
					{
						if(choice == 1)
						{
						}
						else if(choice == 2)
						{
						}
						else if(choice == 3)
						{
						}
						else  
							break;
				}
			}
			

		

		}catch(Exception e){
				//Handle errors for Class.forName
				e.printStackTrace();
		}
	}
	//end main

	public static boolean verifyStudentLogin(String id)
	{
		try{
		daoFactory.activateConnection();
		StudentDAO sdao = daoFactory.getStudentDAO();
		return sdao.getStudentByKey(id);
		}
		catch(Exception e){
			// End transaction boundary with failure
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
		return false;
	}
}
	