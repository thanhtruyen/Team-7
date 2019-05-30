package com.example.myapplication;

import java.io.Serializable;
import java.util.ArrayList;

public class Comic implements Serializable {
    private int id;
    private String tenTruyen;
    private int biaTruyen;
    private ArrayList<Chuong> arrChuong;
    private String tomTat;

    public Comic() {
    }

    public Comic(String tenTruyen, int biaTruyen, ArrayList<Chuong> arrChuong, String tomTat) {
        this.tenTruyen = tenTruyen;
        this.biaTruyen = biaTruyen;
        this.arrChuong = arrChuong;
        this.tomTat = tomTat;
    }

    public Comic(String tenTruyen, int biaTruyen, String tomTat) {
        this.tenTruyen = tenTruyen;
        this.biaTruyen = biaTruyen;
        this.tomTat = tomTat;
    }

    public Comic(int id, String tenTruyen, int biaTruyen, String tomTat) {
        this.id = id;
        this.tenTruyen = tenTruyen;
        this.biaTruyen = biaTruyen;
        this.tomTat = tomTat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public int getBiaTruyen() {
        return biaTruyen;
    }

    public void setBiaTruyen(int biaTruyen) {
        this.biaTruyen = biaTruyen;
    }

    public ArrayList<Chuong> getArrChuong() {
        return arrChuong;
    }

    public void setArrChuong(ArrayList<Chuong> arrChuong) {
        this.arrChuong = arrChuong;
    }

    public String getTomTat() {
        return tomTat;
    }

    public void setTomTat(String tomTat) {
        this.tomTat = tomTat;
    }
}
