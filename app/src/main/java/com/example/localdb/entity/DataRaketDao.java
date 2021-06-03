package com.example.localdb.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DataRaketDao {
    @Insert
    Long insertData(DataRaket dataRaket);

    @Query("Select * from raket_db")
    List<DataRaket> getData();

    @Update
    int updateData(DataRaket item);

    @Delete
    void deleteData(DataRaket item);
}
