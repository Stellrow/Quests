package utils;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class RandomCollections<E> {
    private final NavigableMap<Double, E> map = new TreeMap<Double, E>();
    private final Random random;
    private double total = 0;

    public RandomCollections() {
        this(new Random());
    }

    public RandomCollections(Random random) {
        this.random = random;
    }

    public RandomCollections<E> add(double weight, E result) {
        if (weight <= 0) return this;
        total += weight;
        map.put(total, result);
        return this;
    }

    public E next() {
        double value = random.nextDouble() * total;
        return map.higherEntry(value).getValue();
    }
    public void clear() {
    	map.clear();
    }
}
