package it.unimi.di.sdp;

import it.unimi.di.sdp.DataStucture.Counter;
import it.unimi.di.sdp.DataStucture.RechargeQueue;
import it.unimi.di.sdp.DataStucture.RidesQueue;
import it.unimi.di.sdp.Helper.Position;
import it.unimi.di.sdp.DataStucture.ResponseQueue;
import it.unimi.di.sdp.Helper.Ride;
import it.unimi.di.sdp.Simulator.SensorBuffer;
import it.unimi.di.sdp.Threads.*;

import javax.xml.bind.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Taxi {
    private transient SmartCity localSmartCity;

    private int id;
    private int portNumber;
    private String serverAddress ;

    private Position position;

    private int batteryLevel;
    private int rides;
    private double kilometers;

    private volatile boolean available = true;
    private transient ResponseQueue ridesResponses ;
    private transient RidesQueue rideQueue ;
    private transient List<Ride> ridesServed ;
    private transient SensorBuffer buffer;

    private Counter clock ;

    private transient ResponseQueue rechargeResponses ;
    private transient RechargeQueue rechargeQueue;
    private boolean needToRecharge = false;
    private boolean isRecharging = false;

    private volatile boolean stopCondition = false;

    public Taxi() {}

    public Taxi (int id, int portNumber) {
        this.id = id;
        this.portNumber = portNumber;
        this.serverAddress = "localhost";
        this.batteryLevel = 100;
        this.ridesResponses = new ResponseQueue();
        this.rideQueue = new RidesQueue();
        this.ridesServed = new ArrayList<>();
        this.buffer = new SensorBuffer();
        this.rechargeQueue = new RechargeQueue();
        this.rechargeResponses = new ResponseQueue();
        this.clock = new Counter();
        this.localSmartCity = new SmartCity();
    }

    public SmartCity getLocalSmartCity() {
        return localSmartCity;
    }

    public int getId() {
        return id;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public int getRides() {
        return rides;
    }

    public void setRides(int rides) {
        this.rides = rides;
    }

    public double getKilometers() {
        return kilometers;
    }

    public void setKilometers(double kilometers) {
        this.kilometers = kilometers;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public ResponseQueue getRidesResponses() {
        return ridesResponses;
    }

    public RidesQueue getRideQueue() {
        return rideQueue;
    }

    public synchronized List<Ride> getRidesServed() {
        return ridesServed;
    }

    public synchronized void addRideServed(Ride ride) {
        ridesServed.add(ride);
    }

    public SensorBuffer getBuffer() {
        return buffer;
    }

    public Counter getClock() {
        return clock;
    }

    public ResponseQueue getRechargeResponses() {
        return rechargeResponses;
    }

    public RechargeQueue getRechargeQueue() {
        return rechargeQueue;
    }

    public boolean getNeedToRecharge() {
        return needToRecharge;
    }

    public void setNeedToRecharge(boolean needToRecharge) {
        this.needToRecharge = needToRecharge;
    }

    public boolean isRecharging() {
        return isRecharging;
    }

    public void setRecharging(boolean recharging) {
        isRecharging = recharging;
    }

    public boolean getStopCondition() {
        return stopCondition;
    }

    public void setStopCondition(boolean stopCondition) {
        this.stopCondition = stopCondition;
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter taxi id:");
            int id = Integer.parseInt(br.readLine());

            System.out.println("Enter taxi port number:");
            int portNumber = Integer.parseInt(br.readLine());

            Taxi taxi = new Taxi(id, portNumber);

            CommunicationWithServer cws = new CommunicationWithServer(taxi);
            cws.start();

            cws.join();

            if(taxi.getPosition()!=null) {
                GRPCServer grpcServer = new GRPCServer(taxi);
                grpcServer.start();

                grpcServer.onConnect();

                if(!taxi.getStopCondition()) {

                    AvailabilitySETA availabilitySETA = new AvailabilitySETA(true,taxi.getPosition().getDistrict());
                    availabilitySETA.start();

                    StdInput stdInput = new StdInput(taxi);
                    stdInput.start();

                    RideMonitor rm = new RideMonitor(taxi);
                    rm.start();

                    SendStatistics sendStatistics = new SendStatistics(taxi);
                    sendStatistics.start();
                }
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("\n"+e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("\nTaxi id or port aren't valid Integer.");
        }

    }

    @Override
    public String toString() {
        return "Taxi { " +
                "id = " + id +
                " , portNumber = " + portNumber + " }" ;
    }
}
