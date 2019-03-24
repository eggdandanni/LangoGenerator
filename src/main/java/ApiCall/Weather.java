package ApiCall;

import java.util.Arrays;
import java.util.HashSet;

public class Weather {
    private String category;
    private double temperature;
    private final static HashSet<String> goodWeathers = new HashSet<>(Arrays.asList("Clouds","Clear"));

    public Weather(String category,double temperature){
        this.category =  category;
        this.temperature = temperature;
    }

    public boolean isGoodDay(){
        if(goodWeathers.contains(category) && temperature>=18 && temperature <=28) return true;
        else return false;
    }

    @Override
    public String toString() {
        return category + " " + temperature;
    }
}
