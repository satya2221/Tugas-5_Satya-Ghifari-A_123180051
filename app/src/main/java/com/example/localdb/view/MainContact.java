package com.example.localdb.view;

import android.view.View;

import com.example.localdb.entity.AppDatabase;
import com.example.localdb.entity.DataRaket;

import java.util.List;

public interface MainContact {
    interface view extends View.OnClickListener{
        void successAdd();
        void successDelete();
        void resetForm();
        void getData(List<DataRaket> list);
        void editData(DataRaket item);
        void deleteData(DataRaket item);
    }

    interface presenter{
        void insertData(String nama_raket, String merek_raket, int tensi_max, int balance_point, AppDatabase database);
        void readData(AppDatabase database);
        void editData(String nama_raket, String merek_raket, int tensi_max, int balance_point, int id, AppDatabase database);
        void deleteData(DataRaket dataRaket, AppDatabase database);
    }
}
