import java.sql.*;

import com.mysql.jdbc.jdbc2.optional.*;

import java.util.*;

import javax.naming.*;
import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MainProgram {
	static MainProgram program = new MainProgram();

	

	public static void main(String[] args) throws SQLException {
		
		

		// program.testAllMethods();


	}

	public void testAllMethods() throws SQLException {
//		uh.getUsername(0);
//		uh.setUsername(0);
//		uh.getParticipantCounter(0);
//		uh.increaseParticipantCounter(0);
//		uh.getHostCounter(0);
//		uh.increaseHostCounter(0);
//		uh.getGender(0);
//		uh.setGender(0);
//		uh.getAge(0);
//		uh.setAge(0);
//		uh.getName(0);
//		uh.setName(0);
	}



}



//ALL USER INPUTS "KNAPPAR"

//	UserHandler uh = new UserHandler();

	// SKAPA KOPPLING TILL DATABAS (connection)
	// INITIERA STATEMENTS ETC
	


//	public static DataSource getMySQLDataSource() { // Funkar denna som connect
//		MysqlDataSource mysqlDS = null;
//
//		try {
//			mysqlDS = new MysqlDataSource();
//			mysqlDS.setURL("jdbc:mysql://localhost:3306/mydb");
//			mysqlDS.setUser("root");
//			mysqlDS.setPassword("");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return mysqlDS;
//	}
//
//	public Connection getConnection() throws SQLException {
//		Connection connection = null;
//
//		// Get the MySqlDataSource
//		
//		System.out.println("Verifying access");
//		DataSource dataSource = getMySQLDataSource();
//
//		// Get connection from the database
//
//		System.out.println("Connecting database...");
//		connection = dataSource.getConnection();
//
//
//		return connection;
//
//	}

//	private void getUserID(int inputUserID) {
//
//		PreparedStatement prstmt = null;
//		Connection connection = null;
//		try {
//			connection = getConnection();
//			System.out.println("Database connected!");
//			// Execute query
//	
//			prstmt = connection.prepareStatement();
//
//			prstmt.setInt(1, inputUserID);
//
//			ResultSet rs = prstmt.executeQuery();
//
//
//			rs.close();
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		finally {
//			// Finally block used to close resources
//				closePrstmt(prstmt);
//				closeConnection(connection); 
//		}
//	}
//	
//	public void closePrstmt(PreparedStatement prstmt){
//		try {
//			if (prstmt != null) {
//				prstmt.close();
//			}
//		} catch (SQLException sqlException) {
//			sqlException.printStackTrace();
//		}
//
//	}
//	
//	public void closeConnection(Connection connection){
//		try {
//			if (connection != null) {
//				connection.close();
//			}
//		} catch (SQLException sqlException) {
//			sqlException.printStackTrace();
//		}
//	}