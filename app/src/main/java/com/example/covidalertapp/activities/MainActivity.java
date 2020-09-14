package com.example.covidalertapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.covidalertapp.R;
import com.example.covidalertapp.room.DataRepository;
import com.example.covidalertapp.room.NoteViewModel;
import com.example.covidalertapp.room.SampleDao;
import com.example.covidalertapp.room.SampleData;
import com.example.covidalertapp.util.ListStorage;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    private BottomNavigationView bottomNavigationView;
    private FrameLayout containerFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: called");
        setContentView(R.layout.activity_main);


        bottomNavigationView = findViewById(R.id.bottom_navig_view);
        containerFrame = findViewById(R.id.frame_container);


        NoteViewModel noteViewModel = new ViewModelProvider(MainActivity.this).get(NoteViewModel.class);


        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                new HomeFragment()).commit();


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                                new HomeFragment()).commit();
                        return true;
                    case R.id.menu_stats:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                                new StatisticFragment()).commit();
                        return true;
                    case R.id.menu_info:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                                new InformationFragment()).commit();
                        return true;
                    case R.id.menu_dashboard:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                                new DashboardFargment()).commit();
                        return true;
                    default:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                                new HomeFragment()).commit();
                        return true;
                }
            }
        });


        new GetDataAsyncTask(noteViewModel.getDataRepository()).execute();

        noteViewModel.getAllDeceasedPeople();
        ListStorage storage = ListStorage.getListInstance();
        try {
            if(noteViewModel.getAllNotes().size() <=0){
               new somethingAsyncTask(getApplicationContext(), noteViewModel).execute();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    private class GetDataAsyncTask extends AsyncTask<Void, Void, Void> {

        DataRepository repository;


        public GetDataAsyncTask(DataRepository repository) {
            this.repository = repository;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            repository.getCollegeDataFromApi();
            try {
                repository.getHospitalDataFromApi();
            } catch (IOException e) {
                e.printStackTrace();
            }
            repository.getNotificationData();
            repository.getHelplineDateFromApi();
            repository.getAllPeople();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            ListStorage storage = ListStorage.getListInstance();


            Log.d(TAG, "onPostExecute Special:  list Size College : " + storage.getCollegeList().size());
            Log.d(TAG, "onPostExecute Special:  list Size Helpline : " + storage.getHelplineList().size());
            Log.d(TAG, "onPostExecute Special:  list Size Notification : " + storage.getNotificationObjectList().size());
            Log.d(TAG, "onPostExecute Special:  list Size Dead : " + storage.getDeceasedList().size());
            Log.d(TAG, "onPostExecute Special:  list Size Recovered : " + storage.getRecoveredList().size());
            Log.d(TAG, "onPostExecute Special:  list Size hospital : " + storage.getHospitalList().size());
            Log.d(TAG, "onPostExecute Special:  list Size Hospitalized : " + storage.getHospitalizedList().size());

        }
    }

    private class somethingAsyncTask extends AsyncTask<Void, Void, Void>{

        DataRepository repository = new DataRepository(getApplication());
        Context context;
        NoteViewModel dao;

        public somethingAsyncTask(Context context , NoteViewModel dao) {
            this.context = context;
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            repository.readDataFromCsv(context);
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dao.getAllDeceasedPeople();
        }
    }

}

