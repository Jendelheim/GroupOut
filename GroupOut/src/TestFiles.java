package src;  


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.time.LocalDate;
import java.time.ZoneId;

public class TestFiles {

	static final String SELECT_ALL_USERNAMES = "SELECT name from User;";
	static final String SELECT_ALL_USERS = "SELECT * FROM User;";
	static final String INSERT_USER = "INSERT INTO User (name, email, password) VALUES (?, ?, ?);";

	static final String SELECT_ALL_EVENTNAMES = "SELECT eventName from Event;";
	static final String SELECT_ALL_EVENTS = "SELECT * FROM Event";
	static final String INSERT_EVENT = "INSERT INTO Event (eventName, leaderID, date, startTime, endTime , category, description, minCapacity, maxCapacity, place,  visibility, numberOfRegistrations) VALUES (?, (SELECT userID from User WHERE userID='170'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

	static final String INSERT_PARTICIPATION = "INSERT INTO Participation (eventID, userID) VALUES (?,?);"; 
	static final String SELECT_ALL_PARTICIPATIONS = "SELECT eventID FROM Participation;"; 
	
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

		System.out.println("Verifying access");
		DataSource dataSource = getMySQLDataSource();
		System.out.println("Connecting database...");
		connection = dataSource.getConnection();

		System.out.println("Connection completed!");
		return connection;
	}

	public void testConnection() {
		try {
			getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	
	public LocalDate returnLocalDate() {
		LocalDate todayLocalDate = LocalDate.now(ZoneId.of("Europe/Stockholm"));
		System.out.println(todayLocalDate);
		return todayLocalDate;
	}
	
	public void insertUser(String name, String email, String password) {
		PreparedStatement prstmt = null;
		Connection connection = null;
		try {
			connection = getConnection();

			prstmt = connection.prepareStatement(INSERT_USER);

			prstmt.setString(1, name);
			prstmt.setString(2, email);
			prstmt.setString(3, password);

			boolean rs = prstmt.execute(); // http://stackoverflow.com/questions/1905607/cannot-issue-data-manipulation-statements-with-executequery

			System.out.println(rs);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			closePrstmt(prstmt);
			closeConnection(connection);
		}
	}

	public void insertEvent(String eventName, LocalDate date, String startTime,
			String endTime, String category, String description, int minCapacity, int maxCapacity, int place, boolean visibility, int numberOfRegistrations) {
		PreparedStatement prstmt = null;
		Connection connection = null;

		try {
			connection = getConnection();
			
			prstmt = connection.prepareStatement(INSERT_EVENT);
			
			prstmt.setString(1, eventName);
			prstmt.setObject(2, date);
			prstmt.setString(3, startTime);
			prstmt.setString(4, endTime);
			prstmt.setString(5, category);
			prstmt.setString(6, description);
			prstmt.setInt(7, minCapacity);
			prstmt.setInt(8, maxCapacity);
			prstmt.setInt(9, place);
			prstmt.setBoolean(10, visibility);
			prstmt.setInt(11, numberOfRegistrations);


			boolean rs = prstmt.execute(); // http://stackoverflow.com/questions/1905607/cannot-issue-data-manipulation-statements-with-executequery

			System.out.println(rs);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			closePrstmt(prstmt);
			closeConnection(connection);
		}
	}
	
	public void insertParticipation(int eventID, int userID){
		PreparedStatement prstmt = null;
		Connection connection = null;
		try {
			connection = getConnection();
			
			prstmt = connection.prepareStatement(INSERT_PARTICIPATION);

			prstmt.setInt(1, eventID);
			prstmt.setInt(2, userID);


			boolean rs = prstmt.execute(); // http://stackoverflow.com/questions/1905607/cannot-issue-data-manipulation-statements-with-executequery

			System.out.println(rs);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			closePrstmt(prstmt);
			closeConnection(connection);
		}
	}
	
	public String getAllUsername() throws SQLException {
		PreparedStatement prstmt = null;
		String username = null;
		Connection connection = null;
		try {
			connection = getConnection();

			prstmt = connection.prepareStatement(SELECT_ALL_USERNAMES);

			ResultSet rs = prstmt.executeQuery();

			while (rs.next()) {

				username = rs.getString("name");

				System.out.println("name: " + username);
			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			closePrstmt(prstmt);
			closeConnection(connection);
		}

		return username;
	}

	public String getAllUsers() throws SQLException {
		PreparedStatement prstmt = null;
		String username = null;
		Connection connection = null;
		try {
			connection = getConnection();

			prstmt = connection.prepareStatement(SELECT_ALL_USERS);

			ResultSet rs = prstmt.executeQuery();

			while (rs.next()) {
				
				System.out.println(rs.next());

			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			closePrstmt(prstmt);
			closeConnection(connection);
		}

		return username;
	}

	public String getAllEventNames() throws SQLException {
		PreparedStatement prstmt = null;
		String eventName = null;
		Connection connection = null;
		try {
			connection = getConnection();

			prstmt = connection.prepareStatement(SELECT_ALL_EVENTNAMES);

			ResultSet rs = prstmt.executeQuery();

			while (rs.next()) {

				eventName = rs.getString("eventName");

				System.out.println("eventName: " + eventName);

			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			closePrstmt(prstmt);
			closeConnection(connection);
		}

		return eventName;
	}

	public int getAllEventIDs(){
		PreparedStatement prstmt = null;
		int eventID = 0;
		Connection connection = null;
		try {
			connection = getConnection();

			prstmt = connection.prepareStatement(SELECT_ALL_EVENTS);

			ResultSet rs = prstmt.executeQuery();

			while (rs.next()) {

				eventID = rs.getInt("eventID");

				System.out.println("eventID: " + eventID);
			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			closePrstmt(prstmt);
			closeConnection(connection);
		}

		return eventID;
	}
	
	public void testIncreaseParticipationCounter(){ // Tests both get and increase "participationCounter" 
		int userID = 101; 
		
	}
	
	public void testIncreaseHostCounter(){ // Tests both get and increase "hostCounter" 
		int userID = 102; 
		
			
	}
	
	public void testChangeDescriptionOfUser(){ // Tests both get and update description 
		int userID = 103; 
		
	}
	
	
	
	
	

	
	
	
	
	
	
	
	public void oneTimeUseAddLoadsOfEvents() {

//		insertEvent("Diddes cykling", returnLocalDate(), "1600", "1900", "Cykel",
//				"10 mil cykling, sisten fram suger!", 3, 15, 8000, true, 0);
//		insertEvent("Amalias Yoga", returnLocalDate(), "1500", "1600", "Yoga",
//				"Avancerad yoga, ta med yogamatta!", 5, 10, 8000, true, 0);
//		insertEvent("Victors parkour",  returnLocalDate(), "2100", "2300", "Parkour",
//				"Underground parkour med start vid Björns trädgård, Medborgarplatsen!", 3, 10, 8000, true, 0);
//		insertEvent( "Axels simning", returnLocalDate(), "0900", "1000", "simning",
//				"Motionssimning på GIH! Glöm inte badkläder!", 5, 10, 8000, true, 0);
//		insertEvent("Coleens Marathon", returnLocalDate(), "0900", "1000", "löpning",
//				"Högt tempo, siktar på under 4 timmar!", 5, 10, 8000, true, 0);
//		insertEvent("Olivers barnvagns-powerwalk", returnLocalDate(), "0700", "0900", "power-walk",
//				"Föräldrapowerwalk med barnvagnar!", 5, 10, 8000, true, 0);

		
		
		insertEvent("Olivers barnvagns-powerwalk", returnLocalDate(), "0700", "0900", "power-walk", "Föräldrapowerwalk med barnvagnar!", 5, 10, 8000, true, 0);

	}

	public void oneTimeUseAddLoadsOfUsers() {
        insertUser( "Adam", "example@gmail.com", "Password1");
        insertUser( "Eva", "example@gmail.com",  "Password2");
        insertUser( "Gabby", "example@gmail.com", "Password3");
        insertUser( "Berit", "example@gmail.com", "Password4");
        insertUser( "Henrik", "example@gmail.com", "Password5");
        insertUser( "Victor", "example@gmail.com",  "Password6");
        insertUser( "Amira", "example@gmail.com", "Password7");
        insertUser( "Marie", "example@gmail.com",  "Password8");
        insertUser( "Caroline", "example@gmail.com",  "Password9");
        insertUser( "Didrik", "example@gmail.com",  "Password10");
        insertUser( "Johan", "example@gmail.com",  "Password11");
        insertUser( "Didrik", "example@gmail.com",  "Password12");
        insertUser( "Axel", "example@gmail.com",  "Password13");
        insertUser( "Antonia", "example@gmail.com",  "Password14");
        insertUser( "Coleen", "example@gmail.com",  "Password15");
        insertUser( "Amalia", "example@gmail.com",  "Password16");
        insertUser( "Victor", "example@gmail.com",  "Password17");
        insertUser( "Andrea", "example@gmail.com",  "Password18");
        insertUser( "Louise", "example@gmail.com",  "Password19");
        insertUser( "Susanne", "example@gmail.com", "Password20");
        insertUser( "Maria", "example@gmail.com",  "Password21");
        insertUser( "Oliver", "example@gmail.com",  "Password22");
        insertUser( "Adam", "example@gmail.com", "Password23");
        insertUser( "Eva", "example@gmail.com",  "Password24");
        insertUser( "Gabby", "example@gmail.com",  "Password25");
        insertUser( "Berit", "example@gmail.com",  "Password26");
        insertUser( "Henrik", "example@gmail.com",  "Password27");
        insertUser( "Victor", "example@gmail.com",  "Password28");
        insertUser( "Amira", "example@gmail.com",  "Password29");
        insertUser( "Marie", "example@gmail.com", "Password30");
        insertUser( "Caroline", "example@gmail.com",  "Password31");
        insertUser( "Didrik", "example@gmail.com", "Password32");
        insertUser( "Johan", "example@gmail.com",  "Password33");
        insertUser( "Didrik", "example@gmail.com",  "Password34");
        insertUser( "Axel", "example@gmail.com",  "Password35");
        insertUser( "Antonia", "example@gmail.com", "Password36");
        insertUser( "Coleen", "example@gmail.com",  "Password37");
        insertUser( "Amalia", "example@gmail.com",  "Password38");
        insertUser( "Victor", "example@gmail.com",  "Password39");
        insertUser( "Andrea", "example@gmail.com",  "Password40");
        insertUser( "Louise", "example@gmail.com",  "Password41");
        insertUser( "Susanne", "example@gmail.com",  "Password42");
        insertUser( "Maria", "example@gmail.com",  "Password43");
        insertUser( "Oliver", "example@gmail.com",  "Password44");
        insertUser( "Adam", "example@gmail.com",  "Password45");
        insertUser( "Eva", "example@gmail.com", "Password46");
        insertUser( "Gabby", "example@gmail.com", "Password47");
        insertUser( "Berit", "example@gmail.com",  "Password48");
        insertUser( "Henrik", "example@gmail.com","Password49");
        insertUser( "Victor", "example@gmail.com", "Password50");
        insertUser( "Amira", "example@gmail.com",  "Password51");
        insertUser( "Marie", "example@gmail.com",  "Password52");
        insertUser( "Caroline", "example@gmail.com",  "Password53");
        insertUser( "Didrik", "example@gmail.com", "Password54");
        insertUser( "Johan", "example@gmail.com",  "Password55");
        insertUser( "Didrik", "example@gmail.com",  "Password56");
        insertUser( "Axel", "example@gmail.com",  "Password57");
        insertUser( "Antonia", "example@gmail.com",  "Password58");
        insertUser( "Coleen", "example@gmail.com", "Password59");
        insertUser( "Amalia", "example@gmail.com",  "Password60");
        insertUser( "Victor", "example@gmail.com",  "Password61");
        insertUser( "Andrea", "example@gmail.com",  "Password62");
        insertUser( "Louise", "example@gmail.com", "Password63");
        insertUser( "Susanne", "example@gmail.com",  "Password64");
        insertUser( "Maria", "example@gmail.com", "Password65");
        insertUser( "Oliver", "example@gmail.com", "Password66");
        insertUser( "Adam", "example@gmail.com",  "Password67");
        insertUser( "Eva", "example@gmail.com",  "Password68");
        insertUser( "Gabby", "example@gmail.com", "Password69");
        insertUser( "Berit", "example@gmail.com", "Password70");
        insertUser( "Henrik", "example@gmail.com",  "Password71");
        insertUser( "Victor", "example@gmail.com",  "Password72");
        insertUser( "Caroline", "example@gmail.com",  "Password73");
        insertUser( "Didrik", "example@gmail.com",  "Password74");
        insertUser( "Johan", "example@gmail.com",  "Password75");
        insertUser( "Didrik", "example@gmail.com",  "Password76");
        insertUser( "Axel", "example@gmail.com",  "Password77");
        insertUser( "Antonia", "example@gmail.com",  "Password78");
        insertUser( "Coleen", "example@gmail.com",  "Password79");
        insertUser( "Amalia", "example@gmail.com",  "Password80");
        insertUser( "Victor", "example@gmail.com", "Password81");
        insertUser( "Andrea", "example@gmail.com",  "Password82");
        insertUser( "Louise", "example@gmail.com", "Password83");
        insertUser( "Susanne", "example@gmail.com",  "Password84");
        insertUser( "Maria", "example@gmail.com",  "Password85");
        insertUser( "Oliver-pierre", "example@gmail.com", "Password86");

	}

	public void oneTimeUserAddLoadsOfParticipations(){
		insertParticipation(1001,120);
		insertParticipation(1001,110);
		insertParticipation(1001,123);
		insertParticipation(1001,121);
		insertParticipation(1001,116);
		insertParticipation(1002,120);
		insertParticipation(1002,115);
		insertParticipation(1003,120);
		insertParticipation(1004,120);
		insertParticipation(1002,102);
		insertParticipation(1003,101);
		insertParticipation(1004,116);
		
	}
}