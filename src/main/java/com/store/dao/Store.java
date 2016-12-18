package com.store.dao;

public class Store {

	private String storeNumber;
	
	private String storeName;
	
	private String postCode;
	
	private Double longitude;
	
	private Double latitude;

	public Store(String storeNumber, String storeName, String postCode, Double longitude, Double latitude){
		this.storeName = storeName;
		this.storeNumber = storeNumber;
		this.postCode = postCode;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	public String getStoreNumber() {
		return storeNumber;
	}

	public void setStoreNumber(String storeNumber) {
		this.storeNumber = storeNumber;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	@Override
	public String toString() {
		return "Store [storeNumber=" + storeNumber + ", storeName=" + storeName + ", postCode=" + postCode + ", longitude=" + longitude + ", latitude=" + latitude + "]";
	}
}
