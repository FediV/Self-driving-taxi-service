package it.unimi.di.sdp.Threads;

import it.unimi.di.sdp.GRPC.RechargeService;
import it.unimi.di.sdp.Helper.Position;
import it.unimi.di.sdp.Taxi;

public class StartRecharging extends Thread {
    private final Taxi taxi;
    private final int district;

    public StartRecharging(Taxi taxi) {
        this.taxi = taxi;
        this.district = taxi.getPosition().getDistrict();
    }

    @Override
    public void run() {

        try {

            taxi.setAvailable(false);
            taxi.setRecharging(true);

            System.out.println("\n[Thread START RECHARGING] taxi is recharging at gas station of district "+district);

            Thread.sleep(10000);

            Position finish = new Position(district);
            taxi.setPosition(finish);

            taxi.setBatteryLevel(100);
            taxi.setNeedToRecharge(false);
            taxi.setRecharging(false);
            taxi.setAvailable(true);

            RechargeService.timestamp = Double.MAX_VALUE;

        } catch (InterruptedException e) {
            System.out.println("\n" + e.getCause() + " : " + e.getMessage());
        }

    }

}
