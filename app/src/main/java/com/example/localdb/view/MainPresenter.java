package com.example.localdb.view;

import android.os.AsyncTask;
import android.util.Log;

import com.example.localdb.entity.AppDatabase;
import com.example.localdb.entity.DataRaket;

import java.util.List;

public class MainPresenter implements MainContact.presenter{
    private MainContact.view view;

    public MainPresenter(MainContact.view view) {
        this.view = view;
    }

    class InsertData extends AsyncTask<Void,Void,Long>{
        private AppDatabase appDatabase;
        private DataRaket dataRaket;

        public InsertData(AppDatabase appDatabase, DataRaket dataRaket) {
            this.appDatabase = appDatabase;
            this.dataRaket = dataRaket;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            return appDatabase.dao().insertData(dataRaket);
        }
        protected void onPostExecute(Long along){
            super.onPostExecute(along);
            view.successAdd();
        }
    }
    @Override
    public void insertData(String nama_raket, String merek_raket, int tensi_max, int balance_point, AppDatabase database) {
        final DataRaket dataRaket = new DataRaket();
        dataRaket.setNama_raket(nama_raket);
        dataRaket.setMerek_raket(merek_raket);
        dataRaket.setTensi_max(tensi_max);
        dataRaket.setBalance_point(balance_point);
        new InsertData(database, dataRaket).execute();
    }

    @Override
    public void readData(AppDatabase database) {
        List<DataRaket> list;
        list = database.dao().getData();
        view.getData(list);
    }
    class EditData extends AsyncTask<Void, Void, Integer> {
        private AppDatabase appDatabase;
        private DataRaket dataRaket;

        public EditData(AppDatabase appDatabase, DataRaket dataRaket) {
            this.appDatabase = appDatabase;
            this.dataRaket = dataRaket;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return appDatabase.dao().updateData(dataRaket);
        }
        protected void onPostExecute(Integer integer){
            super.onPostExecute(integer);
            Log.d("integer db", "onPostExecute : "+integer);
            view.successAdd();
        }
    }
    @Override
    public void editData(String nama_raket, String merek_raket, int tensi_max, int balance_point, int id, AppDatabase database) {
        final DataRaket dataRaket = new DataRaket();
        dataRaket.setNama_raket(nama_raket);
        dataRaket.setMerek_raket(merek_raket);
        dataRaket.setTensi_max(tensi_max);
        dataRaket.setBalance_point(balance_point);
        dataRaket.setId(id);
        new EditData(database, dataRaket).execute();
    }
    class DeleteData extends AsyncTask<Void, Void, Long> {
        private AppDatabase appDatabase;
        private DataRaket dataRaket;

        public DeleteData(AppDatabase appDatabase, DataRaket dataRaket) {
            this.appDatabase = appDatabase;
            this.dataRaket = dataRaket;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            appDatabase.dao().deleteData(dataRaket);
            return null;
        }
        protected void onPostExecute(Long along){
            super.onPostExecute(along);
            view.successDelete();
        }
    }
    @Override
    public void deleteData(DataRaket dataRaket, AppDatabase database) {
        new DeleteData(database, dataRaket).execute();
    }
}
