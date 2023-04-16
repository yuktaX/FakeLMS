import java.util.ArrayList;
import java.util.List;
import java.sql.*;


public class StudentDAO_JDBC implements StudentDAO {
																																																																																																																																																																																																																																															Connection dbConnection;

	public StudentDAO_JDBC(Connection dbconn){
		// JDBC driver name and database URL
 		//  Database credentials
		dbConnection = dbconn;
	}

	@Override
	public boolean getStudentByKey(String id) {
		//Student s = new Student();
		String sql;
		Statement stmt = null;
		
		try{
			stmt = dbConnection.createStatement();
			sql = "select Student_ID from student where STUDENT_ID = " + id;
			ResultSet rs = stmt.executeQuery(sql);
																																																																																																																																																																																			
			//STEP 5: Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				String studentid  = rs.getString("Student_ID");
				if(studentid == null)
					return false;
				//String name = rs.getString("name");
				//s.setStudentID(id);
				//s.setName(name);
				break;
				// Add exception handling here if more than one row is returned
			}
			return true;
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		// Add exception handling when there is no matching record
		//return true;
		return false;
	}
	@Override
	public void getEligibleCourses() {
		String sql;
		Statement stmt = null;
		try{
			stmt = dbConnection.createStatement();
			sql = "select CourseName from Course where STUDENT_ID = " + id;
			ResultSet rs = stmt.executeQuery(sql);

		}
		catch(SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	@Override
	public void EnrollForCourse(String Course_ID) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'EnrollForCourse'");
	}

	@Override
	public void UnenrollForCourse(String Course_ID) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'UnenrollForCourse'");
	}

	@Override
	public void getTranscript() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getTranscript'");
	}

	

}
