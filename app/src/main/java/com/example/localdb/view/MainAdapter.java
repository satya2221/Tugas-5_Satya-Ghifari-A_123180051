package com.example.localdb.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.localdb.R;
import com.example.localdb.entity.DataRaket;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.viewHolder> {
    Context context;
    List<DataRaket> list;
    MainContact.view mView;

    public MainAdapter(Context context, List<DataRaket> list, MainContact.view mView) {
        this.context = context;
        this.list = list;
        this.mView = mView;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_raket, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final DataRaket dataRaket = list.get(position);
        holder.tvNama.setText(dataRaket.getNama_raket());
        holder.tvMerek.setText(dataRaket.getMerek_raket());
        holder.tvTensiMax.setText(String.valueOf(dataRaket.getTensi_max()));
        holder.tvBalancePoint.setText(String.valueOf(dataRaket.getBalance_point()));

        holder.kartu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView.editData(dataRaket);
            }
        });

        holder.kartu.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mView.deleteData(dataRaket);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvMerek, tvTensiMax, tvBalancePoint;
        CardView kartu;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.nama_raket);
            tvMerek = itemView.findViewById(R.id.merek);
            tvTensiMax = itemView.findViewById(R.id.tensi);
            tvBalancePoint = itemView.findViewById(R.id.bp);

            kartu = itemView.findViewById(R.id.bungkus);

        }
    }
}
