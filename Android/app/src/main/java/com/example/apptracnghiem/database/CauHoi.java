package com.example.apptracnghiem.database;

import java.io.Serializable;

public class CauHoi implements Serializable {
    private int id;
    private String cauHoi;
    private String dapan_a;
    private String dapan_b;
    private String dapan_c;
    private String dapan_d;
    private String ketqua;
    private int madethi;
    private int cauLiet;
    private String cauTraLoi;

    //Check id của radio group
    public int choiceIdRadio = -1;

    public CauHoi() {
    }

    public CauHoi(int id, String cauHoi, String dapan_a, String dapan_b, String dapan_c, String dapan_d, String ketqua, int madethi, int cauLiet, String cauTraLoi) {
        this.id = id;
        this.cauHoi = cauHoi;
        this.dapan_a = dapan_a;
        this.dapan_b = dapan_b;
        this.dapan_c = dapan_c;
        this.dapan_d = dapan_d;
        this.ketqua = ketqua;
        this.madethi = madethi;
        this.cauLiet = cauLiet;
        this.cauTraLoi = cauTraLoi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCauHoi() {
        return cauHoi;
    }

    public void setCauHoi(String cauHoi) {
        this.cauHoi = cauHoi;
    }

    public String getDapan_a() {
        return dapan_a;
    }

    public void setDapan_a(String dapan_a) {
        this.dapan_a = dapan_a;
    }

    public String getDapan_b() {
        return dapan_b;
    }

    public void setDapan_b(String dapan_b) {
        this.dapan_b = dapan_b;
    }

    public String getDapan_c() {
        return dapan_c;
    }

    public void setDapan_c(String dapan_c) {
        this.dapan_c = dapan_c;
    }

    public String getDapan_d() {
        return dapan_d;
    }

    public void setDapan_d(String dapan_d) {
        this.dapan_d = dapan_d;
    }

    public String getKetqua() {
        return ketqua;
    }

    public void setKetqua(String ketqua) {
        this.ketqua = ketqua;
    }

    public int getMadethi() {
        return madethi;
    }

    public void setMadethi(int madethi) {
        this.madethi = madethi;
    }

    public int getCauLiet() {
        return cauLiet;
    }

    public void setCauLiet(int cauLiet) {
        this.cauLiet = cauLiet;
    }

    public String getCauTraLoi() {
        return cauTraLoi;
    }

    public void setCauTraLoi(String cauTraLoi) {
        this.cauTraLoi = cauTraLoi;
    }

}
