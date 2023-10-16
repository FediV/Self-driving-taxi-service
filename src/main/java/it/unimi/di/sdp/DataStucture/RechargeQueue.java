package it.unimi.di.sdp.DataStucture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RechargeQueue {
    private final Map<Integer, List<Integer>> rechargeQueue = new HashMap<>();

    public synchronized List<Integer> get(Integer key) {
        rechargeQueue.computeIfAbsent(key, k -> new ArrayList<>());
        return new ArrayList<>(rechargeQueue.get(key));
    }

    public synchronized void put(Integer key, List<Integer> taxiList) {
        rechargeQueue.put(key, taxiList);
    }

    public synchronized void clean(Integer key) {
        rechargeQueue.get(key).clear();
    }


}
