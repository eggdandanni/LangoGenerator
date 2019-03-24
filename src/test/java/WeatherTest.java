import ApiCall.HttpGetClient;
import ApiCall.Weather;
import ApiCall.WeatherExtractor;

public class WeatherTest {
    public static void main(String[] args){
        WeatherExtractor extractor = new WeatherExtractor(new HttpGetClient());
        try{
            Weather w = extractor.getWeather("Adelaide");
            System.out.println(w.isGoodDay());
            System.out.println(w);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
