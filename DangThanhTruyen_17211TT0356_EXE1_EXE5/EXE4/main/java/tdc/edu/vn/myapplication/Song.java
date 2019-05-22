package tdc.edu.vn.myapplication;

public class Song {
    private String tenBaiHat;
    private int hinhAnh;
    private int file;

    public Song(String tenBaiHat, int hinhAnh, int file) {
        this.tenBaiHat = tenBaiHat;
        this.hinhAnh = hinhAnh;
        this.file = file;
    }

    public String getTenBaiHat() {
        return tenBaiHat;
    }

    public void setTenBaiHat(String tenBaiHat) {
        this.tenBaiHat = tenBaiHat;
    }

    public int getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(int hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getFile() {
        return file;
    }

    public void setFile(int file) {
        this.file = file;
    }



}
