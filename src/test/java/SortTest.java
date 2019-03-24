import ApiCall.ApiGateway;
import Recommender.LevelSorter;
import Recommender.Restaurant;
import Recommender.User;
import com.google.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class SortTest {
    public static void main(String[] args)throws Exception{
        ApiGateway gateway = new ApiGateway();
        List<Restaurant> restaurantList = new ArrayList<>();
        LatLng myLocation = new LatLng(-34.9279483,138.6011298);
        String city = "Adelaide";
        gateway.getRestaurants(restaurantList,myLocation,city);
        Thread.sleep(15000);
        LevelSorter levelSorter = new LevelSorter();
        ArrayList<String> feature = new ArrayList();
        feature.add("sushi");
        feature.add("sashimi");
        User user = new User("123",feature,50);
        System.out.println(levelSorter.sortByFeatures(restaurantList,user));
    }
}
