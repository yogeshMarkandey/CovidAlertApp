package com.example.covidalertapp.room;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;


/*
 *
 * ViewModel--
 *           Part of android architecture library &
 * its job is to store and process data for the User
 * interface and communicate with the model.
 *
 * It helps to put ui related information in ViewModel
 * because it can survive configuration changes.
 *
 * Our activity have a reference to the ViewModel. Not
 * to the repository. So here we create Wrapper Method
 * for our database operation methods which our activity
 * will call to perform database operations.
 *
 *
 */
public class NoteViewModel extends AndroidViewModel {
    private static final String TAG = "NoteViewModel";
    private DataRepository dataRepository;
    private List<SampleData> allNotes;


    public NoteViewModel(@NonNull Application application) {
        super(application);
        dataRepository = new DataRepository(application);


    }


    //Wrapper methods for database operations.
    public void insert(SampleData sampleData){
        dataRepository.insert(sampleData);
    }
    public void update(SampleData sampleData){
        dataRepository.update(sampleData);
    }
    public void delete(SampleData sampleData){
        dataRepository.delete(sampleData);
    }
    public void deleteAll(){
        dataRepository.deleteAll();
    }
    public List<SampleData> getAllNotes() throws ExecutionException, InterruptedException {
        return dataRepository.getAllNotes();
    }

    public void readCsvData(Context context){
        dataRepository.readDataFromCsv(context);
    }

   public void getAllDeceasedPeople(){
         dataRepository.getAllPeople();
   }


    public DataRepository getDataRepository() {
        return dataRepository;
    }
}
