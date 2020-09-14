package com.example.covidalertapp.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidalertapp.R;
import com.example.covidalertapp.adapter.HospitalRVAdapter;
import com.example.covidalertapp.model.HospitalRegionalData;
import com.example.covidalertapp.room.DataRepository;
import com.example.covidalertapp.util.ListStorage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Hospitals extends Fragment {
    private static final String TAG = "Hospitals";

    private RecyclerView recyclerView_hospitals;
    private HospitalRVAdapter adapter;
    private ProgressBar progressBar;

    private List<HospitalRegionalData> list = new ArrayList<>();


    public Hospitals(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_hospital, container, false)  ;

        ListStorage storage = ListStorage.getListInstance();

        Log.d(TAG, "onCreateView: called ");
        recyclerView_hospitals = view.findViewById(R.id.recycler_view_hospital);
        progressBar = view.findViewById(R.id.progress_hospital);


        if(storage.getHospitalList().isEmpty()){
            progressBar.setVisibility(View.VISIBLE);
            try {
                list = new getDataAsyncTask().execute().get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }else {
            progressBar.setVisibility(View.GONE);
            list = storage.getHospitalList();
            setupRecyclerView();
        }

        return view;
    }

    private void setupRecyclerView(){
        Log.d(TAG, "setupRecyclerView: Called");
        Log.d(TAG, "setupRecyclerView: size of list = " + list.size());

        adapter = new HospitalRVAdapter(list);

        recyclerView_hospitals.setAdapter(adapter);
        recyclerView_hospitals.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView_hospitals.setHasFixedSize(true);
        Log.d(TAG, "setupRecyclerView: Complete");
    }

    private class getDataAsyncTask extends AsyncTask<Void, Void, List<HospitalRegionalData>>{

        private DataRepository repository = new DataRepository(getActivity().getApplication());

        public  getDataAsyncTask(){

        }


        @Override
        protected void onPostExecute(List<HospitalRegionalData> hospitalRegionalData) {
            super.onPostExecute(hospitalRegionalData);
            Log.d(TAG, "onPostExecute: Called");

            ListStorage storage = ListStorage.getListInstance();
            list = storage.getHospitalList();

            progressBar.setVisibility(View.GONE);

            setupRecyclerView();


        }

        @Override
        protected List<HospitalRegionalData> doInBackground(Void... voids) {

            Log.d(TAG, "doInBackground: called");
            List<HospitalRegionalData> listN = null;
            try {
                listN = repository.getHospitalDataFromApi();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "doInBackground: listN size = " + listN.size());

            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return listN;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "onPreExecute: called");
            progressBar.setVisibility(View.VISIBLE);
        }
    }
}
