package Recommender;

import java.util.Collections;
import java.util.List;

public class RankSumSorter {
    private static final RankSumSorter F = new RankSumSorter();

    private RankSumSorter(){

    }

    private static List<Restaurant> sortResCommentNum(List<Restaurant> R){
        Collections.sort(R,(Restaurant a, Restaurant b)->a.getReviewCount()<b.getReviewCount()?1:-1);
        for(int i=0;i<R.size();i++){
            R.get(i).setRankReviewCnt(i);
        }
        return R;
    }

    private static List<Restaurant> sortDistance(List<Restaurant> R){
        Collections.sort(R,(Restaurant a, Restaurant b)->a.getDistance()>b.getDistance()?1:-1);
        for(int i=0;i<R.size();i++){

            R.get(i).setRankDistance(i);
        }
        return R;
    }

    private static List<Restaurant> sortScore(List<Restaurant> R){
        Collections.sort(R,(Restaurant a, Restaurant b)->a.getRating()<b.getRating()?1:-1);
        for(int i=0;i<R.size();i++){
            R.get(i).setRankRating(i);
        }
        return R;
    }

    private static List<Restaurant> sortVisitedTimes(List<Restaurant> R){
        Collections.sort(R,(Restaurant a, Restaurant b)->a.getPreviousVisitTimes()<b.getPreviousVisitTimes()?1:-1);
        for(int i=0;i<R.size();i++){
            R.get(i).setRankVisits(i);
        }
        return R;
    }

    public static List<Restaurant> sortOverallScore(List<Restaurant> R){
        sortResCommentNum(R);
        sortDistance(R);
        sortScore(R);
        sortVisitedTimes(R);
        Collections.sort(R,(Restaurant a, Restaurant b)->a.getRankDistance()+a.getRankReviewCnt()+a.getRankVisits()
                +a.getRankRating()>b.getRankDistance()+b.getRankReviewCnt()+b.getRankVisits()+b.getRankRating()?1:-1);
        return R;
    }

}

