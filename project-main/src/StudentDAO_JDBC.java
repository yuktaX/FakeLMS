import java.util.Scanner;
import java.sql.*;
import java.util.Dictionary;
import java.util.Hashtable;

public class StudentDAO_JDBC implements StudentDAO {
	Connection dbConnection;

	public StudentDAO_JDBC(Connection dbconn) {
		// JDBC driver name and database URL
		// Database credentials
		dbConnection = dbconn;
		dict.put("A", (float) 4);
		dict.put("A-", (float) 3.7);
		dict.put("B+", (float) 3.4);
		dict.put("B", (float) 3);
		dict.put("B-", (float) 2.7);
		dict.put("C+", (float) 2.4);
		dict.put("C", (float) 2);
		dict.put("D", (float) 1);
		dict.put("F", (float) 0);
	}

	Dictionary<String, Float> dict = new Hashtable<>();

	@Override
	public Student loginStudent(Integer id, String password) {
		Student s = new Student();
		String sql;
		Statement stmt = null;
		PreparedStatement loginStatement1 = null;
		String loginSql1;

		try {
			loginSql1 = "select Student_ID, Password from StudentLogin where Student_ID = ? and Password = ?";
			loginStatement1 = dbConnection.prepareStatement(loginSql1);
			loginStatement1.setInt(1, id);
			loginStatement1.setString(2, password);
			ResultSet rs1 = loginStatement1.executeQuery();
			if (rs1.next() == false) {
				System.out.println("Login failed");
				return null;
			} else {
				stmt = dbConnection.createStatement();
				sql = "select * from Student where STUDENT_ID = " + id;
				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					// Retrieve by column name
					int studentid = rs.getInt("Student_ID");
					String name = rs.getString("Name");
					String email = rs.getString("Email");
					String branch = rs.getString("Branch");
					String gender = rs.getString("Gender");
					Date dob = rs.getDate("DOB");
					int sem = rs.getInt("CurrentSemester");
					s.setStudentID(studentid);
					s.setName(name);
					s.setDOB(dob);
					s.setBranch(branch);
					s.setEmail(email);
					s.setCurrentSem(sem);
					s.setGender(gender);
					// Add exception handling here if more than one row is returned
				}
				return s;
			}
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			System.exit(1);
		}
		// Add exception handling when there is no matching record
		// return true;
		return s;
	}

	@Override
	public void getEligibleCourses(Student s) {
		String sql;
		Statement stmt = null;
		try {
			stmt = dbConnection.createStatement();
			sql = "select CourseName, Course_ID, Credits from Course where SemOfferedIn = " + s.getSem() + " and (Branch = '"
					+ s.getBranch() + "' or Branch is NULL or Branch = 'gen')";
			ResultSet rs = stmt.executeQuery(sql);

			System.out.println("\n-----------Your eligible courses are------------\n");
			if (rs.next() == false) {
				System.out.println("Not eligible for any courses.");
				return;
			}
			else{
				System.out.println(
							"Course Name = " + rs.getString("CourseName") + "\nCourse_ID = " + rs.getInt("Course_ID") + "\nCredits = " + rs.getInt("Credits")+" \n");
				while (rs.next()) {
					System.out.println(
						"Course Name = " + rs.getString("CourseName") + "\nCourse_ID = " + rs.getInt("Course_ID") + "\nCredits = " + rs.getInt("Credits")+" \n");
				}
			}

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			System.exit(1);
		}
	}

	@Override
	public void viewMyCourses(Student s) {
		String sql;
		String sql2;
		Statement stmt1 = null;
		Statement stmt2 = null;
		try {
			int id = s.getStudentID();
			stmt1 = dbConnection.createStatement();
			sql = "select distinct c.Course_ID, c.CourseName from Course c, Enrollment e where e.Course_ID=c.Course_ID and e.Student_ID="
					+ id;// enrollment query
			ResultSet rs = stmt1.executeQuery(sql);

			System.out.println("\n--------------------Your courses are------------------------\n");

			while (rs.next()) {
				System.out.println(
						"Course Name = " + rs.getString("CourseName") + "\nCourse_ID = " + rs.getInt("Course_ID")+"\n");
			}

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

		try {
			int id2 = s.getStudentID();
			stmt2 = dbConnection.createStatement();
			sql2 = "select sum(Credits) from Course where Course_ID in (select Course_ID from Enrollment where Student_ID="
					+ id2 +")";// enrollment query
			ResultSet rs2 = stmt2.executeQuery(sql2);

			while(rs2.next()){
				System.out.println("Total Credits = "+rs2.getInt("sum(Credits)"));
			}

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	@Override
	public void EnrollForCourse(Student s) {
		String sql, sql_valid, sql_checkenrolled;
		Statement stmt = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;
		sql = "insert into Enrollment(Course_ID, Student_ID) values (?, ?)";// enrollment query
		sql_valid = "select CourseName from Course where SemOfferedIn = "+ s.getSem() + " and (Branch = '" + s.getBranch() + "' or Branch is NULL or Branch = 'gen')";//courses eligible
		sql_checkenrolled = "select e.Course_ID, e.Student_Id, c.CourseName from Enrollment e, Course c where c.Course_ID = e.Course_ID and CourseName = ? and Student_Id = ?";
		
		// make sure student enrolls for eligible courses
		try {
			preparedStatement2 = dbConnection.prepareStatement(sql_checkenrolled);
			preparedStatement = dbConnection.prepareStatement(sql);
			stmt = dbConnection.createStatement();
			Scanner scanner = new Scanner(System.in);
			Course c = new Course();

			String coursename;
			int flg = 0;
			System.out.println("Enter Course Name:");
			coursename = scanner.nextLine();

			preparedStatement2.setString(1, coursename);
			preparedStatement2.setInt(2, s.getStudentID());
			ResultSet rs2 = preparedStatement2.executeQuery();
			while(rs2.next())
			{
				System.out.println("You are already enrolled in course: " + coursename);
				return;
			}
			
			ResultSet rs1 = stmt.executeQuery(sql_valid);
			while (rs1.next()) {
				if (rs1.getString("CourseName").equals(coursename)) {
					flg = -1;
					String sql_getcourse = "select * from Course where CourseName = '" + coursename + "'";
					Statement getcourseinfo;
					getcourseinfo = dbConnection.createStatement();
					ResultSet rs = getcourseinfo.executeQuery(sql_getcourse);
					while (rs.next()) {
						c.setCourseID(rs.getInt("Course_ID"));
						c.setCourseName(rs.getString("CourseName"));
						c.setSemOfferedIn(rs.getInt("SemOfferedIn"));
						c.setCourseCredit(rs.getInt("Credits"));
						c.setType(rs.getString("Type"));
						c.setBranch(rs.getString("Branch"));
						c.setCourseProfessor(rs.getInt("Professor_ID"));

					}

					preparedStatement.setInt(1, c.getCourseID());
					preparedStatement.setInt(2, s.getStudentID());

					preparedStatement.executeUpdate();

					System.out.println("\n-----------Succesfully enrolled for course: " + coursename + "------------\n");

					break;

				}
			}

			if (flg == 0) {
				System.out.println("You are not eligible for this course\n");
			}

		} catch (SQLException ex) {
			// handle any errors
			System.out.println(ex);
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			System.exit(1);
		}

	}

	@Override
	public void UnenrollForCourse(Student s) {
		String sql, sql_valid;
		int flg = 0;
		PreparedStatement preparedStatement = null;
		PreparedStatement stmt_valid = null;
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter course name: ");
			String coursename = scanner.nextLine();

			sql_valid = "select distinct c.CourseName from Course c, Enrollment e where e.Course_ID=c.Course_ID and e.Student_ID=?";
			stmt_valid = dbConnection.prepareStatement(sql_valid);
			stmt_valid.setInt(1, s.getStudentID());
			ResultSet rs1 = stmt_valid.executeQuery();

			while (rs1.next()) {
				if (rs1.getString("CourseName").equals(coursename)) {
					flg = 1;
					sql = "delete e from Enrollment e inner join Course c on e.Course_ID=c.Course_ID and c.CourseName=?";
					preparedStatement = dbConnection.prepareStatement(sql);
					preparedStatement.setString(1, coursename);
					preparedStatement.executeUpdate();
					System.out.println("Succesfully deleted " + coursename + " from your courses");
					break;
				}
			}
			if (flg == 0) {
				System.out.println("You are not enrolled in this course.\n");
			}

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	@Override
	public void getTranscript(Student s) {
		String sql;
		Statement stmt = null;


		try {
			int id = s.getStudentID();
			stmt = dbConnection.createStatement();
			sql = "select c.CourseName, c.Credits, p.Grade from Course c, Performance p where p.Course_ID = c.Course_ID and p.Student_ID ="
					+ id;// enrollment
			// query
			ResultSet rs = stmt.executeQuery(sql);

			System.out.println("\n----------Here is your transcript-----------\n");
			System.out.println("Current Semester = "+s.getSem());

			Integer totalCredits = 0;
			Float gradePoints = (float) 0;
			while (rs.next()) {
				String CourseName = rs.getString("CourseName");
				String Grade = rs.getString("Grade");
				Integer Credits = rs.getInt("Credits");
				System.out.println("Course Name = " + CourseName + " Grade = " + Grade);

				totalCredits += Credits;
				gradePoints += dict.get(Grade) * Credits;
			}

			System.out.println("Your CGPA is: " + (float) gradePoints / totalCredits);

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

	}

	@Override
	public void viewCoursesbyProf(Student s) {
		String sql;
		PreparedStatement preparedStatement = null;

		String sql1;
		PreparedStatement preparedStatement1 = null;
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter Professor name: ");
			String name = scanner.nextLine();
			// stmt = dbConnection.createStatement();
			sql = "select c.Course_ID, c.CourseName, c.SemOfferedIn from Course c, Professor p where c.Professor_ID = p.Professor_ID and p.Name = ?";// enrollment
			sql1 = "select Name from Professor"; // query

			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement1 = dbConnection.prepareStatement(sql1);

			preparedStatement.setString(1, name);

			int flag = 0;
			ResultSet rs1 = preparedStatement1.executeQuery();
			if (rs1.next() == false) {
				System.out.println("There are no Professors in the database :(");
			} else {
				do {
					String data = rs1.getString("Name");
					if (data.equals(name)) {
						flag = 1;
						break;
					}
				} while (rs1.next());
			}

			if (flag == 1) {
				ResultSet rs = preparedStatement.executeQuery();
				System.out.println("\n-----------Courses offered by Prof." + name + "---------------\n");
				while (rs.next()) {
					System.out.println(
							"Course Name = " + rs.getString("CourseName") + "\nCourse_ID = "
									+ rs.getString("Course_ID") +"\nSemester ="+rs.getInt("SemOfferedIn")+"\n");
				}
			} else {
				System.out.println("Given professor doesn't exist");
			}

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	@Override
	public void ContactTA(Student s) {
		String sql, coursename, sql_getTA;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement1 = null;
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter name of course whos TA's you want to contact: ");
			coursename = scanner.nextLine();
			sql = "select distinct c.CourseName from Course c, Enrollment e where e.Course_ID=c.Course_ID and e.Student_ID="
					+ s.getStudentID();// only contact your course TA's

			preparedStatement = dbConnection.prepareStatement(sql);

			int flag = 0;
			ResultSet rs1 = preparedStatement.executeQuery();
			while (rs1.next()) {
				if (rs1.getString("CourseName").equals(coursename)) {
					flag = 1;
					sql_getTA = "select s.Name, s.Email from Student s, TA t, Course c where c.Course_ID = t.Course_ID and t.Student_ID = s.Student_ID and c.CourseName=?";
					preparedStatement1 = dbConnection.prepareStatement(sql_getTA);
					preparedStatement1.setString(1, coursename);
					ResultSet rs = preparedStatement1.executeQuery();
					System.out.println("--------------TA's for " + coursename + "-----------\n");
					while (rs.next()) {
						System.out.println("Name of TA: " + rs.getString("Name") + "\nEmail: " + rs.getString("Email")+"\n");
					}
					break;
				}
			}
			if (flag == 0)
				System.out.println("This course does not exist or you are not enrolled in it.\n");

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

}