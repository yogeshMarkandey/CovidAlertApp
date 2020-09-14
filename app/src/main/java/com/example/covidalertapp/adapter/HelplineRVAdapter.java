package com.example.covidalertapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidalertapp.R;
import com.example.covidalertapp.model.ContactDetails;

import java.util.ArrayList;
import java.util.List;

public class HelplineRVAdapter extends RecyclerView.Adapter<HelplineRVAdapter.HelpLineViewHolder>  {

    private List<ContactDetails> list = new ArrayList<>();

    public HelplineRVAdapter(List<ContactDetails> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public HelpLineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_helpiline, parent, false);
        HelpLineViewHolder holder = new HelpLineViewHolder(v);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull HelpLineViewHolder holder, int position) {

        ContactDetails data = list.get(position);
        holder.state.setText(data.getLoc());
        holder.helpline.setText(data.getNumber());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HelpLineViewHolder extends RecyclerView.ViewHolder{

        TextView state, helpline;


        public HelpLineViewHolder(@NonNull View itemView) {
            super(itemView);

            state = itemView.findViewById(R.id.textView_name_of_state);
            helpline = itemView.findViewById(R.id.text_view_helpline);

        }
    }
}
