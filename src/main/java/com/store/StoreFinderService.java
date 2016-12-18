package com.store;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.dao.Store;
import com.store.dao.StoreDAO;
import com.store.utils.GeocodingAPI;
import com.store.utils.HelperUtils;

@Service
public class StoreFinderService {

	@Autowired
	public StoreDAO storeDAO;
	
	@Autowired
	public GeocodingAPI geocodingAPI;
	
	public Store addStore(String storeName, String storeNumber, String postCode){
		
		HashMap<String, Double> longLatMap = geocodingAPI.getLongitudeLatitude(storeName, storeNumber, postCode);
		
		if(null == longLatMap ){
			return(new Store(null, null, null, null, null));
		}
		
		storeDAO.addStore(storeName, storeNumber, postCode, longLatMap.get("longitude"), longLatMap.get("latitude"));
		
		return new Store(storeNumber, storeName, postCode, longLatMap.get("longitude"), longLatMap.get("latitude"));
	}
	
	public Store findStore(Double longitude, Double latitude){
				
		List<Store> storeList = storeDAO.fetchAllStore(longitude, latitude);
		
		if(null!=storeList && storeList.size() == 0){
			return new Store(null, null, null, null, null);
		}
		double minimumDistance = 0;
		Store nearestStore = null;
		boolean firstTime = true;
		for(Store store : storeList){
			double thisDistance= HelperUtils.distance(latitude, longitude, 
					store.getLatitude(), store.getLongitude(), "M");
			
			System.out.println("Currect Distance --->"+thisDistance);
			if(firstTime){
				minimumDistance = thisDistance;
				nearestStore = store;
				System.out.println("First Comparison");
				firstTime = false;
			}
			else{
				if(thisDistance < minimumDistance){
					minimumDistance = thisDistance;
					nearestStore = store;
					System.out.println("Next Comparison");
				}
			}
			
		}
		 return nearestStore;
	}
}
