import ApiCall.*;
import Recommender.Restaurant;
import Recommender.Today;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;

import java.util.ArrayList;
import java.util.List;

public class MapsTest {
    public static void main(String[] args){
        Today today = Today.getInstance();
        List<Restaurant> restaurantList = new ArrayList<>();
        try{
            MapsCaller caller = new MapsCaller();
            LatLng myLocation = new LatLng(-34.9279483,138.6011298);
            //LatLng myLocation = new LatLng(-34.9330171,138.5965326);
            //LatLng myLocation = new LatLng(40.4441248,-79.9454435);
            MapFilter filter = new MapFilter(Today.getInstance(),new WeatherExtractor(new HttpGetClient()).getWeather("Adelaide"));
            PlacesSearchResponse response = caller.requestNearbyRestaurant(myLocation,filter);
            while(response.nextPageToken!=null){
                //if(response.nextPageToken==null)
                PlacesSearchResult[] results = caller.getNearbyRestaurant(response);
                System.out.println(response.nextPageToken);

                for(PlacesSearchResult r:results){
                    //System.out.println(r.name+" "+ r.rating+" "+caller.requestRestaurantDistance(myLocation,r.geometry.location).rows[0].elements[0]);
                    LatLng loc = r.geometry.location;
                    DistanceMatrix dm = caller.requestDistanceMatrix(myLocation,loc);
                    System.out.println(r.name +"---Distance:" + caller.getDistance(dm)+" ---Duration:"+caller.estimateRouteTime(dm));
                    //restaurantList.add(caller.generateRestaurant(r,myLocation,today));
                }
                response = caller.requestNearbyRestaurant(response);
                System.out.println(response.nextPageToken);
            }

            /*
            //PlacesSearchResult[] results = caller.requestNearbyRestaurant(myLocation);
            System.out.println(results.length);
            for(PlacesSearchResult r:results){
                //System.out.println(r.name+" "+ r.rating+" "+caller.requestRestaurantDistance(myLocation,r.geometry.location).rows[0].elements[0]);
                LatLng loc = r.geometry.location;
                DistanceMatrix dm = caller.requestDistanceMatrix(myLocation,loc);
                System.out.println(r.name +"---Distance:" + caller.getDistance(dm)+" ---Duration:"+caller.estimateRouteTime(dm));
                //restaurantList.add(caller.generateRestaurant(r,myLocation,today));
            }
            */
            //List<Restaurant> newList =
                    //System.out.println(sortOverallScore(restaurantList));

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
