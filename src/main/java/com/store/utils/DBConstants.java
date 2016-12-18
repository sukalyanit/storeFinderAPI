package com.store.utils;

public class DBConstants {

	protected static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	protected static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/storeLocator";
	protected static final String DB_USER = "root";
	protected static final String DB_PASSWORD = "root";
	public static final String INSERT_STORE = "INSERT INTO STORELOCATOR.STORE"
	+ "(STORE_NUMBER, STORE_NAME, POSTCODE, LONGITUDE, LATITUDE) VALUES"
	+ "(?,?,?,?,?)";
	public static final String SELECT_STORE  = "SELECT * FROM STORELOCATOR.STORE";
}
