import java.sql.*;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class ParticipationHandler {


	
	//Den här hämtar alla deltagare I ett visst event
	public static final String SS = "SELECT * FROM Participation WHERE eventID = ?";
	 
	//Den här hämtar alla event en viss deltagare är registrerad på
	public static final String DD = "SELECT * FROM Participation WHERE userID = ? ";
	 
	//det här tar bort en deltagare ur ett specifikt event
	public static final String RR = "DELETE FROM Participation WHERE eventID = ? AND userID = ?";
	 
	//den här tar bort en deltagare från alla event den är anmäld till
	public static final String TT = " DELETE FROM Participation WHERE userID = ? ";
	 
	//den här tar bort ett event och alla deltagande
	public static final String YY = "DELETE FROM Participation WHERE eventID = ?"; 

	
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

}
