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
			sql = "select * from Student where STUDENT_ID = " + id;
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int studentid = rs.getInt("Student_ID");
				//if (studentid == null)
				//	break;
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
			sql = "select CourseName, Course_ID from Course where SemOfferedIn = " + s.getSem();
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
			System.exit(1);
		}

		//return 0;
	}

	@Override
	public void viewMyCourses(Student s) {
		String sql;
		Statement stmt = null;
		try {
			int id = s.getStudentID();
			stmt = dbConnection.createStatement();
			sql = "select c.Course_ID, c.CourseName from Course c, Enrollment e where e.Course_ID=c.Course_ID and e.Student_ID=" + id;// enrollment query
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
		PreparedStatement preparedStatement = null;
		sql = "insert into Enrollment(Course_ID, Student_ID, Type) values (?, ?, ?)";//enrollment query
		try{
			preparedStatement = dbConnection.prepareStatement(sql);
			Scanner scanner = new Scanner(System.in);
			int Courseid;
			String type;
			System.out.println("Enter Course_ID:"); Courseid = scanner.nextInt();
			//System.out.println("Enter Student_ID:"); studentid = scanner.nextInt();
			System.out.println("Specify type (Core / Elective):");type = scanner.next();
			scanner.close();

			preparedStatement.setInt(1, Courseid);
			preparedStatement.setInt(2, s.getStudentID());
			preparedStatement.setString(3, type);
			System.out.println(sql);
			preparedStatement.executeUpdate(sql);

			System.out.println("Succesfully enrolled for course ");

		}
		catch(SQLException ex) {
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
		String sql;
		Statement stmt = null;
		try {
			Scanner scanner = new Scanner(System.in);
			String coursename = scanner.next();
			stmt = dbConnection.createStatement();
			sql = "delete e from Enrollment e inner join Course c on e.Course_ID=c.Course_ID and c.CourseName=" + coursename;// enrollment query
			ResultSet rs = stmt.executeQuery(sql);
			scanner.close();

			System.out.println("Succesfully deleted " + coursename + "from your courses");

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
			sql = "select c.CourseName, p.Grade from Course c, Performance p where p.Course_ID = c.Course_ID and p.Student_ID =" + id;// enrollment
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
