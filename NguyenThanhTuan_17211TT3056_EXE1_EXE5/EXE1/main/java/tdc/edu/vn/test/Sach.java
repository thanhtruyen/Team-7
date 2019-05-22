package tdc.edu.vn.test;

public class Sach {
    private String nhap1;
    private String nhap2;

    public Sach(String nhap1, String nhap2) {
        this.nhap1 = nhap1;
        this.nhap2 = nhap2;

    }

    public String getSoA() {
        return nhap1;
    }

    public void setNhap1(String nhap1) {
        this.nhap1 = nhap1;
    }

    public String getSoB() {
        return nhap2;
    }

    public void setNhap2(String nhap2) {
        this.nhap2 = nhap2;
    }

}
