package ApiCall;

import Recommender.Restaurant;
import Recommender.Today;
import com.google.gson.JsonObject;
import com.google.maps.model.*;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ApiGateway {
    private MapsCaller mapsCaller;
    private YelpExtractor yelpExtractor;
    private WeatherExtractor weatherExtractor;

    public ApiGateway(){
        yelpExtractor = new YelpExtractor(new HttpGetClient());
        mapsCaller = new MapsCaller();
        weatherExtractor = new WeatherExtractor(new HttpGetClient());

    }


    public void getRestaurants(List<Restaurant> restaurantList,LatLng loc,String city){
        MapFilter filter = getMapFilter(city);
        try{
            PlacesSearchResponse response = mapsCaller.requestNearbyRestaurant(loc,filter);
            PlacesSearchResult[] results= response.results;

            ExecutorService executor = Executors.newFixedThreadPool(10);
            Restaurant r = null;

            for(PlacesSearchResult result:results){
                r = createRestaurant(result);
                executor.execute(new ApiCallTask(this,r,result,loc,city,restaurantList));

            }
            Thread.sleep(2000);
            while(response.nextPageToken!=null){
                response = mapsCaller.requestNearbyRestaurant(response);
                results = response.results;
                for(PlacesSearchResult result:results){
                    r = createRestaurant(result);
                    executor.execute(new ApiCallTask(this,r,result,loc,city,restaurantList));
                }
                if(response.nextPageToken!=null)
                    Thread.sleep(2000);
            }
            executor.shutdown();

            /*
            for(PlacesSearchResult result:results){
                completeRestaurant(restaurantList,result,loc,city);
            }
            System.out.println(response.nextPageToken);
            Thread.sleep(2000);
            while(response.nextPageToken!=null){
                response = mapsCaller.requestNearbyRestaurant(response);
                System.out.println(response.nextPageToken);
                results = response.results;
                for(PlacesSearchResult result:results){
                    completeRestaurant(restaurantList,result,loc,city);
                }
            }
            */


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public Restaurant createRestaurant(PlacesSearchResult placesSearchResult){
        Restaurant restaurant = new Restaurant();

        String name = placesSearchResult.name;
        restaurant.setName(name);

        double rating = placesSearchResult.rating;
        restaurant.setRating(rating);

        String address = placesSearchResult.formattedAddress;
        restaurant.setAddress(address);

        return restaurant;
    }

    public void callDetailsApi(Restaurant restaurant,PlacesSearchResult placesSearchResult){
        try{
            PlaceDetails detail = mapsCaller.requestRestaurantDetails(placesSearchResult);
            PriceLevel priceLevel = detail.priceLevel;
            restaurant.setPriceLevel(priceLevel);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void callDistanceApi(Restaurant restaurant, PlacesSearchResult placesSearchResult, LatLng curLoc){
        try{
            DistanceMatrix dm = mapsCaller.requestDistanceMatrix(curLoc,placesSearchResult.geometry.location);
            long distance = mapsCaller.getDistance(dm);
            restaurant.setDistance(distance);
            long walkingTime = mapsCaller.estimateRouteTime(dm);
            restaurant.setWalkingTime(walkingTime);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void callYelpApi(Restaurant restaurant,String city){

        JsonObject object = yelpExtractor.getRestaurantJson(restaurant.getName(),city);
        List<String> tags = yelpExtractor.getTags(object);
        restaurant.setTags(tags);
        int reviewCount = yelpExtractor.getReviewCount(object);
        restaurant.setReviewCount(reviewCount);
    }

    public void completeRestaurant(List<Restaurant> restaurantList, PlacesSearchResult placesSearchResult, LatLng curLoc, String city){
        Restaurant r = createRestaurant(placesSearchResult);
        callDetailsApi(r,placesSearchResult);
        callDistanceApi(r,placesSearchResult,curLoc);
        callYelpApi(r,city);
        System.out.println(r);
        restaurantList.add(r);
    }

    public MapFilter getMapFilter(String city){
        Weather weather = weatherExtractor.getWeather(city);
        return new MapFilter(Today.getInstance(),weather);
    }
}
