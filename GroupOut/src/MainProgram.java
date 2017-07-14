package src;  


import java.sql.*;

//import com.mysql.jdbc.jdbc2.optional.*;
//
//import java.util.*;
//
//import javax.naming.*;
//import javax.sql.DataSource;
//
//import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MainProgram {
	static MainProgram program = new MainProgram();

	// ALL USER INPUTS "KNAPPAR"

	UserHandler uh = new UserHandler();
	TestFiles tf = new TestFiles();

	public static void main(String[] args) throws SQLException {

	}
	
	public void printAllData() throws SQLException{
		tf.getAllUsername();
		tf.getAllEventNames(); 
		tf.getAllEventIDs(); 
	}
	public void returnDate(){
		tf.returnLocalDate(); 
	}

	public void addDataToDatabase(){
		tf.oneTimeUseAddLoadsOfUsers();
		tf.oneTimeUseAddLoadsOfEvents(); 
		tf.oneTimeUserAddLoadsOfParticipations();
	}


}

