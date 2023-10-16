package it.unimi.di.sdp.DataStucture;

import it.unimi.di.sdp.Helper.Position;
import it.unimi.di.sdp.Helper.Ride;

import java.util.ArrayList;

public class RidesQueue {
    private final ArrayList<Ride> buffer = new ArrayList<>();
    private final Object ridesLock = new Object();
    private boolean stopCondition = false;
    private boolean needToRecharge = false;

    public void add(Ride ride) {
        synchronized (ridesLock) {
            buffer.add(ride);
            ridesLock.notifyAll();
        }
    }

    public Ride take() {
        Ride ride;

        synchronized (ridesLock) {
            while (buffer.size() == 0 && !stopCondition && !needToRecharge) {
                try {
                    ridesLock.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (needToRecharge || stopCondition) {
                needToRecharge = false;
                return new Ride(0, new Position(), new Position());
            }

            ride = buffer.get(0);
            buffer.remove(0);
        }

        return ride;
    }

    public void remove(Ride ride) {
        synchronized (ridesLock) {
            buffer.remove(ride);
        }
    }

    public boolean contains(int id) {
        synchronized (ridesLock) {
            return buffer.stream().anyMatch(r->r.getId()==id);
        }
    }

    public void stop() {
        synchronized (ridesLock) {
            stopCondition = true;
            ridesLock.notifyAll();
        }
    }

    public void recharge() {
        synchronized (ridesLock) {
            needToRecharge = true;
            ridesLock.notifyAll();
        }
    }

}
