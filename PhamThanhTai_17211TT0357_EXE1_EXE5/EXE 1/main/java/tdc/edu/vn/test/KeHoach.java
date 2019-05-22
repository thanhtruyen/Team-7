package tdc.edu.vn.test;

public class KeHoach {
    private String ngay;
    private String tieude;
    private String noidung;

    public KeHoach(String ngay, String tieude, String noidung) {
        this.ngay = ngay;
        this.tieude = tieude;
        this.noidung = noidung;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }
}
