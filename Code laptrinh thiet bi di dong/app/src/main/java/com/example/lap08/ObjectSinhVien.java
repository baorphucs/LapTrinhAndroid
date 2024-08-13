package com.example.lap08;

public class ObjectSinhVien {
    private Integer id;
    private String maSV,tenSv;

    public String toString(){
        return id + "  -   " + maSV + "  -   " + tenSv;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getTenSv() {
        return tenSv;
    }

    public void setTenSv(String tenSv) {
        this.tenSv = tenSv;
    }

    public ObjectSinhVien(Integer id, String maSV, String tenSv) {
        this.id = id;
        this.maSV = maSV;
        this.tenSv = tenSv;
    }
}
