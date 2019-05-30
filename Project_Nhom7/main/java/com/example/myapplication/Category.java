package com.example.myapplication;

import java.io.Serializable;

public class Category implements Serializable {
    private int id;
    private String tenTheLoai;
    private int image;
    public Category() { super(); }

    public Category(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }



    public Category(int id, String tenTheLoai, int image) {
        this.id = id;
        this.tenTheLoai = tenTheLoai;
        this.image = image;
    }

    public Category(String tenTheLoai, int image) {
        this.tenTheLoai = tenTheLoai;
        this.image = image;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", tenTheLoai='" + tenTheLoai + '\'' +
                ", image=" + image +
                '}';
    }
}
