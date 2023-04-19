import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.*;

public class ProfessorDAO_JDBC implements ProfessorDAO {
    Connection dbConnection;

    public ProfessorDAO_JDBC(Connection dbconn) {
        // JDBC driver name and database URL
        // Database credentials
        dbConnection = dbconn;
    }

    @Override
    public void addPerformance() {
        PreparedStatement preparedStatement = null;
        String sql;
        sql = "insert into Performance(Course_ID, Student_ID, Grade, Attendance) values (?,?,?,?)";

        try {
            preparedStatement = dbConnection.prepareStatement(sql);

            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter Course_ID:");
            Integer Course_ID = scanner.nextInt();
            System.out.println("Enter Student_ID:");
            Integer Student_ID = scanner.nextInt();
            System.out.println("Enter Grade:");
            String Grade = scanner.next();
            System.out.println("Enter Attendance:");
            Float Attendance = scanner.nextFloat();

            scanner.close();

            preparedStatement.setInt(1, Course_ID);
            preparedStatement.setInt(2, Student_ID);
            preparedStatement.setString(3, Grade);
            preparedStatement.setFloat(4, Attendance);

            // execute insert SQL stetement
            preparedStatement.executeUpdate();

            System.out.println("Performance of " + Student_ID + " added to the Performance table");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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

        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void getStudentsFromCourse()
    {
        String sql;
		Statement stmt = null;
		try{
			Scanner scanner = new Scanner(System.in);
            String courseName;
            courseName = scanner.next();
			stmt = dbConnection.createStatement();
			sql = "select CourseName from Course where STUDENT_ID = " + id;//enrollment query
			ResultSet rs = stmt.executeQuery(sql);

			System.out.println("Here is your transcript\n");

			while(rs.next())
			{
				System.out.println("Course Name = " + rs.getString("CourseName") + "Course_ID = " + rs.getString("Course_ID"));
			}

		}
		catch(SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
    }
}
