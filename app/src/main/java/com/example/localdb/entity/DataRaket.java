package com.example.localdb.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "raket_db")
public class DataRaket {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "nama_raket")
    private String nama_raket;
    @ColumnInfo(name = "merek_raket")
    private String merek_raket;
    @ColumnInfo(name = "tensi_max")
    private int tensi_max;
    @ColumnInfo(name = "balance_point")
    private int balance_point;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama_raket() {
        return nama_raket;
    }

    public void setNama_raket(String nama_raket) {
        this.nama_raket = nama_raket;
    }

    public String getMerek_raket() {
        return merek_raket;
    }

    public void setMerek_raket(String merek_raket) {
        this.merek_raket = merek_raket;
    }

    public int getTensi_max() {
        return tensi_max;
    }

    public void setTensi_max(int tensi_max) {
        this.tensi_max = tensi_max;
    }

    public int getBalance_point() {
        return balance_point;
    }

    public void setBalance_point(int balance_point) {
        this.balance_point = balance_point;
    }
}
