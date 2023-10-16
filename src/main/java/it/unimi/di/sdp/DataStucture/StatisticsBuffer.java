package it.unimi.di.sdp.DataStucture;

import it.unimi.di.sdp.Helper.Statistic;

import java.util.*;
import java.util.stream.Collectors;

public class StatisticsBuffer {
    private final Map<Integer, List<Statistic>> statistics = new HashMap<>();
    private final Object lock = new Object();

    public void addStatistics(Statistic s, int idTaxi) {
        synchronized (lock) {
            statistics.computeIfAbsent(idTaxi,  k -> new ArrayList<>());
            List<Statistic> taxiStatistics = statistics.get(idTaxi);
            taxiStatistics.add(s);
            statistics.put(idTaxi, taxiStatistics );
        }
    }

    public Statistic computeTaxiStatistics(int id, int n) {
        double pollutionLevel, kilometers;
        int rides, batteryLevel;

        List<Double> averages = new ArrayList<>();
        List<Double> allRides = new ArrayList<>();
        List<Double> allBatteryLevel = new ArrayList<>();
        List<Double> allKilometers = new ArrayList<>();

        synchronized (lock) {
            List<Statistic> stats = statistics.get(id);

            if (stats.size()==0) return new Statistic(0.0,0,0.0,id,0);

            for ( int i = stats.size() - 1; i>=0 && i >= stats.size() - n ; i-- ) {
                averages.add(computeAverage(stats.get(i).getAveragesList()));
                allRides.add((double) stats.get(i).getNumberOfRides());
                allBatteryLevel.add((double) stats.get(i).getBatteryLevel());
                allKilometers.add(stats.get(i).getKilometers());
            }
        }

        pollutionLevel = computeAverage(averages);
        rides = (int) computeAverage(allRides);
        batteryLevel = (int) computeAverage(allBatteryLevel);
        kilometers = computeAverage(allKilometers);

        return new Statistic(kilometers,rides,pollutionLevel,id,batteryLevel);
    }

    public Statistic computeAllTaxiStatistics(double t1, double t2) {
        double pollutionLevel, kilometers;
        int rides, batteryLevel;

        List<Double> averages = new ArrayList<>();
        List<Double> allRides = new ArrayList<>();
        List<Double> allBatteryLevel = new ArrayList<>();
        List<Double> allKilometers = new ArrayList<>();

        synchronized (lock) {
            for(Map.Entry<Integer, List<Statistic>> stats : statistics.entrySet()) {
                List<Statistic> listComplete = stats.getValue();
                List<Statistic> listPartial = listComplete.subList(findStatisticsWithTSGreaterThan(listComplete,t1),findStatisticsWithTSLowerThan(listComplete,t2) + 1);

                for (Statistic statistic : listPartial) {
                    averages.add(computeAverage(statistic.getAveragesList()));
                    allRides.add((double) statistic.getNumberOfRides());
                    allBatteryLevel.add((double) statistic.getBatteryLevel());
                    allKilometers.add(statistic.getKilometers());
                }
            }
        }

        pollutionLevel = computeAverage(averages);
        rides = (int) computeAverage(allRides);
        batteryLevel = (int) computeAverage(allBatteryLevel);
        kilometers = computeAverage(allKilometers);

        return new Statistic(kilometers,rides,pollutionLevel, batteryLevel);
    }

    private double computeAverage(List<Double> values) {
        double sum = 0;
        for (double d : values) {
            sum += d;
        }
        return sum / values.size();
    }

    private int findStatisticsWithTSGreaterThan(List<Statistic> statistics, Double timestamp) {
        List<Statistic> stats = statistics.stream().filter( s -> s.getTimestamp() >= timestamp ).collect(Collectors.toList());
        return stats.size()>0 ? statistics.indexOf(stats.get(0)) : statistics.indexOf(statistics.size()-1);
    }

    private int findStatisticsWithTSLowerThan(List<Statistic> statistics, Double timestamp) {
        List<Statistic> stats = statistics.stream().filter( s -> s.getTimestamp() <= timestamp ).collect(Collectors.toList());
        return stats.size()>0 ? statistics.indexOf(stats.get(stats.size()-1)) : statistics.indexOf(statistics.size()-1);
    }
}
