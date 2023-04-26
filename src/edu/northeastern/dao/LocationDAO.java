package edu.northeastern.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.northeastern.dbObject.DBConnectionUtil;
import edu.northeastern.models.Location;

public class LocationDAO {
	
	public void insertNewLocation(String locationName) throws SQLException {
		String insertQuery = "Insert into Location values('"+locationName+"')";
		DBConnectionUtil dbConnectionUtil =  new DBConnectionUtil();
		dbConnectionUtil.queryOperations(insertQuery);
	}
	
	public Location getLocation(String locationName) throws SQLException {
		String getLocationQuery = "Select * from Location where LocationName='"+locationName+"'";
		DBConnectionUtil dbConnectionUtil =  new DBConnectionUtil();
		ResultSet results = dbConnectionUtil.selectOperations(getLocationQuery);
		results.next();
		Location location = new Location(results.getString("LocationName"));
		return location;
	}
	
	public ArrayList<String> getAllLocations() throws SQLException {
		String getLocationQuery = "Select * from Location";
		DBConnectionUtil dbConnectionUtil =  new DBConnectionUtil();
		ResultSet results = dbConnectionUtil.selectOperations(getLocationQuery);
		ArrayList<String> locationList = new ArrayList<>();
		while(results.next()) {
			locationList.add(results.getString("LocationName"));
		}
		return locationList;
	}

}