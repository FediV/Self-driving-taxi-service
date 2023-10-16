package it.unimi.di.sdp.DataStucture;

public class Counter {
    private volatile int counter = 0;

    public synchronized void increment() {
        this.counter ++;
    }

    public synchronized int getCounter() {
        return counter;
    }

    public synchronized void setCounter(int value) {
        counter = value;
    }
}
