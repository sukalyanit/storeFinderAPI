package com.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.store.dao.Store;
import com.store.utils.DBConstants;
import com.store.utils.HelperUtils;

/*
 * Initially Thought of Using Hibernate, found some problem on JPA API Class Loading.
 * Hence Below Implementation
 * */

@Component
public class StoreDAOImpl implements StoreDAO{
	@Override
	public int addStore(String storeName, String storeNumber, String postCode, Double longitude, Double latitude) {
		// TODO Auto-generated method stub
		
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		int status = 0;
		try {
			dbConnection = HelperUtils.dbConnection();
			preparedStatement = dbConnection.prepareStatement(DBConstants.INSERT_STORE);

			preparedStatement.setInt(1, Integer.parseInt(storeNumber));
			preparedStatement.setString(2, storeName);
			preparedStatement.setString(3, postCode);
			preparedStatement.setDouble(4, longitude);
			preparedStatement.setDouble(5, latitude);
			
			status = preparedStatement.executeUpdate();

			System.out.println("Record is inserted into STORELOCATOR.STORE table!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return status;
	}

	@Override
	public List<Store> fetchAllStore(Double longitude, Double latitude) {
		// TODO Auto-generated method stub
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		List<Store> storeList = new ArrayList<>();
		ResultSet rs = null;
		
		int status = 0;
		try {
			dbConnection = HelperUtils.dbConnection();
			preparedStatement = dbConnection.prepareStatement(DBConstants.SELECT_STORE);

			
			rs = preparedStatement.executeQuery();

			while(rs.next())  {
			
				Store store = new Store(String.valueOf(rs.getInt(1)), 
						rs.getString(2), rs.getString(3),
						rs.getDouble(4), rs.getDouble(5));
				
				storeList.add(store);
				System.out.println(String.valueOf(rs.getInt(1)) + 
						rs.getString(2) + rs.getString(3) +
						rs.getDouble(4) + rs.getDouble(5));
			}
			
			System.out.println("Record Fetched From STORELOCATOR.STORE table!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		
		
		return storeList;
	}

	
}
