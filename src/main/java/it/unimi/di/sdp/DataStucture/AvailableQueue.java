package it.unimi.di.sdp.DataStucture;

import java.util.HashMap;
import java.util.Map;

public class AvailableQueue {

    private final Map<Integer, Integer> availableTaxi = new HashMap<Integer, Integer>() {{
        put(1,0);
        put(2,0);
        put(3,0);
        put(4,0);
    }};

    public synchronized void add(Integer district) {
        availableTaxi.put(district, availableTaxi.get(district) + 1);
    }

    public synchronized void remove(Integer district) {
        availableTaxi.put(district, availableTaxi.get(district) - 1);
    }

    public synchronized Integer numberOfTaxi(Integer district) {
        return availableTaxi.get(district);
    }
}
