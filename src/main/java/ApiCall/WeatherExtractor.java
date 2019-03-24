package ApiCall;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.Response;

import java.io.IOException;

public class WeatherExtractor {
    private static final String API_KEY = "2f103c7ff481ee2b2f991b995c8c6e1d";
    private static final String URL = "http://api.openweathermap.org/data/2.5/weather";
    private HttpGetClient client;

    public WeatherExtractor(HttpGetClient client){
        this.client = client;
    }

    public Weather getWeather(String city) {
        //http://api.openweathermap.org/data/2.5/forecast?id=524901&APPID={APIKEY}
        Weather weather = null;
        try{
            Response response = client.sendHttpGetRequest(URL+"?q="+city+"&units=metric"+"&APPID="+API_KEY);

            JsonParser parser = new JsonParser();
            JsonObject object = parser.parse(response.body().string()).getAsJsonObject();

            String category = object.get("weather")
                    .getAsJsonArray()
                    .get(0)
                    .getAsJsonObject()
                    .get("main")
                    .getAsString();

            String temp = object.get("main")
                    .getAsJsonObject()
                    .get("temp")
                    .getAsString();

            weather =  new Weather(category,Double.parseDouble(temp));
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return weather;
    }

}
