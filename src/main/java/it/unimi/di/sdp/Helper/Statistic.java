package it.unimi.di.sdp.Helper;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Statistic {
    private double kilometers;
    private int numberOfRides;
    private List<Double> averagesList = new ArrayList<>();
    private int idTaxi;
    private double timestamp;
    private int batteryLevel;

    private double pollutionAverage;

    public Statistic() {}

    public Statistic(double kilometers, int numberOfRides, List<Double> averages, int idTaxi, double timestamp, int batteryLevel) {
        this.kilometers = kilometers;
        this.numberOfRides = numberOfRides;
        this.averagesList = averages;
        this.idTaxi = idTaxi;
        this.timestamp = timestamp;
        this.batteryLevel = batteryLevel;
    }

    public Statistic(double kilometers, int numberOfRides, double pollutionAverage, int idTaxi, int batteryLevel) {
        this.kilometers = kilometers;
        this.numberOfRides = numberOfRides;
        this.pollutionAverage = pollutionAverage;
        this.idTaxi = idTaxi;
        this.batteryLevel = batteryLevel;
    }

    public Statistic(double kilometers, int numberOfRides, double pollutionAverage, int batteryLevel) {
        this.kilometers = kilometers;
        this.numberOfRides = numberOfRides;
        this.pollutionAverage = pollutionAverage;
        this.batteryLevel = batteryLevel;
    }

    public Double getKilometers() {
        return kilometers;
    }

    public void setKilometers(Double kilometers) {
        this.kilometers = kilometers;
    }

    public int getNumberOfRides() {
        return numberOfRides;
    }

    public void setNumberOfRides(int numberOfRides) {
        this.numberOfRides = numberOfRides;
    }

    public List<Double> getAveragesList() {
        return new ArrayList<>(averagesList);
    }

    public void setAveragesList(List<Double> averagesList) {
        this.averagesList = averagesList;
    }

    public int getIdTaxi() {
        return idTaxi;
    }

    public void setIdTaxi(int idTaxi) {
        this.idTaxi = idTaxi;
    }

    public double getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(double timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(Integer batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public double getPollutionAverage() {
        return pollutionAverage;
    }

    public void setPollutionAverage(double pollutionAverage) {
        this.pollutionAverage = pollutionAverage;
    }

    public String printStatisticSingleTaxi() {
        return "Statistics of taxi "+idTaxi+" { " +
                "kilometers = " + String.format("%.2f",kilometers) +
                " , number of rides = " + numberOfRides +
                " , average pollution level = " + String.format("%.2f",pollutionAverage) +
                " , battery level = " + batteryLevel +
                " }";
    }

    public String printStatistics() {
        return "Average statistics { " +
                "kilometers = " + String.format("%.2f",kilometers) +
                " , number of rides = " + numberOfRides +
                " , average pollution level = " + String.format("%.2f",pollutionAverage) +
                " , battery level = " + batteryLevel +
                " }";
    }
}
