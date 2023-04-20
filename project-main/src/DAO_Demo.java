
//STEP 1. Import required packages
import java.util.Scanner;

public class DAO_Demo {
	public static DAO_Factory daoFactory;

	public static void main(String[] args) {
		try {
			daoFactory = new DAO_Factory();

			int userlogin;

			try {
				Scanner scanner = new Scanner(System.in);
				System.out.println("*********WELCOME TO FakeLMS*********\n");
				System.out.println("Whom would you like to log in as? \n 1)Student \n 2)Professor");
				userlogin = scanner.nextInt();

				String userID;

				if (userlogin == 1) {
					System.out.println("Enter username(Student_ID): ");
					userID = scanner.next();
					Student received = verifyStudentLogin(userID);
					if (received != null) {
						System.out.println("\nLogin Successful!\n");
						int choice = 0;

						while (choice != 6) {
							System.out.println(
									"\nWhat would you like to do?\n1)View eligible courses to enroll \n2)View my courses \n3)Enroll for a course \n4)View Courses offered by a Professor \n5)View Grades/Transcript\n6)Logout\n \nEnter choice: ");
							choice = scanner.nextInt();
							if (choice == 1) {
								usecase1(received);
							} else if (choice == 2) {
								usecase2(received);
							} else if (choice == 3) {
								usecase3(received);
							} else if (choice == 4) {
								usecase4(received);
							} else if (choice == 5) {
								usecase5(received);
							}
						}

					} else {
						System.out.println("Invalid login. Please try again\n");
					}
				}
				if (userlogin == 2) {
					System.out.println("Enter username(Professor_ID): ");
					userID = scanner.next();
					Professor received = verifyProfessorLogin(userID);
					if (received != null) {
						System.out.println("\nLogin Successful!\n");
						int choice = 0;

						while (choice != 4) {
							System.out.println(
									"\nWhat would you like to do?\n1)Add performance report of a student \n2)Create a new course \n3)View all the students of a particular course \n4)Logout\n \nEnter choice: ");
							choice = scanner.nextInt();
							if (choice == 1) {
								usecase6(received);
							} else if (choice == 2) {
								usecase7(received);
							} else if (choice == 3) {
								usecase8(received);
							}
						}

					} else {
						System.out.println("Invalid login. Please try again\n");
					}
				}
				scanner.close();

			} catch (Exception e) {
				// Handle errors for Class.forName
				e.printStackTrace();
			}
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
	}

	// end main

	public static Student verifyStudentLogin(String id) {
		try {
			daoFactory.activateConnection();
			StudentDAO sdao = daoFactory.getStudentDAO();
			Student s = sdao.getStudentByKey(id);
			daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.ROLLBACK);
			return s;
		} catch (Exception e) {
			// End transaction boundary with failure
			daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.ROLLBACK);
			e.printStackTrace();
		}
		Student tmp = new Student();
		return tmp;
	}

	public static Professor verifyProfessorLogin(String id) {
		try {
			daoFactory.activateConnection();
			ProfessorDAO pdao = daoFactory.getProfessorDAO();
			Professor p = pdao.getProfessorByKey(id);
			daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.ROLLBACK);
			return p;
		} catch (Exception e) {
			// End transaction boundary with failure
			daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.ROLLBACK);
			e.printStackTrace();
		}
		Professor tmp = new Professor();
		return tmp;
	}

	public static void usecase1(Student s) {
		try {
			daoFactory.activateConnection();
			StudentDAO sdao = daoFactory.getStudentDAO();
			sdao.getEligibleCourses(s);
			daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.COMMIT);
		} catch (Exception e) {
			// End transaction boundary with failure
			daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.ROLLBACK);
			e.printStackTrace();
		}
	}

	public static void usecase2(Student s) {
		try {
			daoFactory.activateConnection();
			StudentDAO sdao = daoFactory.getStudentDAO();
			sdao.viewMyCourses(s);
			daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.COMMIT);
		} catch (Exception e) {
			// End transaction boundary with failure
			daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.ROLLBACK);
			e.printStackTrace();
		}
	}

	public static void usecase3(Student s) {
		try {
			daoFactory.activateConnection();
			StudentDAO sdao = daoFactory.getStudentDAO();
			sdao.EnrollForCourse(s);
			daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.COMMIT);
		} catch (Exception e) {
			// End transaction boundary with failure
			daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.ROLLBACK);
			e.printStackTrace();
		}
	}

	public static void usecase4(Student s) {
		try {
			daoFactory.activateConnection();
			StudentDAO sdao = daoFactory.getStudentDAO();
			sdao.viewCoursesbyProf(s);
			daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.COMMIT);
		} catch (Exception e) {
			// End transaction boundary with failure
			daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.ROLLBACK);
			e.printStackTrace();
		}
	}

	public static void usecase5(Student s) {
		try {
			daoFactory.activateConnection();
			StudentDAO sdao = daoFactory.getStudentDAO();
			sdao.getTranscript(s);
			daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.COMMIT);
		} catch (Exception e) {
			// End transaction boundary with failure
			daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.ROLLBACK);
			e.printStackTrace();
		}
	}

	public static void usecase6(Professor p) {
		try {
			daoFactory.activateConnection();
			ProfessorDAO pdao = daoFactory.getProfessorDAO();
			pdao.addPerformance();
			daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.COMMIT);
		} catch (Exception e) {
			// End transaction boundary with failure
			daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.ROLLBACK);
			e.printStackTrace();
		}
	}

	public static void usecase7(Professor p) {
		try {
			daoFactory.activateConnection();
			ProfessorDAO pdao = daoFactory.getProfessorDAO();
			pdao.addCourse(p);
			daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.COMMIT);
		} catch (Exception e) {
			// End transaction boundary with failure
			daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.ROLLBACK);
			e.printStackTrace();
		}
	}

	public static void usecase8(Professor p) {
		try {
			daoFactory.activateConnection();
			ProfessorDAO pdao = daoFactory.getProfessorDAO();
			pdao.getStudentsFromCourse();
			daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.COMMIT);
		} catch (Exception e) {
			// End transaction boundary with failure
			daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.ROLLBACK);
			e.printStackTrace();
		}
	}
}
