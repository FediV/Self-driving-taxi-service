package it.unimi.di.sdp;

import it.unimi.di.sdp.Helper.Position;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SmartCity {

    @XmlElement(name="Taxi")
    private List<Taxi> taxiList;

    private static SmartCity instance;

    SmartCity() {
        taxiList = new ArrayList<>();
    }

    public static SmartCity getInstance(){
        if(instance==null)
            instance = new SmartCity();
        return instance;
    }

    public synchronized List<Taxi> getTaxiList() {
        return new ArrayList<>(taxiList);
    }

    public synchronized void setTaxiList(List<Taxi> Taxislist) {
        this.taxiList = Taxislist;
    }

    private Position getRandomDistrict() {
        int district = (int) (1 + Math.random() * 4);
        return new Position(district);
    }

    public synchronized Position add(Taxi t){
        int id = t.getId();
        for(Taxi taxi : taxiList) {
            if (taxi.getId() == id) {
                throw new IllegalArgumentException("Taxi id is already present");
            }
        }
        Position district = getRandomDistrict();
        t.setPosition(district);
        taxiList.add(t);

        return district;
    }

    public synchronized void delete(int id){
        taxiList.removeIf(taxi -> taxi.getId() == id);
    }

}