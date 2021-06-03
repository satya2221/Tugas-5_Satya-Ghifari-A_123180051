package com.example.localdb.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.localdb.R;
import com.example.localdb.entity.AppDatabase;
import com.example.localdb.entity.DataRaket;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContact.view{
    private AppDatabase appDatabase;
    private MainPresenter mainPresenter;
    private MainAdapter mainAdapter;

    private Button btnSubmit;
    private RecyclerView recyclerView;
    private EditText etNama, etMerek,etTensiMax, etBalancePoint;

    private int id = 0;
    boolean edit = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSubmit = findViewById(R.id.submit);
        etNama = findViewById(R.id.nama_raket);
        etMerek = findViewById(R.id.merek);
        etTensiMax = findViewById(R.id.tensi_max);
        etBalancePoint = findViewById(R.id.balance_point);
        recyclerView = findViewById(R.id.rv_raket);

        appDatabase = AppDatabase.inidb(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mainPresenter = new MainPresenter(this);
        mainPresenter.readData(appDatabase);

        btnSubmit.setOnClickListener(this::onClick);
    }

    @Override
    public void successAdd() {
        Toast.makeText(this, "Berhasil nambahkan", Toast.LENGTH_SHORT).show();
        mainPresenter.readData(appDatabase);
    }

    @Override
    public void successDelete() {
        Toast.makeText(this, "Berhasil Ngapus", Toast.LENGTH_SHORT).show();
        mainPresenter.readData(appDatabase);
    }

    @Override
    public void resetForm() {
        etNama.setText("");
        etMerek.setText("");
        etTensiMax.setText("");
        etBalancePoint.setText("");
        btnSubmit.setText("Submit");
    }

    @Override
    public void getData(List<DataRaket> list) {
        mainAdapter = new MainAdapter(this, list, this);
        recyclerView.setAdapter(mainAdapter);
    }

    @Override
    public void editData(DataRaket item) {
        etNama.setText(item.getNama_raket());
        etMerek.setText(item.getMerek_raket());
        etTensiMax.setText(String.valueOf(item.getTensi_max()));
        etBalancePoint.setText(String.valueOf(item.getBalance_point()));
        id = item.getId();
        edit = true;
        btnSubmit.setText("Edit Data");
    }

    @Override
    public void deleteData(DataRaket item) {
        AlertDialog.Builder builder;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        }
        else{
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Menghapus Data")
                .setMessage("Yakin mau dihapus?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        resetForm();
                        mainPresenter.deleteData(item, appDatabase);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_dialer)
                .show();
    }

    @Override
    public void onClick(View v) {
        if(v == btnSubmit){
            Log.d("nyobain", "onClick: yes");
            if(etNama.getText().toString().equals("")||etMerek.getText().toString().equals("")||
               etTensiMax.getText().toString().equals("") || etBalancePoint.getText().toString().equals("")){
                Toast.makeText(this, "Diisi dulu semua bos", Toast.LENGTH_SHORT).show();
            }
            else{
                if(!edit){
                    mainPresenter.insertData(etNama.getText().toString(), etMerek.getText().toString(),
                            Integer.parseInt(etTensiMax.getText().toString()),
                            Integer.parseInt(etBalancePoint.getText().toString()), appDatabase);
                }
                else{
                    mainPresenter.editData(etNama.getText().toString(), etMerek.getText().toString(),
                            Integer.parseInt(etTensiMax.getText().toString()),
                            Integer.parseInt(etBalancePoint.getText().toString()), id, appDatabase);
                    edit = false;
                }
                resetForm();
            }
        }
    }
}