package TextModel;

import org.deeplearning4j.models.word2vec.Word2Vec;

import java.util.concurrent.Callable;

public class Word2VecThread implements Callable<Double> {
    private Word2Vec model;
    private String customer;
    private String rest;

    public Word2VecThread(Word2Vec model, String customer, String rest) {
        this.model = model;
        this.customer = customer;
        this.rest = rest;
    }

    public Double call() throws Exception {
        double cosSim = model.similarity(customer.toLowerCase(), rest.toLowerCase());
        //System.out.println(customer + "  " + rest + " = " + cosSim);
        return cosSim;
    }
}
