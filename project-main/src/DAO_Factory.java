import java.sql.*;

/*
	Methods to be called in the following order:

	1. activateConnection
	2. 	Any number getDAO calls with any number of database transactions
	3. deactivateConnection
*/
public class DAO_Factory {

	public enum TXN_STATUS {
		COMMIT, ROLLBACK
	};

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/fakelms?characterEncoding=utf8";
	static final String USER = "root";
	static final String PASS = "Vineet@123";
	Connection dbconnection = null;

	// You can add additional DAOs here as needed
	StudentDAO studentDAO = null;
	ProfessorDAO professorDAO = null;

	boolean activeConnection = false;

	public DAO_Factory() {
		dbconnection = null;
		activeConnection = false;
	}

	public void activateConnection() throws Exception {

		System.out.println("Connecting to database...");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			dbconnection = DriverManager.getConnection(DB_URL, USER, PASS);
			dbconnection.setAutoCommit(false);
			activeConnection = true;
		} catch (ClassNotFoundException ex) {
			System.out.println(ex);
			System.out.println("Error: unable to load driver class!");
			System.exit(1);
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			System.exit(1);
		}
	}

	public StudentDAO getStudentDAO() throws Exception {
		if (activeConnection == false)
			throw new Exception("Connection not activated...");

		if (studentDAO == null)
			studentDAO = new StudentDAO_JDBC(dbconnection);

		return studentDAO;
	}

	public ProfessorDAO getProfessorDAO() throws Exception {
		if (activeConnection == false)
			throw new Exception("Connection not activated...");

		if (professorDAO == null)
			professorDAO = new ProfessorDAO_JDBC(dbconnection);

		return professorDAO;
	}

	public void deactivateConnection(TXN_STATUS txn_status) {
		// Okay to keep deactivating an already deactivated connection
		activeConnection = false;
		if (dbconnection != null) {
			try {
				if (txn_status == TXN_STATUS.COMMIT)
					dbconnection.commit();
				else
					dbconnection.rollback();

				dbconnection.close();
				dbconnection = null;

				// Nullify all DAO objects
				studentDAO = null;
				professorDAO = null;
			} catch (SQLException ex) {
				// handle any errors
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("VendorError: " + ex.getErrorCode());
				System.exit(1);

			}
		}
	}
};
