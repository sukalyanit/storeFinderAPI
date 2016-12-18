package com.store.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class HelperUtils {

	public static HashMap<String, Double> parseJSON(String response){
		
		JSONParser parser = new JSONParser();
		Object obj;
		JSONObject jsonObj=null;
		try {
			obj = parser.parse(response.toString());
			jsonObj = (JSONObject) obj;
			System.out.println(jsonObj);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray res = (JSONArray) jsonObj.get("results");
		
		if(res.size() == 0 && jsonObj.get("status").equals("ZERO_RESULTS")){
			return null;
		}
		JSONObject jObj = (JSONObject) res.get(0);
		JSONObject jjObj= (JSONObject) jObj.get("geometry");
		JSONObject jjjObj= (JSONObject) jjObj.get("location");
		
		Double latitude = (Double) jjjObj.get("lat");
		System.out.println(latitude);

		Double longitude = (Double) jjjObj.get("lng");
		System.out.println(longitude);
		
		HashMap<String, Double> latLongMap = new HashMap<>();
		latLongMap.put("latitude", latitude);
		latLongMap.put("longitude", longitude);
		
		return latLongMap;
}
	public static Connection dbConnection(){
		Connection dbConnection = null;

		try {

			Class.forName(DBConstants.DB_DRIVER);

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}

		try {

			dbConnection = DriverManager.getConnection(
					DBConstants.DB_CONNECTION, DBConstants.DB_USER,DBConstants.DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;
	}
	
	public static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;

		return dist;
	}
	
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts decimal degrees to radian:*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}
	
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts radians to decimal degrees
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
}
