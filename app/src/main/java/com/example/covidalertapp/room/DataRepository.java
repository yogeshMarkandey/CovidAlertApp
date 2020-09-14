package com.example.covidalertapp.room;


import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.os.storage.StorageManager;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.example.covidalertapp.R;
import com.example.covidalertapp.activities.MainActivity;
import com.example.covidalertapp.apis.ContactDetailsApi;
import com.example.covidalertapp.apis.HospitalApi;
import com.example.covidalertapp.apis.MedicalCollegeApi;
import com.example.covidalertapp.apis.NotificationApi;
import com.example.covidalertapp.model.ContactDetailResponses;
import com.example.covidalertapp.model.ContactDetails;
import com.example.covidalertapp.model.HospitalDataResponse;
import com.example.covidalertapp.model.HospitalRegionalData;
import com.example.covidalertapp.model.MedicalCollegesObject;
import com.example.covidalertapp.model.MedicalCollegesResponse;
import com.example.covidalertapp.model.NotificationDetailsResponses;
import com.example.covidalertapp.model.NotificationObject;
import com.example.covidalertapp.util.ListStorage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 * This class act as another abstraction layer
 * to the database extraction.
 *
 * This class helps as in accessing the data for
 * database and also when there is more than one
 * type of database or different APIs.
 *
 *
 * */
public class DataRepository {
    private static final String TAG = "DataRepository";

    private SampleDao sampleDao;
    private CantactDetailesDao cantactDetailesDao;
    private static List<SampleData> allNotes;

    //Constructor
    public DataRepository(Application application) {
        SampleTestingDatabase sampleTestingDatabase = SampleTestingDatabase.getInstance(application);
        // below methods do not contains body because we created it in abstract and interface
        // Room will auto-generate this methods while compiling so we don't have to do it.
        sampleDao = sampleTestingDatabase.sampleDao();
        cantactDetailesDao = sampleTestingDatabase.cantactDetailesDao();
    }

    // for these operations we have to create a new Thread because room do not all database
    //operation on main thread, So we have to make AsyncTask for these methods
    public void insert(SampleData sampleData) {
        new InsertNoteAsyncTask(sampleDao).execute(sampleData);
    }

    public void update(SampleData sampleData) {
        new UpdateNoteAsyncTask(sampleDao).execute(sampleData);
    }

    public void delete(SampleData sampleData) {
        new DeleteNoteAsyncTask(sampleDao).execute(sampleData);
    }

    public void deleteAll() {
        new DeleteAllNoteAsyncTask(sampleDao).execute();
    }


    // for this method Room automatically run this method on new thread.
    // Todo : make a AsyncTask for This method
    public List<SampleData> getAllNotes() throws ExecutionException, InterruptedException {
        Log.d(TAG, "getAllNotes: Called... get notes");
        return new GetAllDataAsyncTask(sampleDao).execute().get();

    }


    public void getAllPeople(){
         new GetAllAsyncTask(sampleDao).execute();
    }


    private static class  GetAllAsyncTask extends AsyncTask<Void, Void, Void>{

        private SampleDao sampleDao;
        private ListStorage storage = ListStorage.getListInstance();
        public GetAllAsyncTask(SampleDao sampleDao) {
            this.sampleDao = sampleDao;

        }

        @Override
        protected Void doInBackground(Void... voids) {
            storage.setDeceasedList(sampleDao.getAllDeceasedPeople());
            storage.setRecoveredList(sampleDao.getAllRecoveredPeople());
            storage.setHospitalizedList(sampleDao.getAllHospitalisedPeople());

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



    private static class GetAllDataAsyncTask extends AsyncTask<Void, Void, List<SampleData>>{

        private SampleDao sampleDao;

        public GetAllDataAsyncTask(SampleDao sampleDao) {
            this.sampleDao = sampleDao;
        }


        @Override
        protected List<SampleData> doInBackground(Void... voids) {
            Log.d(TAG, "doInBackground: called get all notes");
            return sampleDao.getAllNotes();
        }
    }

    // it has to be static so that it doenot have reference to the repository
    //otherwise it will cause a memory leak.
    private static class InsertNoteAsyncTask extends AsyncTask<SampleData, Void, Void> {

        private SampleDao sampleDao;

        private InsertNoteAsyncTask(SampleDao sampleDao) {
            this.sampleDao = sampleDao;
        }

        @Override
        protected Void doInBackground(SampleData... sampleData) {
            sampleDao.insert(sampleData[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<SampleData, Void, Void> {

        private SampleDao sampleDao;

        private UpdateNoteAsyncTask(SampleDao sampleDao) {
            this.sampleDao = sampleDao;
        }

        @Override
        protected Void doInBackground(SampleData... sampleData) {
            sampleDao.update(sampleData[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<SampleData, Void, Void> {

        private SampleDao sampleDao;

        private DeleteNoteAsyncTask(SampleDao sampleDao) {
            this.sampleDao = sampleDao;
        }

        @Override
        protected Void doInBackground(SampleData... sampleData) {
            sampleDao.delete(sampleData[0]);
            return null;
        }
    }

    private static class DeleteAllNoteAsyncTask extends AsyncTask<Void, Void, Void> {

        private SampleDao sampleDao;

        private DeleteAllNoteAsyncTask(SampleDao sampleDao) {
            this.sampleDao = sampleDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            sampleDao.deleteAll();
            return null;
        }


    }

    // for setting up the database

    public void readDataFromCsv(Context context){
// for getting the data from CSV file


        List<SampleData> sampleDataList = new ArrayList<>();
        Log.d(TAG, "onCreate: setting up Reading CSV");
        String tableName = null;
        InputStream inputStream;

        tableName = "sample_testing_table";
        inputStream = context.getResources().openRawResource(R.raw.covid_india);

        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(inputStream, Charset.forName("UTF-8"))
        );

        String line = null;


        try {

            Log.d(TAG, "onCreate: Entered in try ....");
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {


                // Split line
                String[] tokens = line.split(",");



                if(tokens.length <8){
                    //Toast.makeText(this, "At index less than 8: " + tokens[1], Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onCreate: At index less than 8: " + tokens[1]);
                }else{
                    // Read Line
                    SampleData data = new SampleData();
                    data.setReportedOn(tokens[1]);
                    if(!tokens[3].isEmpty()){
                        data.setAgeEstimate(tokens[3]);
                    }else {
                        data.setAgeEstimate("null");
                    }

                    if(!tokens[4].isEmpty()){
                        data.setGender(tokens[4]);
                    }else {
                        data.setGender("null");
                    }

                    if(!tokens[7].isEmpty()){
                        data.setState(tokens[7]);
                    }else {
                        data.setState("null");
                    }

                    if(!tokens[8].isEmpty()){
                        data.setStatus(tokens[8]);
                    }else {
                        data.setStatus("null");
                    }



                    //Store data in list
                    sampleDataList.add(data);

                    insert(data); //
                }




            }

        } catch (IOException e) {
            Log.w(TAG, "readDataFormCSV: Error in reading file : " + line, e);
            e.printStackTrace();
        }

    }

    //--------------------------------------- ----------------------------------------------------------
    // for Contact Details api

    // Setting up Retrofit
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.rootnet.in/covid19-in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    // for getting contact details from Api.
    public List<ContactDetails> getContactDetailsFromApi(){
    ContactDetailsApi contactDetailsApi = retrofit.create(ContactDetailsApi.class);
        Log.d(TAG, "getContactDetailsFromApi: Called...");
        final List<ContactDetails>[] details = new List[]{new ArrayList<>()};
        Call<ContactDetailResponses> call = contactDetailsApi.getContactDetails();

        call.enqueue(new Callback<ContactDetailResponses>() {
            @Override
            public void onResponse(Call<ContactDetailResponses> call, Response<ContactDetailResponses> response) {

                if(!response.isSuccessful()){
                    Log.d(TAG, "onResponse: unsuccessful response.. " + response.code() );
                    return;
                }

                ContactDetailResponses responsesContact = response.body();

                details[0] = responsesContact.getData().getContacts().getRegional();

                Log.d(TAG, "onResponse: DETAILS Resposnes data from API " + details[0].size());

                saveContactDetails(details[0]);
                Log.d(TAG, "onResponse: Complete");
            }

            @Override
            public void onFailure(Call<ContactDetailResponses> call, Throwable t) {
                Log.d(TAG, "onFailure: failed " + t.getMessage());
            }
        });

        Log.d(TAG, "getContactDetailsFromApi: Completed..");


        Log.d(TAG, "getContactDetailsFromApi: size of details =" + details[0].size());
        return details[0];
    }

    // for saving the values in the Database

    public void saveContactDetails(List<ContactDetails> details){

        Log.d(TAG, "saveContactDetails: Called..");

        Log.d(TAG, "saveContactDetails: Details for saving : "  + details.size());
        for (ContactDetails d: details) {

            ContactEntity contactEntity = new ContactEntity();

            contactEntity.setStateName(d.getLoc());
            contactEntity.setHelpLine(d.getNumber());

           new InsertInContactTable(cantactDetailesDao).execute(contactEntity);
        }

        Log.d(TAG, "saveContactDetails: Completed");
    }

    private class InsertInContactTable extends AsyncTask<ContactEntity,Void, Void>{
        private static final String TAG = "InsertInContactTable";
        CantactDetailesDao cantactDetailesDao;

        public InsertInContactTable(CantactDetailesDao cantactDetailesDao) {
            Log.d(TAG, "InsertInContactTable: Called...");
            this.cantactDetailesDao = cantactDetailesDao;
        }

        @Override
        protected Void doInBackground(ContactEntity... contactEntities) {
            Log.d(TAG, "doInBackground: called... InsertInContactTable..");
            cantactDetailesDao.insert(contactEntities[0]);
            return null;
        }
    }


    /*==============================================================================================*/

    // for Hospital api

    public List<HospitalRegionalData> getHospitalDataFromApi() throws IOException {

        final ListStorage storage = ListStorage.getListInstance();

        HospitalApi hospitalApi = retrofit.create(HospitalApi.class);

        final List<HospitalRegionalData>[] data = new List[]{new ArrayList<>()};
        Call<HospitalDataResponse> call1 = hospitalApi.getHospitalData();

        call1.enqueue(new Callback<HospitalDataResponse>() {
            @Override
            public void onResponse(Call<HospitalDataResponse> call, Response<HospitalDataResponse> response) {
                if(!response.isSuccessful()){
                    Log.d(TAG, "onResponse: unsuccessful response.. " + response.code() );

                    return;
                }

                data[0] = response.body().getData().getRegionalDataList();

                Log.d(TAG, "onResponse: size of data[0] = " + data[0].size());
                storage.setHospitalList(data[0]);

                Log.d(TAG, "onResponse: storage list " + storage.getHospitalList().size());

            }

            @Override
            public void onFailure(Call<HospitalDataResponse> call, Throwable t) {

                Log.d(TAG, "onFailure: failed " + t.getMessage());

            }
        });

        List<HospitalRegionalData> newData  = new ArrayList<>();





        Log.d(TAG, "getHospitalDataFromApi: size of data[0] = " + data[0].size());


        Log.d(TAG, "getHospitalDataFromApi: new Data : " + newData.size());
        return newData;
    }


    /*===================================================================================*/

    // for College Api

    public void  getCollegeDataFromApi(){
        MedicalCollegeApi medicalCollegeApi = retrofit.create(MedicalCollegeApi.class);

        Call<MedicalCollegesResponse> call = medicalCollegeApi.getCollegeData();

        call.enqueue(new Callback<MedicalCollegesResponse>() {
            @Override
            public void onResponse(Call<MedicalCollegesResponse> call, Response<MedicalCollegesResponse> response) {

                if(!response.isSuccessful()){
                    Log.d(TAG, "onResponse: unsuccessful response.. " + response.code() );

                    return;
                }


                List<MedicalCollegesObject> list = response.body().getData().getCollegesList();

                ListStorage storage = ListStorage.getListInstance();

                storage.setCollegeList(list);

            }

            @Override
            public void onFailure(Call<MedicalCollegesResponse> call, Throwable t) {

                Log.d(TAG, "onFailure: failed " + t.getMessage());

            }
        });
    }



    /*=========================================================================================*/


    public void getHelplineDateFromApi(){
        ContactDetailsApi contactDetailsApi = retrofit.create(ContactDetailsApi.class);

        Call<ContactDetailResponses> call = contactDetailsApi.getContactDetails();

        call.enqueue(new Callback<ContactDetailResponses>() {
            @Override
            public void onResponse(Call<ContactDetailResponses> call, Response<ContactDetailResponses> response) {

                if(!response.isSuccessful()){
                    Log.d(TAG, "onResponse: unsuccessful response.. " + response.code() );
                    return;
                }

                ContactDetailResponses responsesContact = response.body();

                List<ContactDetails> details = responsesContact.getData().getContacts().getRegional();

                ListStorage storage = ListStorage.getListInstance();
                storage.setHelplineList(details);
                Log.d(TAG, "onResponse: Complete");
            }

            @Override
            public void onFailure(Call<ContactDetailResponses> call, Throwable t) {
                Log.d(TAG, "onFailure: failed " + t.getMessage());

            }
        });
    }


    /*====================================================================================*/

    public void getNotificationData(){
        NotificationApi notificationApi = retrofit.create(NotificationApi.class);

        Call<NotificationDetailsResponses> call1 = notificationApi.getNotificationResponse();

        call1.enqueue(new Callback<NotificationDetailsResponses>() {
            @Override
            public void onResponse(Call<NotificationDetailsResponses> call, Response<NotificationDetailsResponses> response) {
                if(!response.isSuccessful()){
                    Log.d(TAG, "onResponse: unsuccessful response.. " + response.code() );

                    return;
                }

                List<NotificationObject> data = response.body().getData().getNotifications();

                ListStorage storage = ListStorage.getListInstance();
                storage.setNotificationObjectList(data);


            }

            @Override
            public void onFailure(Call<NotificationDetailsResponses> call, Throwable t) {

                Log.d(TAG, "onFailure: failed " + t.getMessage());


            }
        });
    }



    public void getGraphData(String state , String gender, int upper , int lower){

        boolean stateBool = false;
        boolean genderBool = false;
        boolean ageBool = false;

        ListStorage storage = ListStorage.getListInstance();

        List<SampleData> data  = new ArrayList<>();

        /*if(state.equals("Select State") && gender.equals("Select Gender") && upper == 0 && lower ==0){
//            storage.setGraphData(storage.getDeceasedList());
            return;
        }*/


        Log.d(TAG, "getGraphData: Gender ==== " + gender);

        if(!state.equals("Select State") ){
            stateBool = true;
        }
        if(!gender.equals("Select Gender")){
            genderBool  = true;
        }
        if(upper != 0 && lower !=0){
            ageBool = true;
        }


        Log.d(TAG, "getGraphData: state = " +stateBool +" | gender =" + genderBool +" | ageBool = " + ageBool );

        if(stateBool && genderBool && ageBool){
            for (SampleData d: storage.getDeceasedList()) {
                Log.d(TAG, "getGraphData: called");


                int age = Integer.parseInt(d.getAgeEstimate());
                if (d.getGender().equals(gender) &&
                        d.getState().equals(state)
                        && age < upper && age > lower) {

                    data.add(d);
                }
            }
            storage.setFilter_info("Filter :: " + "State : " + state + ", Gender : " +
                    gender +", Age Above : " + lower + ", Age Below : " + upper );

        }else if(stateBool && genderBool){
            for (SampleData d: storage.getDeceasedList()) {
                Log.d(TAG, "getGraphData: called");



                if (d.getGender().equals(gender) &&
                        d.getState().equals(state)) {
                    data.add(d);
                }
            }

            storage.setFilter_info("Filter :: " + "State : " + state + ", Gender : " + gender );
        }
        else if(stateBool && ageBool){
            for (SampleData d: storage.getDeceasedList()) {
                Log.d(TAG, "getGraphData: called");


                int age = Integer.parseInt(d.getAgeEstimate());
                if (d.getState().equals(state)
                        && age < upper && age > lower) {

                    data.add(d);
                }
            }

            storage.setFilter_info("Filter :: " + "State : " + state
                   + ", Age Above : " + lower + ", Age Below : " + upper );
        }
        else if(genderBool && ageBool){
            for (SampleData d: storage.getDeceasedList()) {
                Log.d(TAG, "getGraphData: called");
                int age = Integer.parseInt(d.getAgeEstimate());
                if (d.getGender().equals(gender)
                        && age < upper && age > lower) {
                    data.add(d);
                }
            }

            storage.setFilter_info("Filter :: " +  "Gender : " +
                    gender +", Age Above : " + lower + ", Age Below : " + upper );
        }
        else if(stateBool ){
            for (SampleData d: storage.getDeceasedList()) {
                Log.d(TAG, "getGraphData: called");
                if (d.getState().equals(state)) {

                    data.add(d);
                }
            }
            storage.setFilter_info("Filter :: " + "State : " + state);
        }
        else if(ageBool){
            for (SampleData d: storage.getDeceasedList()) {
                Log.d(TAG, "getGraphData: called");


                int age = Integer.parseInt(d.getAgeEstimate());
                if (age < upper && age > lower) {

                    data.add(d);
                }
            }

            storage.setFilter_info("Filter :: " + " Age Above : " + lower + ", Age Below : " + upper );
        }
        else if(genderBool){
            for (SampleData d: storage.getDeceasedList()) {
                Log.d(TAG, "getGraphData: called");

                if (d.getGender().equals(gender) ){

                    data.add(d);
                }
            }

            storage.setFilter_info("Filter :: " + "Gender : " +gender  );
        }

        else if(!genderBool && !stateBool && !ageBool){
            for(SampleData d : storage.getDeceasedList()){
                data.add(d);
            }

            storage.setFilter_info("Filter :: None"  );
        }





        storage.setGraphData(data);
    }

}
