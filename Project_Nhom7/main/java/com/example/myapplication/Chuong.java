package com.example.myapplication;

import java.io.Serializable;
import java.util.ArrayList;

public class Chuong implements Serializable {
    private int id;
    private int comicId;
    private String tenChuong;
    private ArrayList<Integer> noiDungHinhAnh;

    public Chuong(int id, int comicId, String tenChuong, ArrayList<Integer> noiDungHinhAnh) {
        this.id = id;
        this.comicId = comicId;
        this.tenChuong = tenChuong;
        this.noiDungHinhAnh = noiDungHinhAnh;
    }

    public Chuong(int comicId, String tenChuong, ArrayList<Integer> noiDungHinhAnh) {
        this.comicId = comicId;
        this.tenChuong = tenChuong;
        this.noiDungHinhAnh = noiDungHinhAnh;
    }

    public Chuong() {
    }

    public Chuong(String tenChuong, ArrayList<Integer> noiDungHinhAnh) {
        this.tenChuong = tenChuong;
        this.noiDungHinhAnh = noiDungHinhAnh;
    }

    public int getComicId() {
        return comicId;
    }

    public void setComicId(int comicId) {
        this.comicId = comicId;
    }

    public String getTenChuong() {
        return tenChuong;
    }

    public void setTenChuong(String tenChuong) {
        this.tenChuong = tenChuong;
    }

    public ArrayList<Integer> getNoiDungHinhAnh() {
        return noiDungHinhAnh;
    }

    public void setNoiDungHinhAnh(ArrayList<Integer> noiDungHinhAnh) {
        this.noiDungHinhAnh = noiDungHinhAnh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
