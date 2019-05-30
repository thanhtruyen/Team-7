package com.example.myapplication;

public class History {
    private int comicId;
    private int chapId;
    private int positionImg;

    public History() {
    }

    public History(int comicId, int chapId, int positionImg) {
        this.comicId = comicId;
        this.chapId = chapId;
        this.positionImg = positionImg;
    }

    public int getComicId() {
        return comicId;
    }

    public void setComicId(int comicId) {
        this.comicId = comicId;
    }

    public int getChapId() {
        return chapId;
    }

    public void setChapId(int chapId) {
        this.chapId = chapId;
    }

    public int getPositionImg() {
        return positionImg;
    }

    public void setPositionImg(int positionImg) {
        this.positionImg = positionImg;
    }
}
