package com.example.covidalertapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidalertapp.R;
import com.example.covidalertapp.model.MedicalCollegesObject;
import com.example.covidalertapp.util.ListStorage;

import java.util.ArrayList;
import java.util.List;

public class CollegeRVAdapter extends RecyclerView.Adapter<CollegeRVAdapter.CollegeViewHolder> {

    private List<MedicalCollegesObject> list = new ArrayList<>();

    public CollegeRVAdapter(List<MedicalCollegesObject> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CollegeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_college, parent, false);

        CollegeViewHolder holder =new CollegeViewHolder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull CollegeViewHolder holder, int position) {

        MedicalCollegesObject data  = list.get(position);

        holder.state.setText(data.getState());
        holder.instituteName.setText(data.getName());
        holder.city.setText(data.getCity());
        holder.type.setText(data.getOwnership());
        holder.admissionCapacity.setText(data.getAdmissionCapacity());
        holder.hospitalBeds.setText(data.getHospitalBeds());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CollegeViewHolder extends RecyclerView.ViewHolder {

        TextView state, instituteName, city, type, admissionCapacity, hospitalBeds;

        public CollegeViewHolder(@NonNull View itemView) {
            super(itemView);


            state = itemView.findViewById(R.id.textView_state_college);
            instituteName = itemView.findViewById(R.id.textView_institute_name);
            city = itemView.findViewById(R.id.textView_city_name);
            type = itemView.findViewById(R.id.textView_type);
            admissionCapacity = itemView.findViewById(R.id.textView_capacity);
            hospitalBeds = itemView.findViewById(R.id.textView_beds);

        }


    }
}
