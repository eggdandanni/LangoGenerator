package ApiCall;

import com.google.maps.*;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;

import java.io.IOException;

public class MapsCaller {


    private static final String APIKEY ="AIzaSyDBPaygtpQ3m-A-cXYIvqQ5Y6ob2P4gACU";
    private static final String APIKEY2 = "AIzaSyDd94wh97maAdXEdqxM2QPb8f7D0Uh0DkY";
    //private static final String APIKEY = "AIzaSyDd94wh97maAdXEdqxM2QPb8f7D0Uh0DkY";
    private static final int SEARCH_RADIUS = 800;
    private static final GeoApiContext context = new GeoApiContext.Builder()
            .apiKey(APIKEY).build();


    public LatLng getCurrentLocation(){
        LatLng location = null;
        //TODO
        return location;
    }


    public PlacesSearchResponse requestNearbyRestaurant(LatLng loc,MapFilter filter) throws ApiException,InterruptedException, IOException {
        // keyword,minPrice,maxPrice,name
        int adjustedRadius = SEARCH_RADIUS + filter.getRadiusDelta();
        NearbySearchRequest request = PlacesApi.nearbySearchQuery(context,loc)
                .radius(adjustedRadius)
                .minPrice(PriceLevel.INEXPENSIVE)
                .maxPrice(PriceLevel.EXPENSIVE)
                .type(PlaceType.RESTAURANT);
                //.keyword("Japanese");
                //.openNow(true);

        PlacesSearchResponse response = request.await();
        return response;
    }

    public PlacesSearchResponse requestNearbyRestaurant(PlacesSearchResponse response) throws ApiException,InterruptedException, IOException {
        NearbySearchRequest request = PlacesApi.nearbySearchNextPage(context,response.nextPageToken);
        PlacesSearchResponse nextResponse = request.await();
        return nextResponse;
    }


    public PlacesSearchResult[] getNearbyRestaurant(PlacesSearchResponse response){
        return response.results;
    }

    public PlaceDetails requestRestaurantDetails(PlacesSearchResult result) throws ApiException,InterruptedException, IOException{
        PlaceDetailsRequest request = new PlaceDetailsRequest(context).placeId(result.placeId);
        PlaceDetails detail = request.await();
        return detail;
    }

    public DistanceMatrix requestDistanceMatrix(LatLng curLoc,LatLng restLoc) throws ApiException,InterruptedException, IOException{
        DistanceMatrixApiRequest request = DistanceMatrixApi.newRequest(context);
        DistanceMatrix distanceMatrix = request.origins(curLoc)
                .destinations(restLoc)
                .mode(TravelMode.WALKING)
                .await();
        return distanceMatrix;
    }

    public long getDistance(DistanceMatrix dm){
        long distance = dm.rows[0].elements[0].distance.inMeters;
        return  distance;
    }

    public long estimateRouteTime(DistanceMatrix dm){
        long durationInMinutes = dm.rows[0].elements[0].duration.inSeconds / 60;
        return durationInMinutes;
    }

}
