package src;  

import java.sql.*;

//import javax.naming.Context;
//import javax.naming.InitialContext;
import javax.sql.DataSource;

//import org.omg.Messaging.SyncScopeHelper;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class UserHandler {

    public static final String GET_NAME = "SELECT name FROM User WHERE userID = ?"; // 1, userID
    public static final String SET_NAME = "UPDATE User SET name = ? WHERE userID = ?;"; //1, userName. 2, userID
    public static final String GET_EMAIL = "SELECT email FROM User WHERE userID = ?"; // 1, userID
    public static final String SET_EMAIL = "UPDATE User SET email = ? WHERE userID = ?"; // 1, email. 2, userID  

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
	
	public String getName(int inputUserID) throws SQLException {
		PreparedStatement prstmt = null;
		String name = null;
		Connection connection = null;
		try {
			connection = getConnection();
			
			prstmt = connection.prepareStatement(GET_NAME);

			prstmt.setInt(1, inputUserID); 
			
			ResultSet rs = prstmt.executeQuery();

			while (rs.next()) {

				name = rs.getString("name");

				System.out.println("Name: " + name);

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
	
		return name;
	}

	public void setName(int inputUserID, String inputUsername) throws SQLException {

		PreparedStatement prstmt = null;
		Connection connection = null;
		try {
			connection = getConnection();

			prstmt = connection.prepareStatement(SET_NAME);

			prstmt.setString(1, inputUsername);
			prstmt.setInt(2, inputUserID); 

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

	public String getEmail(int inputUserID) throws SQLException {
		PreparedStatement prstmt = null;
		String email = null;
		Connection connection = null;
		try {
			connection = getConnection();
			
			prstmt = connection.prepareStatement(GET_EMAIL);

			prstmt.setInt(1, inputUserID); 
			
			ResultSet rs = prstmt.executeQuery();

			while (rs.next()) {

				email = rs.getString("email");

				System.out.println("Email: " + email);

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
	
		return email;
	}

	public void setemail(int inputUserID, String inputEmail) throws SQLException {

		PreparedStatement prstmt = null;
		Connection connection = null;
		try {
			connection = getConnection();

			prstmt = connection.prepareStatement(SET_EMAIL);

			prstmt.setString(1, inputEmail);
			prstmt.setInt(2, inputUserID); 

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
