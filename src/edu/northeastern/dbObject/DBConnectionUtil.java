package edu.northeastern.dbObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionUtil {
	
	public void queryOperations(String query) throws SQLException{
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project","root","rootuser");
		Statement stmt = conn.createStatement();
		System.out.println(query);
		stmt.execute(query);
		conn.close();
	}
	
	public ResultSet selectOperations(String query) throws SQLException{
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project","root","rootuser");
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(query);
		return result;
	}

}
