import java.util.Scanner;
import java.sql.*;
import java.util.Dictionary;
import java.util.Enumeration;
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
	public Student getStudentByKey(String id) {
		Student s = new Student();
		String sql;
		Statement stmt = null;

		try {
			stmt = dbConnection.createStatement();
			sql = "select * from Student where STUDENT_ID = " + id;
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int studentid = rs.getInt("Student_ID");
				// if (studentid == null)
				// break;
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
			sql = "select CourseName, Course_ID from Course where SemOfferedIn = " + s.getSem() + " and (Branch = '"
					+ s.getBranch() + "' or Branch is NULL)";
			ResultSet rs = stmt.executeQuery(sql);

			System.out.println("\n-----------Your eligible courses are------------\n");

			while (rs.next()) {
				System.out.println(
						"Course Name = " + rs.getString("CourseName") + " ,Course_ID = " + rs.getString("Course_ID"));
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
		Statement stmt = null;
		try {
			int id = s.getStudentID();
			stmt = dbConnection.createStatement();
			sql = "select distinct c.Course_ID, c.CourseName from Course c, Enrollment e where e.Course_ID=c.Course_ID and e.Student_ID="
					+ id;// enrollment query
			ResultSet rs = stmt.executeQuery(sql);

			System.out.println("\n--------------------Your courses are------------------------\n");

			while (rs.next()) {
				System.out.println(
						"Course Name = " + rs.getString("CourseName") + " ,Course_ID = " + rs.getString("Course_ID"));
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
		String sql, sql_valid;
		Statement stmt = null;
		PreparedStatement preparedStatement = null;
		sql = "insert into Enrollment(Course_ID, Student_ID, Type) values (?, ?, ?)";// enrollment query
		sql_valid = "select CourseName from Course where CourseName in ( select CourseName from Course where SemOfferedIn = "
				+ s.getSem() + " and (Branch = '" + s.getBranch() + "' or Branch is NULL))";// names of all eligible
																							// courses
		try {
			preparedStatement = dbConnection.prepareStatement(sql);
			stmt = dbConnection.createStatement();
			Scanner scanner = new Scanner(System.in);
			Course c = new Course();

			String coursename;
			int flg = 0;
			System.out.println("Enter Course Name:");
			coursename = scanner.nextLine();
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
					preparedStatement.setString(3, c.getType());

					preparedStatement.executeUpdate();

					System.out.println("\n-----------Succesfully enrolled for course------------\n");

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
		PreparedStatement preparedStatement = null; Statement stmt_valid = null;
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter course name: ");
			String coursename = scanner.next();
			
			stmt_valid = dbConnection.createStatement();
			sql_valid = "select distinct c.CourseName from Course c, Enrollment e where e.Course_ID=c.Course_ID and e.Student_ID=" + s.getStudentID();
			ResultSet rs1 = stmt_valid.executeQuery(sql_valid);
			while(rs1.next())
			{
				if(rs1.getString("CourseName").equals(coursename))
				{
					flg = 1;
					sql = "delete e from Enrollment e inner join Course c on e.Course_ID=c.Course_ID and c.CourseName='"+ coursename + "'";// enrollment query
					preparedStatement = dbConnection.prepareStatement(sql);
					preparedStatement.executeUpdate();
					System.out.println("Succesfully deleted " + coursename + " from your courses");
					break;
				}
			}
			if (flg == 0)
			{
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
			sql = "select c.Course_ID, c.CourseName from Course c, Professor p where c.Professor_ID = p.Professor_ID and p.Name = ?";// enrollment
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
							"Course Name = " + rs.getString("CourseName") + " ,Course_ID = "
									+ rs.getString("Course_ID"));
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
		Statement stmt = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement1 = null;
		try {
			Scanner scanner = new Scanner(System.in);
			coursename = scanner.next();
			stmt = dbConnection.createStatement();
			sql = "select distinct c.CourseName from Course c, Enrollment e where e.Course_ID=c.Course_ID and e.Student_ID=" + s.getStudentID();//only contact your course TA's

			preparedStatement = dbConnection.prepareStatement(sql);
			
			int flag = 0;
			ResultSet rs1 = preparedStatement.executeQuery();
			while(rs1.next())
			{
				if(rs1.getString("CourseName").equals(coursename))
				{
					flag = 1;
					sql_getTA = "select s.Name, s.Email from Student s, TA t, Course c where c.Course_ID = t.Course_ID and t.Student_ID = s.Student_ID and c.CourseName = ?";
					preparedStatement1 = dbConnection.prepareStatement(sql_getTA);
					preparedStatement1.setString(1, coursename);
					ResultSet rs = preparedStatement1.executeQuery(sql_getTA);
					System.out.println("--------------TA's for " + coursename + "-----------\n");
					while(rs.next())
					{
						System.out.println("Name of TA: " + rs.getString("Name") + " ,Email: " + rs.getString("Email\n"));
					}
					break;
				}
			}
			if(flag == 0)
				System.out.println("This course does not exist.\n");


		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

}
