//STEP 1. Import required packages
import java.util.Scanner;

public class DAO_Demo 
{
	public static DAO_Factory daoFactory;
	public static void main(String[] args) {
		try{
			daoFactory = new DAO_Factory();

			int userlogin;
			
			try
			{
				Scanner scanner = new Scanner(System.in);
				System.out.println("*********WELCOME TO FakeLMS*********\n");
				System.out.println("Whom would you like to log in as? \n 1) Student \n 2)Professor");
				userlogin = scanner.nextInt();

				String userID;
				
				if(userlogin == 1)
				{
					System.out.println("Enter username(Student_ID): ");
					userID = scanner.next();
					Student received = verifyStudentLogin(userID);
					if(received != null)
					{
						int choice;
						System.out.println("Login Successful!\n What would you like to do?\n1)View eligible courses to enroll \n2)View my courses \n3)Enroll for a course \n4)View Courses offered by a Professor \n5)View Grades/Transcript");
						choice = scanner.nextInt();
						while(true)
						{
							if(choice == 1)
							{
								usecase1(received);
							}
							else if(choice == 2)
							{
								usecase2(received);
							}
							else if(choice == 3)
							{
								usecase3(received);
							}
							else if(choice == 4)
							{
								usecase4(received);
							}
							else if(choice == 5)
							{
								usecase5(received);
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
				scanner.close();

			}catch(Exception e){
				//Handle errors for Class.forName
				e.printStackTrace();
			}
		}
		catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
	}

	//end main

	public static Student verifyStudentLogin(String id)
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
		Student tmp = new Student();
		return tmp;
	}

	public static void usecase1(Student s)
	{
		try{
			daoFactory.activateConnection();
			StudentDAO sdao = daoFactory.getStudentDAO();
			sdao.getEligibleCourses(s);
			}
			catch(Exception e){
				// End transaction boundary with failure
				daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
				e.printStackTrace();
			}
	}

	public static void usecase2(Student s)
	{
		try{
			daoFactory.activateConnection();
			StudentDAO sdao = daoFactory.getStudentDAO();
			sdao.viewMyCourses(s);
			}
			catch(Exception e){
				// End transaction boundary with failure
				daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
				e.printStackTrace();
			}
	}

	public static void usecase3(Student s)
	{
		try{
			daoFactory.activateConnection();
			StudentDAO sdao = daoFactory.getStudentDAO();
			sdao.EnrollForCourse(s);
			}
			catch(Exception e){
				// End transaction boundary with failure
				daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
				e.printStackTrace();
			}
	}

	public static void usecase4(Student s)
	{
		try{
			daoFactory.activateConnection();
			StudentDAO sdao = daoFactory.getStudentDAO();
			sdao.viewCoursesbyProf(s);
			}
			catch(Exception e){
				// End transaction boundary with failure
				daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
				e.printStackTrace();
			}
	}
	public static void usecase5(Student s)
	{
		try{
			daoFactory.activateConnection();
			StudentDAO sdao = daoFactory.getStudentDAO();
			sdao.getTranscript(s);
			}
			catch(Exception e){
				// End transaction boundary with failure
				daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
				e.printStackTrace();
			}
	}

	

}
	