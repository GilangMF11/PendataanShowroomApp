package com.example.pendataanshowroom.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Pemilik implements Parcelable{
    private String id;
    private String nama;
    private String jenisK;
    private String alamat;
    private String no_telp;
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Pemilik() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenisK() {
        return jenisK;
    }

    public void setJenisK(String jenisK) {
        this.jenisK = jenisK;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.nama);
        dest.writeString(this.jenisK);
        dest.writeString(this.alamat);
        dest.writeString(this.no_telp);
        dest.writeString(this.getPhoto());


    }

    protected Pemilik(Parcel in) {
        this.id = in.readString();
        this.nama = in.readString();
        this.jenisK = in.readString();
        this.alamat = in.readString();
        this.no_telp = in.readString();
        this.photo = in.readString();
    }

    public static final Parcelable.Creator<Pemilik> CREATOR = new Parcelable.Creator<Pemilik>(){

        @Override
        public Pemilik createFromParcel(Parcel sourse) {
            return new Pemilik(sourse);
        }

        @Override
        public Pemilik[] newArray(int size) {
            return new Pemilik[size];
        }
    };
}
