package com.burak.barman.utils;

import java.util.Collection;
import java.util.Random;

/**
 * Barman
 * Created by Alexey Burak
 */

public class Greeting {

    // Random point from collection
    public static String getRandom(Collection<String> collection) {
        Random rand = new Random();
        int i = rand.nextInt(collection.size());
        return (String) collection.toArray()[i];
    }
}
