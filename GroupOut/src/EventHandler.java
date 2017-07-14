package src;  

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


public class EventHandler {

	public static final String GET_EVENTNAME = "SELECT eventName FROM Event WHERE eventID = ?;";
	public static final String SET_EVENTNAME = "UPDATE Event SET eventName = ? WHERE eventID = ?;";
	public static final String GET_LEADER = "SELECT  leaderID FROM Event WHERE eventID = ?;"; 
	public static final String SET_LEADER = "UPDATE Event SET leaderID = ? WHERE eventID = ?;";
	public static final String GET_DATE = "SELECT date FROM Event WHERE eventID = ?;";
	public static final String SET_DATE = "UPDATE Event SET date = ? WHERE eventID = ?;";
	public static final String GET_STARTTIME = "SELECT startTime FROM Event WHERE eventID = ?;";
	public static final String SET_STARTTIME = "UPDATE Event SET  startTime = ? WHERE eventID = ?;";
	public static final String GET_ENDTIME = "SELECT endTime FROM Event Where eventID = ?;";
	public static final String SET_ENDTIME = "UPDATE Event SET endTime = ? WHERE eventID = ?;";
	public static final String GET_CATEGORY = "SELECT category FROM Event WHERE eventID = ?;";
	public static final String SET_CATEGORY = "UPDATE Event SET category = ? WHERE eventID = ?;";
	public static final String GET_DESCRIPTION = "SELECT description FROM Event WHERE eventID = ?;";
	public static final String SET_DESCRIPTION = "UPDATE Event SET description = ? WHERE eventID = ?;";
	public static final String GET_MIN_CAPACITY ="SELECT minCapacity FROM Event WHERE eventID = ?;";
	public static final String SET_MIN_CAPACITY ="UPDATE Event SET minCapacity = ? WHERE eventID = ?;";
	public static final String GET_MAX_CAPACITY = "SELECT maxCapacity FROM Event WHERE eventID = ?;";
	public static final String SET_MAX_CAPACITY = "UPDATE Event SET maxCapacity = ? WHERE eventID = ? ;";
	public static final String GET_VISIBILITY = "SELECT visible FROM Event WHERE eventID = ?;";
	public static final String SET_VISIBILITY = "UPDATE Event SET visible = ? WHERE eventID = ? ;";
	public static final String GET_PLACE = "SELECT place FROM Event WHERE eventID = ?;";
	public static final String SET_PLACE = "UPDATE Event SET place = ? WHERE eventID = ?;";

	public static DataSource getMySQLDataSource() {
		MysqlDataSource mysqlDS = null;

		try {
			mysqlDS = new MysqlDataSource();
			mysqlDS.setURL("jdbc:mysql://mysql.dsv.su.se/axan5350");
			mysqlDS.setUser("axan5350");
			mysqlDS.setPassword("Bae3Ohngieph");

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

		System.out.println("Connection completed!");
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
	
	public void closeResources(PreparedStatement prstmt, Connection connection){
		closePrstmt(prstmt); 
		closeConnection(connection); 
	}

	public String getEventName(int eventID) {
		PreparedStatement prstmt = null;
		String eventName = null;
		Connection connection = null;
		try {
			connection = getConnection();

			prstmt = connection.prepareStatement(GET_EVENTNAME);

			prstmt.setInt(1, eventID); 
			ResultSet rs = prstmt.executeQuery();

			while (rs.next()) {

				eventName = rs.getString("username");

				System.out.println("userID" + eventName);
			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			closeResources(prstmt, connection);
		}
		return eventName;
	}

	public void setEventName(String eventName, int eventID) {
		PreparedStatement prstmt = null;
		Connection connection = null;
		try {
			connection = getConnection();

			prstmt = connection.prepareStatement(SET_EVENTNAME);
			
			prstmt.setString(1, eventName);
			prstmt.setInt(2, eventID); 

			ResultSet rs = prstmt.executeQuery();

			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			closeResources(prstmt, connection);
		}
	}

	public int getLeader(int eventID) {
		PreparedStatement prstmt = null;
		int leader = -1;
		Connection connection = null;
		try {
			connection = getConnection();
			
			prstmt = connection.prepareStatement(GET_LEADER);

			prstmt.setInt(1, eventID); 

			ResultSet rs = prstmt.executeQuery();

			while (rs.next()) {

				leader = rs.getInt("participantCounter");

				System.out.println("participantCounter" + leader);
			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			closeResources(prstmt, connection);
		}
		return leader;
	}

	public void setLeader(int userID, int eventID) {

		PreparedStatement prstmt = null;
		Connection connection = null;
		try {
			connection = getConnection();
			
			prstmt = connection.prepareStatement(SET_LEADER);

			
			prstmt.setInt(1, userID);
			prstmt.setInt(2, eventID); 

			ResultSet rs = prstmt.executeQuery();

			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			closeResources(prstmt, connection);
		}
	}

	public Date getDate(int eventID) {
		PreparedStatement prstmt = null;
		Date date = null; 
		Connection connection = null;
		try {
			connection = getConnection();

			prstmt = connection.prepareStatement(GET_DATE);

			prstmt.setInt(1, eventID);

			ResultSet rs = prstmt.executeQuery();

			while (rs.next()) {

				date = rs.getDate("date");

				System.out.println("Date: " + date);

			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			closeResources(prstmt, connection);
		}
		return date;
	}

	public void setDate(Date date, int eventID) { 

		PreparedStatement prstmt = null;
		Connection connection = null;
		try {
			connection = getConnection();

			prstmt = connection.prepareStatement(SET_DATE);

			prstmt.setDate(1, date);
			prstmt.setInt(2, eventID);

			ResultSet rs = prstmt.executeQuery();

			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			closeResources(prstmt, connection);
		}
	}

	public long getStartTime(int eventID) {
		PreparedStatement prstmt = null;
		int startTime = -1;
		Connection connection = null;
		try {
			connection = getConnection();

			prstmt = connection.prepareStatement(GET_STARTTIME);

			prstmt.setInt(1, eventID); 

			ResultSet rs = prstmt.executeQuery();

			while (rs.next()) {

				startTime = rs.getInt("startTime");
				System.out.println("startTime" + startTime);

			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			closeResources(prstmt, connection);
		}
		return startTime;
	}

	public void setStartTime(long startTime, int eventID) {

		PreparedStatement prstmt = null;
		Connection connection = null;
		try {
			connection = getConnection();
			
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
			closeResources(prstmt, connection);
		}
	}

	public long getEndTime(int eventID) {
		PreparedStatement prstmt = null;
		int endTime = -1;
		Connection connection = null;
		try {
			connection = getConnection();

			prstmt = connection.prepareStatement(GET_ENDTIME);

			prstmt.setInt(1, eventID);

			ResultSet rs = prstmt.executeQuery();

			while (rs.next()) {

				endTime = rs.getInt("endTime");

				System.out.println("participantCounter" + endTime);
			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			closeResources(prstmt, connection);
		}
		return endTime;
	}

	public void setEndTime(int eventID) {

		PreparedStatement prstmt = null;
		Connection connection = null;
		try {
			connection = getConnection();

			prstmt = connection.prepareStatement(SET_ENDTIME);

			prstmt.setInt(1, eventID); 

			ResultSet rs = prstmt.executeQuery();

			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			closeResources(prstmt, connection);
		}
	}

	public String getCategory(int eventID) {
		PreparedStatement prstmt = null;
		String category = null;
		Connection connection = null;
		try {
			connection = getConnection();

			prstmt = connection.prepareStatement(GET_CATEGORY);

			prstmt.setInt(1, eventID);

			ResultSet rs = prstmt.executeQuery();

			while (rs.next()) {

				category = rs.getString("category");

				System.out.println("Category: " + category);

			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			closeResources(prstmt, connection);
		}
		return category;
	}

	public void setCategory(int eventID) {

		PreparedStatement prstmt = null;
		Connection connection = null;
		try {
			connection = getConnection();

			prstmt = connection.prepareStatement(SET_CATEGORY);

			prstmt.setInt(1, eventID); 

			ResultSet rs = prstmt.executeQuery();

			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			closeResources(prstmt, connection);
		}
	}

	public String getDescription(int eventID) {
		PreparedStatement prstmt = null;
		String description = null;
		Connection connection = null;
		try {
			connection = getConnection();

			prstmt = connection.prepareStatement(GET_DESCRIPTION);

			prstmt.setInt(1, eventID);

			ResultSet rs = prstmt.executeQuery();

			while (rs.next()) {
				
				description = rs.getString("description");

				System.out.println("Description: " + description);

			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			closeResources(prstmt, connection);
		}
		return description;
	}

	public void setDescription(int eventID) {

		PreparedStatement prstmt = null;
		Connection connection = null;
		try {
			connection = getConnection();

			prstmt = connection.prepareStatement(SET_DESCRIPTION);

			prstmt.setInt(1, eventID); 

			ResultSet rs = prstmt.executeQuery();

			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			closeResources(prstmt, connection);
		}
	}

	public int getMinCapacity(int eventID) {
		PreparedStatement prstmt = null;
		int minCapacity = -1;
		Connection connection = null;
		try {
			connection = getConnection();

			prstmt = connection.prepareStatement(GET_MIN_CAPACITY);

			prstmt.setInt(1, eventID); 

			ResultSet rs = prstmt.executeQuery();

			while (rs.next()) {

				minCapacity = rs.getInt("minCapacity");

				System.out.println("MinCapacity: " + minCapacity);

			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			closeResources(prstmt, connection);
		}
		return minCapacity;
	}

	public void setMinCapacity(int eventID) {

		PreparedStatement prstmt = null;
		Connection connection = null;
		try {
			connection = getConnection();
			
			prstmt = connection.prepareStatement(SET_MIN_CAPACITY);

			prstmt.setInt(1, eventID); 

			ResultSet rs = prstmt.executeQuery();

			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			closeResources(prstmt, connection);
		}
	}

	public int getMaxCapacity(int eventID) {
		PreparedStatement prstmt = null;
		int maxCapacity = -1;
		Connection connection = null;
		try {
			connection = getConnection();
			
			prstmt = connection.prepareStatement(GET_MAX_CAPACITY);

			prstmt.setInt(1, eventID);

			ResultSet rs = prstmt.executeQuery();

			while (rs.next()) {

				maxCapacity = rs.getInt("maxCapacity");

				System.out.println("MaxCapacity: " + maxCapacity);
			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			closeResources(prstmt, connection);
		}
		return maxCapacity;
	}

	public void setMaxCapacity(int eventID) {

		PreparedStatement prstmt = null;
		Connection connection = null;
		try {
			connection = getConnection();
			
			prstmt = connection.prepareStatement(SET_MAX_CAPACITY);

			prstmt.setInt(1, eventID);

			ResultSet rs = prstmt.executeQuery();

			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			closeResources(prstmt, connection);
		}
	}

	public boolean getVisibility(int eventID) {
		PreparedStatement prstmt = null;
		Boolean visibility = null;
		Connection connection = null;
		try {
			connection = getConnection();
			
			prstmt = connection.prepareStatement(GET_VISIBILITY);

			prstmt.setInt(1, eventID);

			ResultSet rs = prstmt.executeQuery();

			while (rs.next()) {

				visibility = rs.getBoolean("visibility");

				System.out.println("visibility: " + visibility);

			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			closeResources(prstmt, connection);
		}
		return visibility;
	}

	public void setVisibility (int eventID) {

		PreparedStatement prstmt = null;
		Connection connection = null;
		try {
			connection = getConnection();
			
			prstmt = connection.prepareStatement(SET_VISIBILITY);

			prstmt.setInt(1, eventID); 

			ResultSet rs = prstmt.executeQuery();

			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			closeResources(prstmt, connection);
		}
	}

	public void getPlace(int eventID) { // Ska vara n�got annat �n void

	}

	public void setPlace(int eventID) {

		PreparedStatement prstmt = null;
		Connection connection = null;
		try {
			connection = getConnection();
			
			prstmt = connection.prepareStatement(SET_PLACE);

			prstmt.setInt(1, eventID);

			ResultSet rs = prstmt.executeQuery();

			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			closeResources(prstmt, connection);
		}
	}


}
