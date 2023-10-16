package it.unimi.di.sdp.Threads;

import it.unimi.di.sdp.Simulator.Measurement;
import it.unimi.di.sdp.Simulator.PM10Simulator;
import it.unimi.di.sdp.Simulator.SensorBuffer;

import java.util.List;

public class PollutionSensor extends Thread {
    private final SensorBuffer sensorBuffer;
    private final List<Double> averages;
    private boolean stopCondition;
    private final Object lock;

    public PollutionSensor(SensorBuffer sensorBuffer, List<Double> averages, Object lock, boolean stopCondition) {
        this.sensorBuffer = sensorBuffer;
        this.stopCondition = stopCondition;
        this.averages = averages;
        this.lock = lock;
    }

    @Override
    public void run() {

        PM10Simulator simulator = new PM10Simulator(sensorBuffer);
        simulator.start();

        calculateAverage();

        simulator.stopMeGently();

    }

    private void calculateAverage() {
        List<Measurement> values;

        try {
            while (!stopCondition) {

                synchronized (sensorBuffer) {
                    sensorBuffer.wait();

                    values = sensorBuffer.readAllAndClean();

                    double sum = 0.0;
                    for (Measurement m : values) {
                        sum += m.getValue();
                    }
                    double mean = sum/8.0;

                    synchronized (lock) {
                        averages.add(mean);
                    }

                    sensorBuffer.notify();
                }

            }
        } catch (InterruptedException e) {
            System.out.println("\n" + e.getCause() + " : " + e.getMessage());
        }
    }

    public void stopSensor() {
        stopCondition = true;
    }
}
