package com.store.utils;

import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public interface GeocodingAPI {

	public  HashMap<String,Double> getLongitudeLatitude(String storeName, String storeNumber, String postCode);
}
