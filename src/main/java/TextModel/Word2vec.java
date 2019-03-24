package TextModel;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;

public class Word2vec {
    private static final Word2Vec model = WordVectorSerializer.readWord2VecModel("pathToSaveModel.txt");
    private static final Word2vec cons = new Word2vec();

    private Word2vec(){}

    public static Word2Vec getWordModel(){
        return model;
    }

}
