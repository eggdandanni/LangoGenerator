package Recommender;

import java.util.List;

public class User {
    private String name;
    private List<String> features;
    private double weight;

    public User(String name, List<String> features, double weight) {
        this.name = name;
        this.features = features;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public List<String> getFeatures() {
        return features;
    }

    public double getWeight() {
        return weight;
    }
}
