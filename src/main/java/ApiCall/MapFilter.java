package ApiCall;

import Recommender.Today;

public class MapFilter {
    private Today today;
    private Weather weather;

    public MapFilter(Today today, Weather weather) {
        this.today = today;
        this.weather = weather;
    }

    private int specialDayDelta(){
        if(today.isSpecial()) return 300;
        else return 0;
    }

    private int weatherDelta(){
        if(!weather.isGoodDay()) return -400;
        else return 0;
    }

    public int getRadiusDelta(){
        return specialDayDelta() + weatherDelta();
    }
}
