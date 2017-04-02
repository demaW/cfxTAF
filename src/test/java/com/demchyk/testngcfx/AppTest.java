package com.demchyk.testngcfx;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import javafx.scene.web.WebView;
import org.apache.cxf.jaxrs.client.WebClient;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Random;


public class AppTest {
    static final String MONEY_URI = "http://api.fixer.io/latest";
    static final String CUR_PARAM = "base=USD";
    public static final String URI = "http://www.omdbapi.com/";
    public static final String ID = "i=tt1219827";
    static final String LOCAL_URI="http://localhost:3000";
    static final String LOCAL_PATH = "cars";


    @Test
    public void testOmdb() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        WebClient client = WebClient.create(URI, Collections.singletonList(new JacksonJsonProvider(mapper))).query(ID).type(MediaType.APPLICATION_JSON);
        OMDBEntry omdb = client.get(OMDBEntry.class);
        Assert.assertEquals("Ghost in the Shell", omdb.getTitle());

    }

    @Test
    public void moneyTest() {
        WebClient client = WebClient.create(MONEY_URI).query(CUR_PARAM).
                type(MediaType.APPLICATION_JSON_TYPE);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        Money money = null;
        try {
            money = mapper.readValue(client.get(String.class), Money.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(money);
    }

    @Test
    public void testJSONToFile() {
        Car car = new Car("BMW", "M3");
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("src/test/resources/car.json"), car);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(Files.exists(Paths.get("src/test/resources/car.json")));
    }

    @Test(dependsOnMethods = "testJSONToFile")
    public void testFromFileToObject() {
        Car car = null;
        Car carToCompare = new Car("BMW", "M3");
        ObjectMapper mapper = new ObjectMapper();
        try {
            car = mapper.readValue(new File("src/test/resources/car.json"), Car.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(car.toString());
        Assert.assertEquals(car, carToCompare);
    }

    @Test
    public void testPots(){
        ObjectMapper objectMapper = new ObjectMapper();
        Car car = new Car("BMW", "M3" + new Random(100).nextInt());
        WebClient client = WebClient.create(LOCAL_URI).path(LOCAL_PATH).type(MediaType.APPLICATION_JSON_TYPE);
        Response response = null;
        try {
           response = client.post(objectMapper.writeValueAsString(car));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(response.getStatus());
    }
}
