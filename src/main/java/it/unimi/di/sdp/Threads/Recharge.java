package it.unimi.di.sdp.Threads;

import it.unimi.di.sdp.GRPC.FinishRechargingClient;
import it.unimi.di.sdp.GRPC.RechargeClient;
import it.unimi.di.sdp.Taxi;


import java.util.List;

public class Recharge extends Thread{
    private final Object rechargeLock ;
    private final Taxi taxi;
    private final int district;

    public Recharge(Taxi taxi) {
        this.taxi = taxi;
        district = taxi.getPosition().getDistrict();
        this.rechargeLock = taxi.getRechargeResponses().getLock();
    }

    @Override
    public void run() {

        try {

            List<Taxi> taxiList = taxi.getLocalSmartCity().getTaxiList();

            taxi.getClock().increment();
            int clock = taxi.getClock().getCounter();

            for (Taxi t : taxiList) {
                RechargeClient rechargeClient = new RechargeClient(t, taxi, clock);
                rechargeClient.start();
            }

            int numberOfTaxi = taxiList.size();

            while ( taxi.getRechargeResponses().getResponses(district).size() != (numberOfTaxi) ) {

                synchronized (rechargeLock) {
                    rechargeLock.wait();
                }

            }

            StartRecharging startRecharging = new StartRecharging(taxi);
            startRecharging.start();

            startRecharging.join();

            System.out.println("\n[Thread RECHARGE] taxi " + taxi.getId() + " finishes recharging at district " + district);

            for (int port : taxi.getRechargeQueue().get(district)) {
                FinishRechargingClient finishRechargingClient = new FinishRechargingClient(port, taxi);
                finishRechargingClient.start();
            }

            taxi.getRechargeQueue().clean(district);
            taxi.getRechargeResponses().clean(district);

        } catch (InterruptedException e) {
            System.out.println("\n" + e.getCause() + " : " + e.getMessage());
        }
    }
}
