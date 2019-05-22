package com.example.admin.myapplication;

public class Sach {
    private String tensach;
    private String loaisach;
    private String tacgia;

    public Sach() {
    }

    public Sach(String tensach, String loaisach, String tacgia) {
        this.tensach = tensach;
        this.loaisach = loaisach;
        this.tacgia = tacgia;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getLoaisach() {
        return loaisach;
    }

    public void setLoaisach(String loaisach) {
        this.loaisach = loaisach;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }
}
