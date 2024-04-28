/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */

public class ChienDich {
    private String chiendichID;
    private String tenChiendich;
    private String motaChitiet;
    private String loaiChiendich;
    private String mucTieu;
    private double chiPhi;
    private String ngayBatDau;
    private String ngayKetThuc;
    private String nhanVienID; 

    public ChienDich() {
    }

    public ChienDich(String chiendichID, String tenChiendich, String motaChitiet, String loaiChiendich, String mucTieu, double chiPhi, String ngayBatDau, String ngayKetThuc, String nhanVienID) {
        this.chiendichID = chiendichID;
        this.tenChiendich = tenChiendich;
        this.motaChitiet = motaChitiet;
        this.loaiChiendich = loaiChiendich;
        this.mucTieu = mucTieu;
        this.chiPhi = chiPhi;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.nhanVienID = nhanVienID;
    }

    public String getChiendichID() {
        return chiendichID;
    }

    public void setChiendichID(String chiendichID) {
        this.chiendichID = chiendichID;
    }

    public String getTenChiendich() {
        return tenChiendich;
    }

    public void setTenChiendich(String tenChiendich) {
        this.tenChiendich = tenChiendich;
    }

    public String getMotaChitiet() {
        return motaChitiet;
    }

    public void setMotaChitiet(String motaChitiet) {
        this.motaChitiet = motaChitiet;
    }

    public String getLoaiChiendich() {
        return loaiChiendich;
    }

    public void setLoaiChiendich(String loaiChiendich) {
        this.loaiChiendich = loaiChiendich;
    }

    public String getMucTieu() {
        return mucTieu;
    }

    public void setMucTieu(String mucTieu) {
        this.mucTieu = mucTieu;
    }

    public double getChiPhi() {
        return chiPhi;
    }

    public void setChiPhi(double chiPhi) {
        this.chiPhi = chiPhi;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getNhanVienID() {
        return nhanVienID;
    }

    public void setNhanVienID(String nhanVienID) {
        this.nhanVienID = nhanVienID;
    }

    @Override
    public String toString() {
        return "ChienDich{" +
                "chiendichID='" + chiendichID + '\'' +
                ", tenChiendich='" + tenChiendich + '\'' +
                ", motaChitiet='" + motaChitiet + '\'' +
                ", loaiChiendich='" + loaiChiendich + '\'' +
                ", mucTieu='" + mucTieu + '\'' +
                ", chiPhi=" + chiPhi +
                ", ngayBatDau=" + ngayBatDau +
                ", ngayKetThuc=" + ngayKetThuc +
                ", nhanVienID='" + nhanVienID + '\'' +
                '}';
    }
}

