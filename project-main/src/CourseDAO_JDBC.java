
import java.sql.*;
import java.util.Scanner;


public class CourseDAO_JDBC implements CourseDAO {
																																																																																																																																																																																																																																															Connection dbConnection;

	public CourseDAO_JDBC(Connection dbconn){
		// JDBC driver name and database URL
 		//  Database credentials
		dbConnection = dbconn;
	}

	@Override
	public Course getCourseByKey(int Course_ID) {
		Course s = new Course();
		String sql;
		Statement stmt = null;
		
		try{
			stmt = dbConnection.createStatement();
			sql = "select Course_ID, COurseName from Course where Course_ID = " + Course_ID;
			ResultSet rs = stmt.executeQuery(sql);
																																																																																																																																																																																			
			//STEP 5: Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				// int rollno  = rs.getInt("rollno");
				// String name = rs.getString("name");
				// s.setRollno(rollno);
				// s.setName(name);
                int c_ID = rs.getInt("Course_ID");
                String CourseName = rs.getString("CourseName");
                int SemOfferedIn = rs.getInt("SemOfferedIn");
                int Credits = rs.getInt("Credits");
                String Type = rs.getString("Type");
                int Professor_ID = rs.getInt("Professor_ID");
                String Branch = rs.getString("Branch");

				s.setCourseID(c_ID);
				s.setCourseName(CourseName);
				s.setBranch(Branch);
				s.setCourseCredit(Credits);
				s.setCourseProfessor(Professor_ID);
				s.setType(Type);
				s.setSemOfferedIn(SemOfferedIn);
				break;
				// Add exception handling here if more than one row is returned
			}
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		// Add exception handling when there is no matching record
		return s;
	}

	@Override
	public void addCourse() {
		PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "insert into Course(CourseName, SemOfferedIn, Credits, Type, Branch, Professor_ID) values (?,?,?,?,?,?)";


        try {
            preparedStatement = dbConnection.prepareStatement(sql);

            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter CourseName:");
            String CourseName = scanner.nextLine();
            System.out.println("Enter course semester");
            Integer SemOfferedIn = scanner.nextInt();
            System.out.println("Enter credits:");
            Integer Credits = scanner.nextInt();
            System.out.println("Enter type of course (core/elective):");
            String Type = scanner.next();
            System.out.println("Enter Branch (NULL if not applicable):");
            String Branch = scanner.next();
            System.out.println("Enter Professor_ID:");
            Integer Professor_ID = scanner.nextInt();

            scanner.close();

            preparedStatement.setString(1, CourseName);
            preparedStatement.setInt(2, SemOfferedIn);
            preparedStatement.setInt(3, Credits);
            preparedStatement.setString(4, Type);
            preparedStatement.setString(5, Branch);
            preparedStatement.setInt(6, Professor_ID);

            // execute insert SQL stetement
            preparedStatement.executeUpdate();

            System.out.println("Course " + CourseName + " added to Course table");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}


	@Override
	public void deleteCourse(){
		PreparedStatement preparedStatement = null;
		String sql;
		
		sql = "delete from Course where Course_ID = ?";

		try{
			preparedStatement = dbConnection.prepareStatement(sql);
			Scanner scanner = new Scanner(System.in);

			System.out.println("Enter Course_ID to be deleted: ");
            Integer Course_ID = scanner.nextInt();
			scanner.close();

			preparedStatement.setInt(1, Course_ID);
			preparedStatement.executeUpdate();

			System.out.println("Course: Course_ID " + Course_ID
				+ ", deleted from the database");
		}  catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		try{
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}
	}

}
