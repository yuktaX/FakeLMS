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
    public Professor getProfessorByKey(String id) {
        Professor p = new Professor();
        String sql;
        Statement stmt = null;

        try {
            stmt = dbConnection.createStatement();
            sql = "select * from Professor where Professor_ID = " + id;
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 5: Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                int professorid = rs.getInt("Professor_ID");
                // if (studentid == null)
                // break;
                String name = rs.getString("Name");
                String email = rs.getString("Email");
                p.setProfessor_ID(professorid);
                p.setName(name);
                p.setEmail(email);
                // Add exception handling here if more than one row is returned
            }
            return p;
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            System.exit(1);
        }
        // Add exception handling when there is no matching record
        // return true;
        return p;
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

            //scanner.close();

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
    public void addCourse(Professor p) {
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

            Integer Professor_ID = p.getProfessortID();

            //scanner.close();

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
    public void getStudentsFromCourse() {
        String sql;
        Statement stmt = null;
        try {
            Scanner scanner = new Scanner(System.in);
            String courseName;
            System.out.println("Enter course name: ");
            courseName = scanner.nextLine().toLowerCase();
            //scanner.close();

            stmt = dbConnection.createStatement();
            sql = "select s.Student_ID, s.Name, s.CurrentSemester, s.Branch from Student s, Enrollment e, Course c  where s.Student_ID=e.Student_ID and c.CourseName= '"
                    + courseName + "'";// enrollment query
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println(
                        "Student_ID = " + rs.getInt("Student_ID") + " ,StudentName = " + rs.getString("Name")
                                + " ,CurrentSemester = " + rs.getInt("CurrentSemester") + " ,Branch = "
                                + rs.getString("Branch"));
            }

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
