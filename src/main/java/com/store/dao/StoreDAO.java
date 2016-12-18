package com.store.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import com.store.dao.Store;;

@Component
public interface StoreDAO {
  
	public int addStore(String storeName, String storeNumber, String postCode,
			Double longitude, Double latitude);
	
	public List<Store> fetchAllStore(Double longitude, Double latitude);
}
