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
import com.example.covidalertapp.model.MedicalCollegesObject;
import com.example.covidalertapp.room.DataRepository;
import com.example.covidalertapp.util.ListStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class CollegesFragment extends Fragment {
    private static final String TAG = "CollegesFragment";

    private RecyclerView recyclerView;
    private CollegeRVAdapter adapter;
    private List<MedicalCollegesObject> list = new ArrayList<>();
    private ListStorage storage;


    public CollegesFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_colleges, container, false)  ;

        storage = ListStorage.getListInstance();


        recyclerView = view.findViewById(R.id.recycler_view_college);

        if(storage.getCollegeList().isEmpty()){
            // call api

            Log.d(TAG, "onCreateView: Starting AsyncTask....");
            new GetDataFromApiAsyncTask().execute();

        }else {

            Log.d(TAG, "onCreateView: Already had the data in the list..");
            list = storage.getCollegeList();
            setupRecyclerView();
        }

        return view;


    }


    private void setupRecyclerView(){

        if(list.isEmpty()){
            list = storage.getCollegeList();
        }

        Log.d(TAG, "setupRecyclerView: called");

        adapter = new CollegeRVAdapter(list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.hasFixedSize();

        Log.d(TAG, "setupRecyclerView: Complete...");
    }

    private class GetDataFromApiAsyncTask extends AsyncTask<Void, Void, List<MedicalCollegesObject>>{

        private DataRepository repository = new DataRepository(getActivity().getApplication());

        public GetDataFromApiAsyncTask(){

        }

        @Override
        protected List<MedicalCollegesObject> doInBackground(Void... voids) {
            Log.d(TAG, "doInBackground: called");

            repository.getCollegeDataFromApi();

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
        protected void onPostExecute(List<MedicalCollegesObject> medicalCollegesObjects) {
            super.onPostExecute(medicalCollegesObjects);

            ListStorage storage = ListStorage.getListInstance();
            list = storage.getCollegeList();
            Log.d(TAG, "onPostExecute: Complete ");
        }
    }
}
