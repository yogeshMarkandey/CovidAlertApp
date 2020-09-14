package com.example.covidalertapp.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidalertapp.R;
import com.example.covidalertapp.adapter.CollegeRVAdapter;
import com.example.covidalertapp.adapter.HelplineRVAdapter;
import com.example.covidalertapp.model.ContactDetails;
import com.example.covidalertapp.model.MedicalCollegesObject;
import com.example.covidalertapp.room.DataRepository;
import com.example.covidalertapp.util.ListStorage;

import java.util.ArrayList;
import java.util.List;

public class HelplineFragment extends Fragment {
    private static final String TAG = "HelplineFragment";

    private RecyclerView recyclerView;
    private HelplineRVAdapter adapter;
    private List<ContactDetails> list = new ArrayList<>();
    private ListStorage storage ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_helpline, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_helpline);

        storage = ListStorage.getListInstance();

        if(storage.getHelplineList().isEmpty()){
            // call api

            new GetDataFromApiAsyncTask().execute();

        }else {
            list = storage.getHelplineList();
            setupRecyclerView();

        }


        return view;
    }

    public HelplineFragment(){

    }


    private void setupRecyclerView(){
        if(list.isEmpty()){
            list = storage.getHelplineList();
        }

        Log.d(TAG, "setupRecyclerView: called");

        Log.d(TAG, "setupRecyclerView: list siz 00 --==  " + list.size());
        Log.d(TAG, "setupRecyclerView: list siz 00 --==  " + storage.getHelplineList().size());

        adapter = new HelplineRVAdapter(list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.hasFixedSize();

        Log.d(TAG, "setupRecyclerView: Complete...");
    }

    private class GetDataFromApiAsyncTask extends AsyncTask<Void, Void, Void>{

        private DataRepository repository = new DataRepository(getActivity().getApplication());

        public GetDataFromApiAsyncTask(){

        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d(TAG, "doInBackground: called");

            repository.getHelplineDateFromApi();

            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



            return null;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Log.d(TAG, "onPreExecute: Called");
        }

        @Override
        protected void onPostExecute(Void medicalCollegesObjects) {
            super.onPostExecute(medicalCollegesObjects);

            ListStorage storage = ListStorage.getListInstance();
            list = storage.getHelplineList();
            Log.d(TAG, "onPostExecute: Complete ");
        }
    }
}
