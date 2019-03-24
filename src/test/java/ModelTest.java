import TextModel.RunWord2Thread;

import java.util.ArrayList;

public class ModelTest {
    public static void main(String[] args){
        ArrayList<String> feature = new ArrayList();
        feature.add("sushi");
        feature.add("sashimi");
        ArrayList<String> resF = new ArrayList<>();
        resF.add("sushi");
        System.out.println(RunWord2Thread.runWord2Vec(feature,resF));
    }
}
