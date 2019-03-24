package Recommender;

import TextModel.RunWord2Thread;
import TextModel.Word2VecThread;

import java.lang.reflect.Array;
import java.util.*;

public class LevelSorter {

    //1: review + count: Bayesian Estimate
    //  Top N
    //2: distance
    //  Top 20
    //3: Visits
    //  Top 10
    //4: Tags
    //  Top 5

    private static final int LAYER2_NUM = 20;
    private static final int LAYER3_NUM = 10;
    private static final int LAYER4_NUM = 5;
    private static final double GLOBAL_AVG_RATING = 3.7;
    private static final double GLOBAL_REV_CNT = 10;

    private void sortByAdjRating(List<Restaurant> restaurantList){
        Collections.sort(restaurantList, new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant r1, Restaurant r2) {
                return (calcBaysianEstimate(r1)<calcBaysianEstimate(r2))?1:-1;
            }
        });
    }

    private double calcBaysianEstimate(Restaurant r){
        return (r.getRating()*r.getReviewCount()+GLOBAL_AVG_RATING*GLOBAL_REV_CNT)/(r.getReviewCount()+GLOBAL_REV_CNT);
    }

    private List<Restaurant> sortByDistance(List<Restaurant> restaurantList){
        List<Restaurant> top = restaurantList.subList(0,LAYER2_NUM);
        List<Restaurant> rest = restaurantList.subList(LAYER2_NUM,restaurantList.size());

        Collections.sort(top, new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant r1, Restaurant r2) {
                return (r1.getDistance()>r2.getDistance())?1:-1;
            }
        });
        top.addAll(rest);
        return top;
    }

    private List<Restaurant> sortByVisits(List<Restaurant> restaurantList){
        List<Restaurant> top = restaurantList.subList(0,LAYER3_NUM);
        List<Restaurant> rest = restaurantList.subList(LAYER3_NUM,restaurantList.size());
        Collections.sort(top, new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant r1, Restaurant r2) {
                return (r1.getPreviousVisitTimes()<r2.getPreviousVisitTimes())?1:-1;
            }
        });
        top.addAll(rest);
        return top;
    }

    private List<Restaurant> sortByTags(List<Restaurant> restaurantList,User user) {
        List<Restaurant> top = restaurantList.subList(0,LAYER4_NUM);
        List<Restaurant> rest = restaurantList.subList(LAYER4_NUM,restaurantList.size());

        Collections.sort(top, new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant r1, Restaurant r2) {
                return r1.getTagScore()>r2.getTagScore()?-1:1;
            }
        });
        top.addAll(rest);

        return top;
    }

    public List<Restaurant> sortByFeatures(List<Restaurant> restaurantList,User user){
        sortByAdjRating(restaurantList);
        List<Restaurant> list = sortByDistance(restaurantList);
        list = sortByVisits(list);
        restaurantList = sortByTags(restaurantList,user);
        return restaurantList;
    }
}
