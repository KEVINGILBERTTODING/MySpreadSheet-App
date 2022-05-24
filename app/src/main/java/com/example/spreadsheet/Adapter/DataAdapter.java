package com.example.spreadsheet.Adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spreadsheet.Model.DataModel;
import com.example.spreadsheet.R;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {

    Context context;
    List<DataModel> dataModels;

    public DataAdapter(Context context, List<DataModel>dataModels) {
        this.context    =   context;
        this.dataModels =   dataModels;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent,false);
        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull DataAdapter.MyViewHolder holder, int position) {

        holder.nama.setText(dataModels.get(position).getNama());
        holder.email.setText(dataModels.get(position).getEmail());
        holder.jk.setText(dataModels.get(position).getJenis_kelamin());
        holder.alamat.setText(dataModels.get(position).getAlamat());
        holder.timestamp.setText(dataModels.get(position).getTimestamp());

    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nama, email, jk, alamat, timestamp;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            // Insialisasi textView

            nama            =       itemView.findViewById(R.id.tv_nama);
            email           =       itemView.findViewById(R.id.tv_email);
            jk              =       itemView.findViewById(R.id.tv_jk);
            alamat          =       itemView.findViewById(R.id.tv_alamat);
            timestamp       =       itemView.findViewById(R.id.tv_time);



        }

    }
}
