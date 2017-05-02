import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class UserHandler {

	public static final String GET_USERNAME = "SELECT userName FROM USER WHERE userID = ?"; // 1, userID
	public static final String SET_USERNAME = "SET userName = ? WHERE userID = ?"; //1, userName. 2, userID
    public static final String GET_PARTICIPANTCOUNTER = "SELECT participantCounter FROM USER WHERE userID = ?"; //1, userID
    public static final String INCREASE_PARTICIPANTCOUNTER = "UPDATE USER SET participantCounter = participantCounter + 1 WHERE userID = ?"; //1, userID
    public static final String GET_HOSTCOUNTER = "SELECT hostCounter FROM USER WHERE userID = ?"; //1, userID
    public static final String INCREASE_HOSTCOUNTER = "UPDATE USER SET hostCounter = hostCounter + 1 WHERE userID = ?"; //1, userID
    public static final String GET_GENDER = "SELECT gender FROM USER WHERE userID = ?"; //1, userID
    public static final String SET_GENDER = "SET gender = ? WHERE userID = ?"; //1, gender, 2, userID
    public static final String GET_AGE = "SELECT age FROM USER WHERE userID = ?"; //1, userID
    public static final String SET_AGE = "SET age = ? WHERE userID = ?"; // 1, age, 2 userID
	public static final String GET_NAME = "SELECT name FROM USER WHERE userID = ?"; // 1, userID
	public static final String SET_NAME = "SET name = ? WHERE userID = ?"; //1, name, 2, UserID

	public static DataSource getMySQLDataSource() { // Funkar denna som connect
		MysqlDataSource mysqlDS = null;

		try {
			mysqlDS = new MysqlDataSource();
			mysqlDS.setURL("jdbc:mysql://localhost:3306/mydb");
			mysqlDS.setUser("root");
			mysqlDS.setPassword("");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mysqlDS;
	}

	public Connection getConnection() throws SQLException {
		Connection connection = null;

		// Get the MySqlDataSource

		System.out.println("Verifying access");
		DataSource dataSource = getMySQLDataSource();

		// Get connection from the database

		System.out.println("Connecting database...");
		connection = dataSource.getConnection();

		return connection;

	}

	public void closePrstmt(PreparedStatement prstmt) {
		try {
			if (prstmt != null) {
				prstmt.close();
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}

	}

	public void closeConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}

	public String getUsername(int inputUserID) throws SQLException {
		PreparedStatement prstmt = null;
		String username = null;
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Database connected!");
			// Execute query

			prstmt = connection.prepareStatement(GET_USERNAME);

			prstmt.setInt(1, inputUserID); // Switch to correct preparedStatment
											// input

			ResultSet rs = prstmt.executeQuery();

			while (rs.next()) {

				// Retrieve by column name

				username = rs.getString("username");

				// Display values

				System.out.println("username: " + username);

			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			// Finally block used to close resources
			closePrstmt(prstmt);
			closeConnection(connection);
		}
		return username;
	}

	public void setUsername(int inputUserID, String inputUsername) throws SQLException {

		PreparedStatement prstmt = null;
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Database connected!");
			// Execute query

			prstmt = connection.prepareStatement(SET_USERNAME);

			prstmt.setString(1, inputUsername);
			prstmt.setInt(2, inputUserID); // Switch to correct preparedStatment
											// input

			ResultSet rs = prstmt.executeQuery();

			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			// Finally block used to close resources
			closePrstmt(prstmt);
			closeConnection(connection);
		}
	}

	public int getParticipantCounter(int inputUserID) throws SQLException {
		PreparedStatement prstmt = null;
		int participantCounter = -1;
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Database connected!");
			// Execute query

			prstmt = connection.prepareStatement(GET_PARTICIPANTCOUNTER);

			prstmt.setInt(1, inputUserID); // Switch to correct preparedStatment
											// input

			ResultSet rs = prstmt.executeQuery();

			while (rs.next()) {

				// Retrieve by column name

				participantCounter = rs.getInt("participantCounter");

				// Display values

				System.out.println("participantCounter" + participantCounter);

			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			// Finally block used to close resources
			closePrstmt(prstmt);
			closeConnection(connection);
		}
		return participantCounter;
	}

	public void increaseParticipantCounter(int inputUserID) throws SQLException {

		PreparedStatement prstmt = null;
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Database connected!");
			// Execute query

			prstmt = connection.prepareStatement(INCREASE_PARTICIPANTCOUNTER);

			prstmt.setInt(1, inputUserID); // Switch to correct preparedStatment
											// input

			ResultSet rs = prstmt.executeQuery();

			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			// Finally block used to close resources
			closePrstmt(prstmt);
			closeConnection(connection);
		}

	}

	public int getHostCounter(int inputUserID) throws SQLException {
		PreparedStatement prstmt = null;
		int hostCounter = -1;
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Database connected!");
			// Execute query

			prstmt = connection.prepareStatement(GET_HOSTCOUNTER);

			prstmt.setInt(1, inputUserID); // Switch to correct preparedStatment
											// input

			ResultSet rs = prstmt.executeQuery();

			while (rs.next()) {

				// Retrieve by column name

				hostCounter = rs.getInt("hostCounter");

				// Display values

				System.out.println("hostCounter" + hostCounter);

			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			// Finally block used to close resources
			closePrstmt(prstmt);
			closeConnection(connection);
		}
		return hostCounter;
	}

	public void increaseHostCounter(int inputUserID) throws SQLException {

		PreparedStatement prstmt = null;
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Database connected!");
			// Execute query

			prstmt = connection.prepareStatement(INCREASE_HOSTCOUNTER);

			prstmt.setInt(1, inputUserID); // Switch to correct preparedStatment
											// input

			ResultSet rs = prstmt.executeQuery();

			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			// Finally block used to close resources
			closePrstmt(prstmt);
			closeConnection(connection);
		}
	}

	public String getGender(int inputUserID) throws SQLException {
		PreparedStatement prstmt = null;
		String gender = null;
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Database connected!");
			// Execute query

			prstmt = connection.prepareStatement(GET_GENDER);

			prstmt.setInt(1, inputUserID); // Switch to correct preparedStatment
											// input

			ResultSet rs = prstmt.executeQuery();

			while (rs.next()) {

				// Retrieve by column name

				gender = rs.getString("username");

				// Display values

				System.out.println("userID: " + gender);

			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			// Finally block used to close resources
			closePrstmt(prstmt);
			closeConnection(connection);
		}
		return gender;
	}

	public void setGender(String gender, int inputUserID) throws SQLException {

		PreparedStatement prstmt = null;
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Database connected!");
			// Execute query

			prstmt = connection.prepareStatement(SET_GENDER);
			
			prstmt.setString(1, gender);	
			prstmt.setInt(2, inputUserID); // Switch to correct preparedStatment
										// input

			ResultSet rs = prstmt.executeQuery();

			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			// Finally block used to close resources
			closePrstmt(prstmt);
			closeConnection(connection);
		}
	}

	public int getAge(int inputUserID) throws SQLException {
		PreparedStatement prstmt = null;
		int age = -1;
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Database connected!");
			// Execute query

			prstmt = connection.prepareStatement(GET_AGE);

			prstmt.setInt(1, inputUserID); // Switch to correct preparedStatment
											// input

			ResultSet rs = prstmt.executeQuery();

			while (rs.next()) {

				// Retrieve by column name

				age = rs.getInt("age");

				// Display values

				System.out.println("age" + age);

			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			// Finally block used to close resources
			closePrstmt(prstmt);
			closeConnection(connection);
		}
		return age;
	}

	public void setAge(int age, int inputUserID) throws SQLException {

		PreparedStatement prstmt = null;
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Database connected!");
			// Execute query

			prstmt = connection.prepareStatement(SET_AGE);

			prstmt.setInt(1, age);
			prstmt.setInt(2, inputUserID); // Switch to correct preparedStatment
											// input

			ResultSet rs = prstmt.executeQuery();

			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			// Finally block used to close resources
			closePrstmt(prstmt);
			closeConnection(connection);
		}
	}

	public String getName(int inputUserID) throws SQLException {
		PreparedStatement prstmt = null;
		String name = null;
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Database connected!");
			// Execute query

			prstmt = connection.prepareStatement(GET_NAME);

			prstmt.setInt(1, inputUserID); // Switch to correct preparedStatment
											// input

			ResultSet rs = prstmt.executeQuery();

			while (rs.next()) {

				// Retrieve by column name

				name = rs.getString("username");

				// Display values

				System.out.println("userID" + name);

			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			// Finally block used to close resources
			closePrstmt(prstmt);
			closeConnection(connection);
		}
		return name;
	}

	public void setName(String name, int inputUserID) throws SQLException {

		PreparedStatement prstmt = null;
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Database connected!");
			// Execute query

			prstmt = connection.prepareStatement(SET_NAME);

			prstmt.setString(1, name);
			prstmt.setInt(2, inputUserID); // Switch to correct preparedStatment
											// input

			ResultSet rs = prstmt.executeQuery();

			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			// Finally block used to close resources
			closePrstmt(prstmt);
			closeConnection(connection);
		}
	}
}