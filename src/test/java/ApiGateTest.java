import ApiCall.ApiGateway;
import Recommender.Restaurant;
import com.google.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class ApiGateTest {
    public static void main(String[] args) throws InterruptedException{
        ApiGateway gateway = new ApiGateway();
        List<Restaurant> restaurantList = new ArrayList<>();
        LatLng myLocation = new LatLng(-34.9279483,138.6011298);
        String city = "Adelaide";
        gateway.getRestaurants(restaurantList,myLocation,city);
        System.out.println(restaurantList.size());
        Thread.sleep(8000);
        int review = 0;
        for(Restaurant r:restaurantList){
            review += r.getReviewCount();
        }
        System.out.println(review);
        System.out.println((review)/restaurantList.size());


    }
}
