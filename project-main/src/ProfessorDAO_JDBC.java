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
    public Professor loginProfessor(Integer id, String password) {
        Professor p = new Professor();
        String sql;
        Statement stmt = null;
        PreparedStatement loginStatement1 = null;
        String loginSql1;

        try {
            loginSql1 = "select Professor_ID, Password from ProfessorLogin where Professor_ID = ? and Password = ?";
            loginStatement1 = dbConnection.prepareStatement(loginSql1);
            loginStatement1.setInt(1, id);
            loginStatement1.setString(2, password);
            ResultSet rs1 = loginStatement1.executeQuery();
            if (rs1.next() == false) {
                System.out.println("Login failed");
                return null;
            } else {
                stmt = dbConnection.createStatement();
                sql = "select * from Professor where Professor_ID = " + id;
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    // Retrieve by column name
                    int professorid = rs.getInt("Professor_ID");
                    String name = rs.getString("Name");
                    String email = rs.getString("Email");
                    p.setProfessor_ID(professorid);
                    p.setName(name);
                    p.setEmail(email);
                    // Add exception handling here if more than one row is returned
                }
                return p;
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
        return p;
    }

    @Override
    public void viewMyCourses(Professor p) {
        String sql;
        PreparedStatement preparedStatement = null;

        String sql1;
        PreparedStatement preparedStatement1 = null;
        try {
            Scanner scanner = new Scanner(System.in);
            sql = "select Course_ID, CourseName from Course where Professor_ID =?";// enrollment
            sql1 = "select Name from Professor"; // query

            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement1 = dbConnection.prepareStatement(sql1);

            preparedStatement.setInt(1, p.getProfessortID());

            int flag = 0;
            ResultSet rs1 = preparedStatement1.executeQuery();
            if (rs1.next() == false) {
                System.out.println("There are no Professors in the database :(");
            } else {
                do {
                    String data = rs1.getString("Name");
                    if (data.equals(p.getName())) {
                        flag = 1;
                        break;
                    }
                } while (rs1.next());
            }

            if (flag == 1) {
                ResultSet rs = preparedStatement.executeQuery();
                System.out.println("\n-----------Courses offered by Prof." + p.getName() + "---------------\n");
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
    public void addPerformance(Professor p) {
        PreparedStatement preparedStatement = null;
        String sql;
        sql = "insert into Performance(Course_ID, Student_ID, Grade, Attendance) values (?,?,?,?)";
        PreparedStatement errStatement1 = null;
        String errSql1;
        PreparedStatement errStatement2 = null;
        String errSql2;
        PreparedStatement delStatement1 = null;
        String delSql1;

        try {
            preparedStatement = dbConnection.prepareStatement(sql);

            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter Course_ID:");
            Integer Course_ID = scanner.nextInt();

            // scanner.close();

            errSql1 = "select Course_ID, Student_ID from Enrollment where Course_ID = ? and Student_ID = ?";
            errStatement1 = dbConnection.prepareStatement(errSql1);

            errSql2 = "select Course_ID, Professor_ID from Course where Course_ID = ? and Professor_ID = ?";
            errStatement2 = dbConnection.prepareStatement(errSql2);
            errStatement2.setInt(1, Course_ID);
            errStatement2.setInt(2, p.getProfessortID());
            ResultSet rs1 = errStatement2.executeQuery();

            delSql1 = "delete from Enrollment where Course_ID = ? and Student_ID = ?";
            delStatement1 = dbConnection.prepareStatement(delSql1);

            if (rs1.next() == false) {
                System.out.println("You don't have permission to add performance for this course.");
            } else {
                System.out.println("Enter Student_ID:");
                Integer Student_ID = scanner.nextInt();
                errStatement1.setInt(1, Course_ID);
                errStatement1.setInt(2, Student_ID);
                ResultSet rs = errStatement1.executeQuery();
                if (rs.next() == false) {
                    System.out.println(
                            "The student with Student_ID " + Student_ID + " isn't enrolled to course " + Course_ID);
                } else {
                    System.out.println("Enter Grade:");
                    String Grade = scanner.next();
                    System.out.println("Enter Attendance:");
                    Float Attendance = scanner.nextFloat();

                    preparedStatement.setInt(1, Course_ID);
                    preparedStatement.setInt(2, Student_ID);
                    preparedStatement.setString(3, Grade);
                    preparedStatement.setFloat(4, Attendance);
                    preparedStatement.executeUpdate();

                    System.out.println("Performance of " + Student_ID + " added to the Performance table");
                    delStatement1.setInt(1, Course_ID);
                    delStatement1.setInt(2, Student_ID);
                    delStatement1.executeUpdate();
                }
            }

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
        PreparedStatement errStatement1 = null;
        String errSql1;
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
            System.out.println("Enter Branch (\"gen\" for general courses):");
            String Branch = scanner.next();

            Integer Professor_ID = p.getProfessortID();

            errSql1 = "select CourseName from Course";
            errStatement1 = dbConnection.prepareStatement(errSql1);
            ResultSet rs = errStatement1.executeQuery();
            int flag = 0;
            while (rs.next()) {
                String name = rs.getString("CourseName");
                if (name.equals(CourseName)) {
                    System.out.println("Unable to add course because it already exists.");
                    flag = 1;
                    break;
                }
            }

            // scanner.close();

            preparedStatement.setString(1, CourseName);
            preparedStatement.setInt(2, SemOfferedIn);
            preparedStatement.setInt(3, Credits);
            preparedStatement.setString(4, Type);
            preparedStatement.setString(5, Branch);
            preparedStatement.setInt(6, Professor_ID);

            // execute insert SQL stetement
            if (flag == 0) {
                preparedStatement.executeUpdate();
                System.out.println("Course " + CourseName + " added to Course table");
            }

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
            // scanner.close();

            stmt = dbConnection.createStatement();
            sql = "select distinct s.Student_ID, s.Name, s.CurrentSemester, s.Branch from Student s, Enrollment e, Course c  where s.Student_ID=e.Student_ID and c.Course_ID=e.Course_ID and c.CourseName='"
                    + courseName + "'";// enrollment query
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next() == false) {
                System.out.println("\nThere are no students enrolled to this course");
            } else {
                do {
                    System.out.println(
                            "\nStudent_ID = " + rs.getInt("Student_ID") + " ,StudentName = " + rs.getString("Name")
                                    + " ,CurrentSemester = " + rs.getInt("CurrentSemester") + " ,Branch = "
                                    + rs.getString("Branch"));
                } while (rs.next());
            }

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
