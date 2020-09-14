package com.example.covidalertapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidalertapp.R;
import com.example.covidalertapp.model.HospitalRegionalData;

import java.util.ArrayList;
import java.util.List;

public class HospitalRVAdapter extends RecyclerView.Adapter<HospitalRVAdapter.HospitalViewHolder> {

    private List<HospitalRegionalData> list  = new ArrayList<>();
    private Context context;

    public HospitalRVAdapter(List<HospitalRegionalData> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public HospitalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stats_hospital, parent, false);

        HospitalViewHolder holder = new HospitalViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalViewHolder holder, int position) {
        HospitalRegionalData data = list.get(position);

        holder.state.setText(data.getState());
        holder.ruralBeds.setText(data.getRuralBeds());
        holder.urbanBeds.setText(data.getUrbanBeds());
        holder.urbanHospital.setText(data.getUrbanHospitals());
        holder.ruralHospital.setText(data.getRuralHospital());
        holder.total_state_wise.setText(data.getTotalBeds());
        holder.totalHospital.setText(data.getTotalHospitals());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HospitalViewHolder extends RecyclerView.ViewHolder{

        private TextView state, ruralHospital, urbanHospital, ruralBeds, urbanBeds;
        private TextView totalHospital, total_state_wise;

        public HospitalViewHolder(@NonNull View itemView) {
            super(itemView);

            state = itemView.findViewById(R.id.textView_state);
            ruralHospital = itemView.findViewById(R.id.textView_rural);
            urbanHospital = itemView.findViewById(R.id.textView_urban);
            ruralBeds = itemView.findViewById(R.id.textView_rural_beds);
            urbanBeds = itemView.findViewById(R.id.textView_urban_beds);
            total_state_wise = itemView.findViewById(R.id.textView_total_state_wise);
            totalHospital = itemView.findViewById(R.id.textView_total);



        }
    }
}
