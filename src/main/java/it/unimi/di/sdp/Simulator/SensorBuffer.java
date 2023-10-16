package it.unimi.di.sdp.Simulator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SensorBuffer implements Buffer {
    private final int windowsSize = 8;
    private final double overlap = 0.5;

    private final LinkedList<Measurement> buffer = new LinkedList<>();

    @Override
    public synchronized void addMeasurement(Measurement m) {

        buffer.add(m);

        if(buffer.size() == windowsSize) {
            notify();

            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("\n" + e.getCause() + " : " + e.getMessage());
            }
        }

    }

    @Override
    public synchronized List<Measurement> readAllAndClean() {
        ArrayList<Measurement> l = new ArrayList<>(buffer);

        for (int i = 0; i < windowsSize * overlap; i++) {
            buffer.removeFirst();
        }

        return l;
    }


    
}
