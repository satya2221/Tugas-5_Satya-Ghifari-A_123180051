package com.example.localdb.entity;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DataRaket.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DataRaketDao dao();
    private static AppDatabase appDatabase;

    public static AppDatabase inidb(Context context){
        if (appDatabase == null){
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, "dbRaket").allowMainThreadQueries().build();
        }
        return appDatabase;
    }
}
