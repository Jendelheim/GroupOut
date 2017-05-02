import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class EventHandler {

	public static final String GET_EVENTNAME = "SELECT eventName FROM Event WHERE eventID = ?"; // 1, eventID
	public static final String SET_EVENTNAME = "SET eventName = ? WHERE eventID = ?"; //  1, eventName 2, eventID 
	public static final String GET_LEADER = "SELECT  leader FROM Event WHERE eventID = ?"; // 1, eventID
	public static final String SET_LEADER = "SET leader = ? WHERE eventID = ?"; // 1, userID 2, eventID 
	public static final String GET_DATE = "SELECT date FROM Event WHERE eventID = ?"; // 1, eventID
	public static final String SET_DATE = "SET date = ? WHERE eventID = ?"; // 1, date 2, eventID 
	
	public static final String GET_STARTTIME = "SELECT startTime FROM Event WHERE eventID = ?"; // 1, eventID 
	public static final String SET_STARTTIME = "SET startTime = ? WHERE eventID = ?"; // 1, startTime 2, eventID 
	public static final String GET_ENDTIME = "SELECT endTime FROM Event Where eventID = ?"; // 1, eventID 
	public static final String SET_ENDTIME = "SET endTime = ? WHERE eventID = ?"; // 1, endTime 2, eventID 
	public static final String GET_CATEGORY = "SELECT category FROM Event WHERE eventID = ?"; // 1, eventID 
	public static final String SET_CATEGORY = "SET category = ? WHERE eventID = ?"; // 1, category 2, eventID 
	public static final String GET_DESCRIPTION = "SELECT description FROM Event WHERE eventID = ?"; // 1, eventID 
	public static final String SET_DESCRIPTION = "SET description = ? WHERE eventID = ?"; // 1, description 2, eventID 
	public static final String GET_MIN_CAPACITY = "SELECT minCapacity FROM Event WHERE eventID = ?"; // 1, eventID
	public static final String SET_MIN_CAPACITY = "SET minCapacity = ? WHERE eventID = ?"; // 1, minCapacity 2, eventID 
	public static final String GET_MAX_CAPACITY = "SELECT maxCapacity FROM Event WHERE eventID = ?"; // 1, eventID
	public static final String SET_MAX_CAPACITY = "SET maxCapacity = ? WHERE eventID = ? "; // 1, maxCapacity 2,eventID
	public static final String GET_VISIBILITY = "SELECT visible FROM Event WHERE eventID = ?"; // 1, eventID 
	public static final String SET_VISIBILITY = "SET visible = ? WHERE eventID = ? "; // 1, visibility 2, eventID
	public static final String GET_PLACE = "SELECT place FROM Event WHERE eventID = ?"; // 1, eventID 
	public static final String SET_PLACE = "SET place = ? WHERE eventID = ?"; // 1, place 2, eventID
	public static final String GET_INPUTCOORDINATE_ID = "SELECT inputID FROM Event WHERE eventID = ?"; // 1, eventID
	public static final String SET_INPUTCOORDINATE_ID = "SET inputID = ? WHERE eventID = ?"; // 1, inputID 2, eventID 
	

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

	public String getEventName(int eventID) {
		PreparedStatement prstmt = null;
		String eventName = null;
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Database connected!");
			// Execute query

			prstmt = connection.prepareStatement(GET_EVENTNAME);

			prstmt.setInt(1, eventID); // Switch to correct preparedStatment
											// input

			ResultSet rs = prstmt.executeQuery();

			while (rs.next()) {

				// Retrieve by column name

				eventName = rs.getString("username");

				// Display values

				System.out.println("userID" + eventName);

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
		return eventName;
	}

	public void setEventName(String eventName, int eventID) {

		PreparedStatement prstmt = null;
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Database connected!");
			// Execute query

			prstmt = connection.prepareStatement(SET_EVENTNAME);

			
			prstmt.setString(1, eventName);
			prstmt.setInt(2, eventID); // Switch to correct preparedStatment
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

	public int getLeader(int eventID) {
		PreparedStatement prstmt = null;
		int leader = -1;
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Database connected!");
			// Execute query

			prstmt = connection.prepareStatement(GET_LEADER);

			prstmt.setInt(1, eventID); // Switch to correct preparedStatment
											// input

			ResultSet rs = prstmt.executeQuery();

			while (rs.next()) {

				// Retrieve by column name

				leader = rs.getInt("participantCounter");

				// Display values

				System.out.println("participantCounter" + leader);

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
		return leader;
	}

	public void setLeader(int userID, int eventID) {

		PreparedStatement prstmt = null;
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Database connected!");
			// Execute query

			prstmt = connection.prepareStatement(SET_LEADER);

			
			prstmt.setInt(1, userID);
			prstmt.setInt(2, eventID); // Switch to correct preparedStatment
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

	public Date getDate(int eventID) {
		PreparedStatement prstmt = null;
		Date date = null; 
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Database connected!");
			// Execute query

			prstmt = connection.prepareStatement(GET_DATE);

			prstmt.setInt(1, eventID); // Switch to correct preparedStatment
											// input

			ResultSet rs = prstmt.executeQuery();

			while (rs.next()) {

				// Retrieve by column name

				date = rs.getDate("date");

				// Display values

				System.out.println("Date: " + date);

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
		return date;
	}

	public void setDate(Date date, int eventID) { 

		PreparedStatement prstmt = null;
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Database connected!");
			// Execute query

			prstmt = connection.prepareStatement(SET_DATE);

			prstmt.setDate(1, date);
			prstmt.setInt(2, eventID); // Switch to correct preparedStatment
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

	public long getStartTime(int eventID) {
		PreparedStatement prstmt = null;
		int participantCounter = -1;
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Database connected!");
			// Execute query

			prstmt = connection.prepareStatement(GET_STARTTIME);

			prstmt.setInt(1, eventID); // Switch to correct preparedStatment
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

	public void setStartTime(long startTime, int eventID) {

		PreparedStatement prstmt = null;
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Database connected!");
			// Execute query

			prstmt = connection.prepareStatement(SET_STARTTIME);

			prstmt.setLong(1, startTime);
			prstmt.setInt(2, eventID); // Switch to correct preparedStatment
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

	public long getEndTime(int eventID) {
		PreparedStatement prstmt = null;
		int participantCounter = -1;
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Database connected!");
			// Execute query

			prstmt = connection.prepareStatement(GET_ENDTIME);

			prstmt.setInt(1, eventID); // Switch to correct preparedStatment
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

	public void setEndTime(int eventID) {

		PreparedStatement prstmt = null;
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Database connected!");
			// Execute query

			prstmt = connection.prepareStatement(SET_ENDTIME);

			prstmt.setInt(1, eventID); // Switch to correct preparedStatment
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

	public String getCategory(int eventID) {
		PreparedStatement prstmt = null;
		String username = null;
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Database connected!");
			// Execute query

			prstmt = connection.prepareStatement(GET_CATEGORY);

			prstmt.setInt(1, eventID); // Switch to correct preparedStatment
											// input

			ResultSet rs = prstmt.executeQuery();

			while (rs.next()) {

				// Retrieve by column name

				username = rs.getString("username");

				// Display values

				System.out.println("userID" + username);

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

	public void setCategory(int eventID) {

		PreparedStatement prstmt = null;
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Database connected!");
			// Execute query

			prstmt = connection.prepareStatement(SET_CATEGORY);

			prstmt.setInt(1, eventID); // Switch to correct preparedStatment
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

	public String getDescription(int eventID) {
		PreparedStatement prstmt = null;
		String username = null;
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Database connected!");
			// Execute query

			prstmt = connection.prepareStatement(GET_DESCRIPTION);

			prstmt.setInt(1, eventID); // Switch to correct preparedStatment
											// input

			ResultSet rs = prstmt.executeQuery();

			while (rs.next()) {

				// Retrieve by column name

				username = rs.getString("username");

				// Display values

				System.out.println("userID" + username);

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

	public void setDescription(int eventID) {

		PreparedStatement prstmt = null;
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Database connected!");
			// Execute query

			prstmt = connection.prepareStatement(SET_DESCRIPTION);

			prstmt.setInt(1, eventID); // Switch to correct preparedStatment
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

	public int getMinCapacity(int eventID) {
		PreparedStatement prstmt = null;
		int participantCounter = -1;
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Database connected!");
			// Execute query

			prstmt = connection.prepareStatement(GET_MIN_CAPACITY);

			prstmt.setInt(1, eventID); // Switch to correct preparedStatment
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

	public void setMinCapacity(int eventID) {

		PreparedStatement prstmt = null;
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Database connected!");
			// Execute query

			prstmt = connection.prepareStatement(SET_MIN_CAPACITY);

			prstmt.setInt(1, eventID); // Switch to correct preparedStatment
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

	public int getMaxCapacity(int eventID) {
		PreparedStatement prstmt = null;
		int participantCounter = -1;
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Database connected!");
			// Execute query

			prstmt = connection.prepareStatement(GET_MAX_CAPACITY);

			prstmt.setInt(1, eventID); // Switch to correct preparedStatment
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

	public void setMaxCapacity(int eventID) {

		PreparedStatement prstmt = null;
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Database connected!");
			// Execute query

			prstmt = connection.prepareStatement(SET_MAX_CAPACITY);

			prstmt.setInt(1, eventID); // Switch to correct preparedStatment
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

	public boolean getVisibility(int eventID) {
		PreparedStatement prstmt = null;
		Boolean visibility = null;
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Database connected!");
			// Execute query

			prstmt = connection.prepareStatement(GET_VISIBILITY);

			prstmt.setInt(1, eventID); // Switch to correct preparedStatment
											// input

			ResultSet rs = prstmt.executeQuery();

			while (rs.next()) {

				// Retrieve by column name

				visibility = rs.getBoolean("visibility");

				// Display values

				System.out.println("visibility: " + visibility);

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
		return visibility;
	}

	public void setVisibility (int eventID) {

		PreparedStatement prstmt = null;
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Database connected!");
			// Execute query

			prstmt = connection.prepareStatement(SET_VISIBILITY);

			prstmt.setInt(1, eventID); // Switch to correct preparedStatment
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

	public void getPlace(int eventID) { // Ska vara något annat än void

	}

	public void setPlace(int eventID) {

		PreparedStatement prstmt = null;
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Database connected!");
			// Execute query

			prstmt = connection.prepareStatement(SET_PLACE);

			prstmt.setInt(1, eventID); // Switch to correct preparedStatment
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

	public int getInputCoordinateID(int eventID) {
		PreparedStatement prstmt = null;
		int participantCounter = -1;
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Database connected!");
			// Execute query

			prstmt = connection.prepareStatement(GET_INPUTCOORDINATE_ID);

			prstmt.setInt(1, eventID); // Switch to correct preparedStatment
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

	public void setInputCoordinateID(int eventID) {

		PreparedStatement prstmt = null;
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println("Database connected!");
			// Execute query

			prstmt = connection.prepareStatement(SET_INPUTCOORDINATE_ID);

			prstmt.setInt(1, eventID); // Switch to correct preparedStatment
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
