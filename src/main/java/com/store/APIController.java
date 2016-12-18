package com.store;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.dao.Store;

@Component
@Path("/")
public class APIController {
	
	@Autowired
	private StoreFinderService storeFinderService;
	
	@GET
	@Path("addStore/{storeName}/{storeNumber}/{postCode}")
	@Produces("application/json")
	public Response addStore(@PathParam("storeName") String storeName, 
			@PathParam("storeNumber") String storeNumber, @PathParam("postCode")
	        String postCode){
		
		Store store = storeFinderService.addStore(storeName, storeNumber, postCode);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null;
		try {
			jsonInString = mapper.writeValueAsString(store);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			String failureString = "{\"Failure\":\"ParseFailure\"}";
			/*FAILURE CASE*/
			return Response.status(500).entity(failureString).build();
		}
		if(null == store.getLongitude() && null == store.getLatitude()){
			/*DATA NOT FOUND CASE*/
			return Response.status(404).entity(jsonInString).build();
		}
		else
			return Response.status(200).entity(jsonInString).build();///*SUCCESS CASE*/

	}
	
	@GET
	@Path("findStore/{longitude}/{latitude}")
	@Produces("application/json")
	public Response findStore(@PathParam("longitude") Double longitude, @PathParam("latitude")
	 Double latitude){
		
		Store store = storeFinderService.findStore(longitude, latitude);
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null;
		try {
			jsonInString = mapper.writeValueAsString(store);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			String failureString = "{\"Failure\":\"ParseFailure\"}";
			/*FAILURE CASE*/
			return Response.status(500).entity(failureString).build();
		}
		if(null == store.getStoreName() && null == store.getStoreNumber() 
				&& null == store.getPostCode()){
			/*DATA NOT FOUND CASE*/
			return Response.status(404).entity(jsonInString).build();
		}
		else
		    return Response.status(200).entity(store.toString()).build();///*SUCCESS CASE*/
	}
	
}
