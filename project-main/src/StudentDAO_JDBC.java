import java.util.Scanner;
import java.sql.*;

public class StudentDAO_JDBC implements StudentDAO {
	Connection dbConnection;

	public StudentDAO_JDBC(Connection dbconn) {
		// JDBC driver name and database URL
		// Database credentials
		dbConnection = dbconn;
	}

	@Override
	public Student getStudentByKey(String id) {
		Student s = new Student();
		String sql;
		Statement stmt = null;

		try {
			stmt = dbConnection.createStatement();
			sql = "select Student_ID from student where STUDENT_ID = " + id;
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				String studentid = rs.getString("Student_ID");
				if (studentid == null)
					break;
				String name = rs.getString("Name");
				String email = rs.getString("Email");
				String branch = rs.getString("Branch");
				String gender = rs.getString("Gender");
				Date dob = rs.getDate("DOB");
				int sem = rs.getInt("CurrentSem");
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
			sql = "select CourseName from Course where SemOffered = " + s.getSem();
			ResultSet rs = stmt.executeQuery(sql);

			System.out.println("Your eligible courses are\n");

			while (rs.next()) {
				System.out.println(
						"Course Name = " + rs.getString("CourseName") + "Course_ID = " + rs.getString("Course_ID"));
			}

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	@Override
	public void viewMyCourses(Student s) {
		String sql;
		Statement stmt = null;
		try {
			String id = s.getStudentID();
			stmt = dbConnection.createStatement();
			sql = "select CourseName from Course where STUDENT_ID = " + id;// enrollment query
			ResultSet rs = stmt.executeQuery(sql);

			System.out.println("Your courses are\n");

			while (rs.next()) {
				System.out.println(
						"Course Name = " + rs.getString("CourseName") + "Course_ID = " + rs.getString("Course_ID"));
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
		String sql;
		Statement stmt = null;
		try {
			String id = s.getStudentID();
			stmt = dbConnection.createStatement();
			sql = "select CourseName from Course where STUDENT_ID = " + id;// enrollment query
			ResultSet rs = stmt.executeQuery(sql);

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

	}

	@Override
	public void UnenrollForCourse(Student s) {
		String sql;
		Statement stmt = null;
		try {
			String id = s.getStudentID();
			stmt = dbConnection.createStatement();
			sql = "select CourseName from Course where STUDENT_ID = " + id;// enrollment query
			ResultSet rs = stmt.executeQuery(sql);

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
			String id = s.getStudentID();
			stmt = dbConnection.createStatement();
			sql = "select c.CourseName, p.Grade from Course c, Performance p where p.Course_ID = c.Course_ID" + id;// enrollment
																													// query
			ResultSet rs = stmt.executeQuery(sql);

			System.out.println("Here is your transcript\n");

			while (rs.next()) {
				System.out.println(
						"Course Name = " + rs.getString("CourseName") + "Course_ID = " + rs.getString("Course_ID"));
			}

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
		Statement stmt = null;
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter Professor name: ");
			String name = scanner.nextLine().toLowerCase();
			scanner.close();
			stmt = dbConnection.createStatement();
			sql = "select c.Course_ID, c.CourseName from Course c, Professor p where c.Professor_ID = p.Professor_ID and p.Name ="
					+ name;// enrollment query

			ResultSet rs = stmt.executeQuery(sql);

			System.out.println("Here is your transcript\n");

			while (rs.next()) {
				System.out.println(
						"Course Name = " + rs.getString("CourseName") + "Course_ID = " + rs.getString("Course_ID"));
			}

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

}
