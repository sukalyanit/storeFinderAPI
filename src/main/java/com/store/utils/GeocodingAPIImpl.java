package com.store.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.stereotype.Component;

@Component
public class GeocodingAPIImpl implements GeocodingAPI{

	public HashMap<String, Double> getLongitudeLatitude(String storeName, String storeNumber, String postCode) {
		// TODO Auto-generated method stub
		HashMap<String, Double> longLatMap = new HashMap<>();

		try {
			URL url = new URL(CommonConstants.GEOCODNG_BASE_URL
					+ "address="+postCode
					+ ","
					+ storeName
					+ ","
					+ storeNumber
					+ "&key=AIzaSyBE7JYy65NYXP09GZs930sxlNP6JVxNCxo");
			System.out.println("URL --->"+url);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			String output;
			StringBuffer response = new StringBuffer();
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				response.append(output);
			}
			System.out.println("RESPONSE--->"+response);
			conn.disconnect();
			
			longLatMap = HelperUtils.parseJSON(response.toString());
			
		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }
		
		return longLatMap;
	}
	
}
