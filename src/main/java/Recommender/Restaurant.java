package Recommender;

import com.google.maps.model.PriceLevel;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    // Basic Attributes
    private String name;
    private PriceLevel priceLevel;
    private String address;

    // Sorting Attributes
    private double rating;
    private int reviewCount;
    private long distance;
    private long walkingTime;
    private List<String> tags;
    private int previousVisitTimes=0;  //要判断
    private boolean isVisitYesterday=false; //她也要
    private double tagScore;
    //private List<Character> resFeature;//这个要怎么做？
    //private double resScore;//用于排序的score


    private int rankDistance;
    private int rankReviewCnt;
    private int rankVisits;
    private int rankRating;

    public void setTagScore(double tagScore) {
        this.tagScore = tagScore;
    }

    public void setRankRating(int rankRating) {
        this.rankRating = rankRating;
    }

    public void setRankDistance(int rankDistance) {
        this.rankDistance = rankDistance;
    }

    public void setRankReviewCnt(int rankReviewCnt) {
        this.rankReviewCnt = rankReviewCnt;
    }

    public void setRankVisits(int rankVisits) {
        this.rankVisits = rankVisits;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setPriceLevel(PriceLevel priceLevel) {
        this.priceLevel = priceLevel;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public void setTags(List<String> tags) {
        this.tags = new ArrayList<>(tags);
    }

    public void setWalkingTime(long walkingTime){ this.walkingTime = walkingTime;}

    public int getRankRating() {
        return rankRating;
    }

    public double getTagScore() {
        return tagScore;
    }

    public int getRankDistance() {
        return rankDistance;
    }

    public int getRankReviewCnt() {
        return rankReviewCnt;
    }

    public int getRankVisits() {
        return rankVisits;
    }

    public String getName() {
        return name;
    }

    public List<String> getTags() {
        return tags;
    }

    public Restaurant(double resCustomerScore, long distance, String name,
                      int reviewCount) {
        this.rating = resCustomerScore;
        this.distance = distance;
        this.name = name;
        //this.previousVisitTimes = previousVisitTimes;
        //this.isVisitYesterday = isVisitYesterday;
        //this.resFeature = resFeature;
        this.reviewCount = reviewCount;
        //this.resScore = isVisitYesterday==true?0:getCustomerCommentScore() + getResDistanceScore() + previousVisitTimes + getFeatureScore();
    }

    public Restaurant(){}


    //to be done
    private int getFeatureScore() {
        return 0;
    }

    /*
    private double getResDistanceScore(){
        double i1 = 0;
        double i2 = 0;
        if(today.isExceedEatingTime()==true)
            i1 = 0.5;
        if(today.isSpecial()==true)
            i2 = 0.5;
        return Math.pow(distance+i1*distance-i2*distance,2);
    }
    */

    private  double getCustomerCommentScore(){
        return Math.sqrt(reviewCount * rating);
    }

    public double getRating() {
        return rating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public double getDistance() {
        return distance;
    }

    public int getPreviousVisitTimes() {
        return previousVisitTimes;
    }

    public String toString(){
        return this.name +" distance "+this.distance +" score:"+Math.round(this.rating)+" price:"+this.priceLevel +" tag:"+tags+" Reviews:"+reviewCount;
    }
    //AIzaSyDBPaygtpQ3m-A-cXYIvqQ5Y6ob2P4gACU
}

