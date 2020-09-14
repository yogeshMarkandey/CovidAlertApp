package com.example.covidalertapp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidalertapp.R;
import com.example.covidalertapp.model.NotificationObject;
import com.example.covidalertapp.room.NoteViewModel;

import java.util.ArrayList;
import java.util.List;

public class NotificationRVAdapter extends RecyclerView.Adapter<NotificationRVAdapter.NotificationHolder>  {
    private static final String TAG = "NotificationRVAdapter";

    private List<NotificationObject> list = new ArrayList<>();

    public NotificationRVAdapter(List<NotificationObject> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public NotificationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);

        NotificationHolder holder = new NotificationHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationHolder holder, int position) {

        NotificationObject data = list.get(position);

       if(data.getTitle().length() > 10){
           holder.date.setText(data.getTitle().substring(0, 10));
           holder.title.setText(data.getTitle().substring(11));
           holder.link.setText(data.getLink());
       }else{
           Log.d(TAG, "onBindViewHolder:  length less than 10");
       }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NotificationHolder extends RecyclerView.ViewHolder{

        TextView date, title, link;

        public NotificationHolder(@NonNull View itemView) {
            super(itemView);

            date =itemView.findViewById(R.id.textView_date);
            title =itemView.findViewById(R.id.text_view_title);
            link =itemView.findViewById(R.id.text_view_link);

        }
    }
}
