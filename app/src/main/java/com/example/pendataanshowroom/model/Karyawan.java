package com.example.pendataanshowroom.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Karyawan implements Parcelable {
    private String id;
    private String nim;
    private String nama;
    private String photo;

    public Karyawan() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.nim);
        dest.writeString(this.nama);
        dest.writeString(this.photo);
    }

    protected Karyawan(Parcel in) {
        this.id = in.readString();
        this.nim = in.readString();
        this.nama = in.readString();
        this.photo = in.readString();
    }

    public static final Parcelable.Creator<Karyawan> CREATOR = new Parcelable.Creator<Karyawan>() {
        @Override
        public Karyawan createFromParcel(Parcel source) {
            return new Karyawan(source);
        }

        @Override
        public Karyawan[] newArray(int size) {
            return new Karyawan[size];
        }
    };
}