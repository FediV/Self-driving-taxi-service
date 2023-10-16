package it.unimi.di.sdp.Helper;

public class Ride {
    private final int id;
    private final Position startPosition;
    private final Position finishPosition;

    public Ride(int id, Position startPosition, Position finishPosition) {
        this.id = id;
        this.startPosition = startPosition;
        this.finishPosition = finishPosition;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getFinishPosition() {
        return finishPosition;
    }

    public int getId() {
        return id;
    }

    public Ride cloneRide() {
        return new Ride(this.id,this.startPosition,this.finishPosition);
    }
}
