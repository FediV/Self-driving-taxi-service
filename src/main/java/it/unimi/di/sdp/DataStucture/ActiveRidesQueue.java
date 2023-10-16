package it.unimi.di.sdp.DataStucture;

import it.unimi.di.sdp.Helper.Ride;

import java.util.ArrayList;
import java.util.List;

public class ActiveRidesQueue {
    private List<Ride> activeRides = new ArrayList<>();

    public ActiveRidesQueue() {
    }

    public ActiveRidesQueue(List<Ride> activeRides) {
        this.activeRides = activeRides;
    }

    public synchronized void add(Ride ride) {
        activeRides.add(ride);
    }

    public synchronized void remove(int rideId) {
        activeRides.removeIf(r->r.getId()==rideId);
    }

    public synchronized List<Ride> getRides() {
        ArrayList<Ride> rides = new ArrayList<>();
        for (Ride r : activeRides) {
            rides.add(r.cloneRide());
        }
        return rides;
    }
}
