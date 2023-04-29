import java.util.Scanner;

public class DAO_Demo {
	public static DAO_Factory daoFactory;

	public static void main(String[] args) {
		try {
			daoFactory = new DAO_Factory();

			int userlogin;

			try {
				Scanner scanner = new Scanner(System.in);
				System.out.println("\n*********WELCOME TO FakeLMS*********\n");
				System.out.println("Whom would you like to log in as? \n 1)Student \n 2)Professor");
				userlogin = scanner.nextInt();

				Integer Student_ID;
				Integer Professor_ID;
				String pass;

				if (userlogin == 1) {
					System.out.println("Enter Student_ID: ");
					Student_ID = scanner.nextInt();
					System.out.println("Enter Password: ");
					pass = scanner.next();
					Student received = verifyStudentLogin(Student_ID, pass);
					if (received != null) {
						System.out.println("\nWelcome, " + received.getName() + "!");
						int choice = 0;

						while (choice != 8) {
							System.out.println(
									"\nWhat would you like to do?\n1)View eligible courses to enroll \n2)View my courses \n3)Enroll for a course \n4)Unenroll from a course \n5)View Courses offered by a Professor \n6)View Grades/Transcript \n7)Contact a TA \n8)Logout\n \nEnter choice: ");
							choice = scanner.nextInt();
							if (choice == 1) {
								usecase1(received);
							} else if (choice == 2) {
								usecase2(received);
							} else if (choice == 3) {
								usecase3(received);
							} else if (choice == 4) {
								usecase9(received);
							} else if (choice == 5) {
								usecase4(received);
							} else if (choice == 6) {
								usecase5(received);
							} else if (choice == 7) {
								usecase11(received);
							}

						}

					} else {
						System.out.println("Invalid login. Please try again\n");
					}
				}
				if (userlogin == 2) {
					System.out.println("Enter Professor_ID: ");
					Professor_ID = scanner.nextInt();
					System.out.println("Enter Password: ");
					pass = scanner.next();
					Professor received = verifyProfessorLogin(Professor_ID, pass);
					if (received != null) {
						System.out.println("\nWelcome, Professor " + received.getName() + "!\n");
						int choice = 0;

						while (choice != 5) {
							System.out.println(
									"\nWhat would you like to do?\n1)Add performance report of a student \n2)Create a new course \n3)View all the students of a particular course \n4)View your courses \n5)Logout\n \nEnter choice: ");
							choice = scanner.nextInt();
							if (choice == 1) {
								usecase6(received);
							} else if (choice == 2) {
								usecase7(received);
							} else if (choice == 3) {
								usecase8(received);
							} else if (choice == 4) {
								usecase10(received);
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

	public static Student verifyStudentLogin(Integer id, String password) {
		Student s;
		try {
			daoFactory.activateConnection();
			StudentDAO sdao = daoFactory.getStudentDAO();
			s = sdao.loginStudent(id, password);
			daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.COMMIT);
			return s;
		} catch (Exception e) {
			// End transaction boundary with failure
			daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.ROLLBACK);
			e.printStackTrace();
		}
		return null;
	}

	public static Professor verifyProfessorLogin(Integer id, String password) {
		Professor p;
		try {
			daoFactory.activateConnection();
			ProfessorDAO pdao = daoFactory.getProfessorDAO();
			p = pdao.loginProfessor(id, password);
			daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.COMMIT);
			return p;
		} catch (Exception e) {
			// End transaction boundary with failure
			daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.ROLLBACK);
			e.printStackTrace();
		}
		return null;
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
			pdao.addPerformance(p);
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

	public static void usecase9(Student s) {
		try {
			daoFactory.activateConnection();
			StudentDAO sdao = daoFactory.getStudentDAO();
			sdao.UnenrollForCourse(s);
			daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.COMMIT);
		} catch (Exception e) {
			// End transaction boundary with failure
			daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.ROLLBACK);
			e.printStackTrace();
		}
	}

	public static void usecase10(Professor p) {
		try {
			daoFactory.activateConnection();
			ProfessorDAO pdao = daoFactory.getProfessorDAO();
			pdao.viewMyCourses(p);
			daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.COMMIT);
		} catch (Exception e) {
			// End transaction boundary with failure
			daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.ROLLBACK);
			e.printStackTrace();
		}
	}

	public static void usecase11(Student s) {
		try {
			daoFactory.activateConnection();
			StudentDAO sdao = daoFactory.getStudentDAO();
			sdao.ContactTA(s);
			daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.COMMIT);
		} catch (Exception e) {
			// End transaction boundary with failure
			daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.ROLLBACK);
			e.printStackTrace();
		}
	}
}
