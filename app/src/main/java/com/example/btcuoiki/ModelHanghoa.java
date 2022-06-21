package com.example.btcuoiki;

import java.util.ArrayList;

public class ModelHanghoa {
    ArrayList<rateItem> rateData;

    public ArrayList<rateItem> getRateData() {
        return rateData;
    }

    public void setRateData(ArrayList<rateItem> rateData) {
        this.rateData = rateData;
    }

    public class rateItem {
        int id;
        int idUser;
        String nhanxet;
        int danhgia;
        String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIdUser() {
            return idUser;
        }

        public void setIdUser(int idUser) {
            this.idUser = idUser;
        }

        public String getNhanxet() {
            return nhanxet;
        }

        public void setNhanxet(String nhanxet) {
            this.nhanxet = nhanxet;
        }

        public int getDanhgia() {
            return danhgia;
        }

        public void setDanhgia(int danhgia) {
            this.danhgia = danhgia;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    int id;
    String ten;
    String gia;
    String mota;
    String hinhanh;
    int soluongton;
    String loai;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public int getSoluongton() {
        return soluongton;
    }

    public void setSoluongton(int soluongton) {
        this.soluongton = soluongton;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }
}
