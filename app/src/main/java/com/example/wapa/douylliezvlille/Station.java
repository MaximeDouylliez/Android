/**
 * Created by wapa on 28/09/14.
 */
package com.example.wapa.douylliezvlille;
public class Station {

    private String name;
    private int id;
    private double lng;
    private double lat;
    private int nbBike;
    private int nbAttach;




    public Station(){}
    public Station(String name, int id, double lng, double lat){this.name=name; this.id=id; this.lat=lat; this.lng=lng;}
    public Station(int nbBike,int nbAttach){this.nbBike=nbBike; this.nbAttach=nbAttach;}
    public Station(String name, int id, double lng, double lat,int nbBike,int nbAttach){this.name=name; this.id=id; this.lat=lat; this.lng=lng;this.nbBike=nbBike; this.nbAttach=nbAttach;}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getNbBike() {
        return nbBike;
    }

    public void setNbBike(int nbBike) {
        this.nbBike = nbBike;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public int getNbAttach() {
        return nbAttach;
    }

    public void setNbAttach(int nbAttach) {
        this.nbAttach = nbAttach;
    }


}
