package Recommender;

import ApiCall.ApiGateway;
import com.google.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException{
        Restaurant[] resC = new Restaurant[2];

        Today today = Today.getInstance();
        /*
        resC[0] = new Restaurant(3.5,1.2,"White Mojo",0,false,today,1000);
        resC[1] = new Restaurant(5,0.8,"Kawai Danni",2,false,today,888);
        */
        //List<Restaurant> restaurantList = Arrays.asList(resC);
        List<Restaurant> restaurantList = new ArrayList<>();
        LatLng myLocation = new LatLng(-34.9237628,138.590676);
        String city = "Adelaide";
        new ApiGateway().getRestaurants(restaurantList,myLocation,city);
        //System.out.println(restaurantList);
        Thread.sleep(8000);
        System.out.println(RankSumSorter.sortOverallScore(restaurantList));

    }
}
