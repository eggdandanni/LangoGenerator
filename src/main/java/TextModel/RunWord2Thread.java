package TextModel;

import org.deeplearning4j.models.word2vec.Word2Vec;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class RunWord2Thread {

    public static double runWord2Vec(List<String> customer,List<String> restaurant) {
        double res = 0;
        Word2Vec model = Word2vec.getWordModel();
        try{
            ExecutorService pool = Executors.newFixedThreadPool(1);
            for(int i =0;i<customer.size();i++) {
                for (int j = 0; j < restaurant.size(); j++) {
                    FutureTask m = new FutureTask<Double>(new Word2VecThread(model, customer.get(i), restaurant.get(j)));

                    pool.execute(m);
                    Double l = Double.parseDouble(m.get().toString());
                    res = Math.max(res,l);
                    //System.out.println(l);
                }
            }
            pool.shutdown();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return res;

    }
}
