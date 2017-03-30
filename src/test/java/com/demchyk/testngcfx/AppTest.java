package com.demchyk.testngcfx;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.jaxrs.client.WebClient;
import org.testng.annotations.Test;

import javax.ws.rs.core.MediaType;
import java.util.Collections;


public class AppTest{
    public static final String URI ="http://www.omdbapi.com/";
    public static final String ID = "i=tt1520211";

    @Test
    public void testOmdb(){
        WebClient client = WebClient.create(URI, Collections.singletonList(new JacksonJsonProvider())).query(ID).type(MediaType.APPLICATION_JSON);
        OMDBEntry omdb = client.get(OMDBEntry.class);
        System.out.println(omdb.toString());

    }
}
