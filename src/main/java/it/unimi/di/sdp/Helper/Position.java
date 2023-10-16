package it.unimi.di.sdp.Helper;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Position {
    private int x;
    private int y;
    private int district;

    public Position() {}

    public Position(int district) {
        this.district = district;
        setCoordinates(this.district);
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        this.district = findDistrict(x,y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDistrict(int district) {
        this.district = district;
    }

    public int getDistrict() {
        return district;
    }

    private static int findDistrict(int x, int y) {
        if(x >= 0 && x <= 4) {
            if(y >= 0 && y <= 4) return 1;
            else return 2;
        }
        else {
            if(y >= 0 && y <= 4) return 4;
            else return 3;
        }
    }

    private void setCoordinates(int district) {
        switch (district) {
            case 1 :
                x = 0;
                y = 0;
                break;
            case 2 :
                x = 9;
                y = 0;
                break;
            case 3 :
                x = 9;
                y = 9;
                break;
            case 4 :
                x = 0;
                y = 9;
                break;
            default:
                System.out.println("Invalid district number");
                break;
        }
    }
}
