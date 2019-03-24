package ApiCall;

import Recommender.Restaurant;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlacesSearchResult;

import java.util.List;
import java.util.Random;

public class ApiCallTask implements Runnable{
    ApiGateway gateway;
    Restaurant restaurant;
    PlacesSearchResult placesSearchResult;
    LatLng curLoc;
    String city;
    List<Restaurant> restaurantList;

    public ApiCallTask(ApiGateway gateway, Restaurant restaurant, PlacesSearchResult placesSearchResult,
                       LatLng curLoc, String city, List<Restaurant> restaurantList) {
        this.gateway = gateway;
        this.restaurant = restaurant;
        this.placesSearchResult = placesSearchResult;
        this.curLoc = curLoc;
        this.city = city;
        this.restaurantList = restaurantList;
    }


    @Override
    public void run(){
        Random rand = new Random();
        try{
            gateway.callDetailsApi(restaurant,placesSearchResult);
            Thread.sleep(rand.nextInt(1000)+1000);
            gateway.callDistanceApi(restaurant,placesSearchResult,curLoc);
            gateway.callYelpApi(restaurant,city);
            System.out.println(restaurant);
            restaurantList.add(restaurant);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
