package com.store;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyInitialization  extends ResourceConfig{

	 /**
     * Register JAX-RS application components.
     */
    public JerseyInitialization(){
        this.packages("com.store");
    }
    
}
