package it.unimi.di.sdp.DataStucture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseQueue {
    private final Object lock = new Object();
    private final Map<Object,List<String>> responses = new HashMap<>();

    public synchronized Object getLock() {
        return lock;
    }

    public synchronized void put(Object key) {
        responses.put(key, new ArrayList<>());
    }

    public synchronized void remove(Object key) {
        responses.remove(key);
    }

    public synchronized List<String> getResponses(Object key) {
        responses.computeIfAbsent(key, k -> new ArrayList<>());
        return new ArrayList<>(responses.get(key));
    }

    public synchronized boolean contains(Object key) {
        return responses.containsKey(key);
    }

    public synchronized void add(Object key, String response) {
        synchronized (lock) {
            responses.computeIfAbsent(key, k -> new ArrayList<>());
            responses.get(key).add(response);
            lock.notifyAll();
        }
    }

    public synchronized void clean(Integer district) {
        responses.get(district).clear();
    }
}
