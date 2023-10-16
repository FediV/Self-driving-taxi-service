package it.unimi.di.sdp.DataStucture;

import it.unimi.di.sdp.Threads.PersistentRides;

import java.util.ArrayList;
import java.util.List;

public class PersistentRidesQueue {
    private final List<PersistentRides> persistentQueues = new ArrayList<>();

    public synchronized void addThread(PersistentRides thread) {
        persistentQueues.add(thread);
    }

    public synchronized PersistentRides get(int index) {
        return persistentQueues.get(index);
    }

    public synchronized void start() {
        persistentQueues.forEach(Thread::start);
    }

    public synchronized void stop() {
        persistentQueues.forEach(PersistentRides::finish);
    }

}
