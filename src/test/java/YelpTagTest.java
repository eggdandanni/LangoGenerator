import ApiCall.HttpGetClient;
import ApiCall.YelpExtractor;
import com.google.gson.JsonObject;

public class YelpTagTest {
    public static void main(String[] args){
        YelpExtractor extractor = new YelpExtractor(new HttpGetClient());
        try{
            JsonObject object = extractor.getRestaurantJson("Mandoo","Adelaide");
            System.out.println(extractor.getTags(object));
            System.out.println(extractor.getReviewCount(object));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
