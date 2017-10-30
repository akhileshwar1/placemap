package com.placemap.placemap;

import android.util.Log;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
//import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.http.json.JsonHttpParser;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;


/**
 * Created by AKHIL on 11-Apr-2016.
 */
@SuppressWarnings("deprecation")
public class googlePlaces {

    /**
     * Global instance of the HTTP transport.
     */
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    // Google API Key
    private static final String API_KEY = "AIzaSyBnmkbtcsLXxncr6sR9tdCM_pKpPCq7S3s"; // place your API key here

    // Google Places serach url's
    private static final String PLACES_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/search/json?";
    private static final String PLACES_TEXT_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/search/json?";
    private static final String PLACES_DETAILS_URL = "https://maps.googleapis.com/maps/api/place/details/json?";
    public static final String PLACES_ADD_URL = "https://maps.googleapis.com/maps/api/place/add/json?key=AIzaSyBnmkbtcsLXxncr6sR9tdCM_pKpPCq7S3s";

    private double _latitude;
    private double _longitude;
    private double _radius;
    JSONObject json1;
    placeadd place;


    /**
     * Searching places
     *
     * @param latitude - latitude of place
     * @param radius   - radius of searchable area
     * @param types    - type of place to search
     * @return list of places
     * @params longitude - longitude of place
     */
    public PlacesList search(double latitude, double longitude, double radius, String types)
            throws Exception {




        try {

            HttpRequestFactory httpRequestFactory = createRequestFactory(HTTP_TRANSPORT);
            HttpRequest request = httpRequestFactory
                    .buildGetRequest(new GenericUrl(PLACES_SEARCH_URL));
            request.getUrl().put("key", API_KEY);
            request.getUrl().put("location", latitude + "," + longitude);
            request.getUrl().put("radius", radius); // in meters
            request.getUrl().put("sensor", "false");
            request.getUrl().put("keyword", "cyra");


            // if (types != null)
              //  request.getUrl().put("type", types);


            PlacesList list = request.execute().parseAs(PlacesList.class);
            // Check log cat for places response status
            Log.d("Places Status", "" + list.status);
            return list;

        } catch (HttpResponseException e) {
            Log.e("Error:", e.getMessage());
            return null;
        }

    }


    public PlaceDetails getPlaceDetails(String reference) throws Exception {
        try {

            HttpRequestFactory httpRequestFactory = createRequestFactory(HTTP_TRANSPORT);
            HttpRequest request = httpRequestFactory
                    .buildGetRequest(new GenericUrl(PLACES_DETAILS_URL));
            request.getUrl().put("key", API_KEY);
            request.getUrl().put("reference", reference);
            request.getUrl().put("sensor", "false");

            PlaceDetails place = request.execute().parseAs(PlaceDetails.class);

            return place;

        } catch (HttpResponseException e) {
            Log.e("Error in Perform Detail", e.getMessage());
            throw e;
        }
    }

    /**
     * Creating http request Factory
     */
    public static HttpRequestFactory createRequestFactory(
            final HttpTransport transport) {
        return transport.createRequestFactory(new HttpRequestInitializer() {
            public void initialize(HttpRequest request) {
                HttpHeaders headers = new HttpHeaders();
                headers.setUserAgent("AndroidHive-Places-Test");
                headers.setContentType("application/json");
                request.setHeaders(headers);
                JsonHttpParser parser = new JsonHttpParser(new JacksonFactory());
                request.addParser(parser);
            }
        });
    }


    public placeadd add(final String lat,final String lon, String phone, String name, String address) throws IOException, JSONException {
       // Map<String, String> json = new HashMap<String, String>();
        // json.put("lat", "-33.8669710");
        //json.put("lon", "11.1958750");

        //json.put("accuracy", "50");
        //json.put("name", "Google Shoes!");
        //json.put("phone_number","(02) 9374 4000");
        // json.put("address","48 Pirrama Road, Pyrmont, NSW 2009, Australia");

        JSONObject body= new JSONObject();
        JSONObject location= new JSONObject();
        JSONArray ar=new JSONArray();

      try
      {
          ar.put(0,"pathos");
          location.put("lat",-33.8669710);
          location.put("lon", 151.1958750);
          body.put("location",location);
          body.put("accuracy",50);
          body.put("name","Google Shoes!");
          body.put("phone_number","(02) 9374 4000");
          body.put("address","48 Pirrama Road, Pyrmont, NSW 2009, Australia");
          body.put("types",ar);
          body.put("website","http://www.google.com.au/");
          body.put("language","en-AU");

      }catch(JSONException e)
      {

      }

        // json.put("types", "pathos");


        //json.put("language", "en-AU");
        String postData = "{\n \"location\":{\n  \"lat\":  19.022755,\n  \"lng\": 72.853733\n },\n \"accuracy\": 50,\n \"name\": \"Google Shoes!\",\n \"phone_number\": \"(02) 9374 4000\", \n \"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\",\n \"types\":[\"hospital\"],\n \"website\": \"http://www.google.com.au/\",\n \"language\": \"en-AU\"\n}";
        try {
           json1 = new JSONObject(postData);
        }catch (JSONException j)
        {
            Log.e("Error:", j.getMessage());

        }

        JSONStringer json = new JSONStringer();
        if (body!=null)
        {
            Iterator<String> itKeys = body.keys();
            if(itKeys.hasNext())
                json.object();
            while (itKeys.hasNext())
            {
                String k=itKeys.next();
                json.key(k).value(body.get(k));
                Log.e("keys "+k,"value "+body.get(k).toString());
            }
        }
        json.endObject();



        HttpClient httpClient = new DefaultHttpClient();

        try {
            Gson son1=new Gson();
            HttpPost request = new HttpPost(PLACES_ADD_URL);
            StringEntity params =new StringEntity(postData);
            params.setContentType("application/json;charset=UTF-8");
            params.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json;charset=UTF-8"));

           request.addHeader("Content-Type", "application/json");
            request.addHeader("Accept","application/json");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);
            String response1 = EntityUtils.toString(response.getEntity());

            Gson gson = new Gson();
            place= gson.fromJson(response1, placeadd.class);
            // handle response here...
        }catch (Exception ex) {
            // handle exception here
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
       return place;
    }
}
