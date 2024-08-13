package com.example.lab7;

public class Employee {
    private String maNV,tenNV;
    private boolean gioiTinh;
    public String toString() { return maNV + " - " + tenNV;}
    public String getMaNV() {return maNV;}

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
}


