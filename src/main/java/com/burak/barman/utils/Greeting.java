package com.burak.barman.utils;

import java.util.Collection;
import java.util.Random;

import static com.burak.barman.utils.Tools.getDay;
import static com.burak.barman.utils.Tools.writeInListFromFile;

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

    // Choosing Greeting which depends on days
    public static void choosingGreeting(Collection<String> collection) {
        if ("Friday".equals(getDay())) {
            writeInListFromFile("./src/main/resources/com/burak/barman/files/greetingFriday.txt", collection);
        } else {
            writeInListFromFile("./src/main/resources/com/burak/barman/files/greeting.txt", collection);
        }
    }
}
