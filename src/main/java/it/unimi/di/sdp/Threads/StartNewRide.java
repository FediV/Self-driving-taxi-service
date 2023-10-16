package it.unimi.di.sdp.Threads;

import it.unimi.di.sdp.Helper.Position;
import it.unimi.di.sdp.Helper.Ride;
import it.unimi.di.sdp.Taxi;

public class StartNewRide extends Thread{
    private final Ride ride;
    private final Taxi taxi;

    public StartNewRide(Ride ride, Taxi taxi) {
        this.ride = ride;
        this.taxi = taxi;
    }

    @Override
    public void run() {

        try {

            System.out.println("\n[Thread START RIDE] taxi is serving ride "+ride.getId());

            Thread.sleep(5000);

            int km = (int) computeDistance(ride,taxi);

            Position finish = ride.getFinishPosition();
            taxi.setPosition(finish);
            taxi.setKilometers(taxi.getKilometers() + km);
            taxi.setBatteryLevel(taxi.getBatteryLevel() - km);

            taxi.getRidesResponses().remove(ride.getId());
            taxi.setAvailable(true);

        } catch (InterruptedException e) {
            System.out.println("\n" + e.getCause() + " : " + e.getMessage());
        }

    }

    private double computeDistance(Ride ride,Taxi t) {
        double result;
        result = Math.sqrt(Math.pow(ride.getStartPosition().getX()-t.getPosition().getX(),2) + Math.pow(ride.getStartPosition().getY()-t.getPosition().getY(),2));

        return result;
    }
}
