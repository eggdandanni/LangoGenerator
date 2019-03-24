package ApiCall;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class YelpExtractor {
    private static final String APIKEY = "M7TMGkh_ynyaFgVIWQk8JnR7e6VKhuF0a_YfAXBm1SzM81eRFF_fqmkPY9X9BhXkT-ssfpjif_pCNttJu9wxpVXhxoY92mVa26kIEufNEHFaAIRaP5g6axiolGaPXHYx";
    //private static final String DETAIL_URL = "https://api.yelp.com/v3/businesses/";
    private static final String SEARCH_URL = "https://api.yelp.com/v3/businesses/search";

    private HttpGetClient client;
    private JsonParser parser;

    public YelpExtractor(HttpGetClient client){
        this.client = client;
        parser = new JsonParser();
    }

    public JsonObject getRestaurantJson(String keyword,String city) {
        JsonObject object = null;
        try{
            Response response = client.sendHttpGetRequest(SEARCH_URL+"?term="+keyword+"&location="+city+"&limit=1","Authorization","Bearer "+APIKEY);
            object = parser.parse(response.body().string()).getAsJsonObject();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return object;
    }

    public List<String> getTags(JsonObject object){
        List<String> tags = new ArrayList<String>();

        if(object.getAsJsonArray("businesses").size()>0){
            JsonObject restaurantJson = object.getAsJsonArray("businesses")
                    .get(0)
                    .getAsJsonObject();
            for(JsonElement e:restaurantJson.getAsJsonArray("categories")){
                String tag = e.getAsJsonObject().get("title").getAsString();
                tags.add(tag);
            }
        }
        return tags;
    }

    public int getReviewCount(JsonObject object){
        int count = 0;
        if(object.getAsJsonArray("businesses").size()>0){
            count = object.getAsJsonArray("businesses")
                    .get(0)
                    .getAsJsonObject()
                    .get("review_count")
                    .getAsInt();
        }
        return count;
    }

}
