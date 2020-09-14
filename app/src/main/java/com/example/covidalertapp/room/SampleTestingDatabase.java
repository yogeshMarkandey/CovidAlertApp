package com.example.covidalertapp.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
/*
* This class must be an Abstract class
* it should extend RoomDatabase
*
* */

@Database(entities = {SampleData.class, ContactEntity.class}, version = 2)
public abstract class SampleTestingDatabase extends RoomDatabase {

    /*
    * This is NoteDatabase instance.
    * We make this variable to make this class to a singleton.
    *
    * Singleton - it meanse we cant create multiple instances
    *             of this class(DATABASE) and we use same instance
    *             everywhere in the App which we can excess using this
    *             static variable.
    *
    * */
    private static SampleTestingDatabase instance;


    /*
    * We will use this noteDao method to get the access of Database
    * operation methods which we created in the NoteDao interface
    * and we will do it in the repository class of this app architecture.*/
    public abstract SampleDao sampleDao();
    public abstract CantactDetailesDao cantactDetailesDao();

    /*
    * In this method we will create only instance of the database
    * which will be used in different part of app because we can
    * call it from outside get the instance.
    *
    * synchronized -- means only one thread can access this method
    *                 at any given time.
    *                 This way we avoid creation of two different
    *                 instance of the class when two different thread
    *                 tries to excess the class at same time.
    *
    *
    * This class is abstract so we cannot use new keyword.
    * So we use Room.databaseBuilder to make the instance.
    *
    * .fallbackToDestructiveMigration() --
    *           This means when version number is changed,
    * the previous database will be deleted and a new update
    * database will be created.
    *
    *   If not done it will give IllLegalStateException.
    *
    * */
    public static synchronized SampleTestingDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    SampleTestingDatabase.class, "covid_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }


    /*
    * The following method is for population the database on onCreate
    * in this way database will have given data when it is created.
    *
    * Inside this we will override onCreate Method.
    *
    * */
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);


        }
    };

    //AsyncTask for population the database..
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{

        private SampleDao sampleDao;

        private PopulateDbAsyncTask(SampleTestingDatabase db){
            this.sampleDao = db.sampleDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
           // TODO: POULATE DATABASE HERE


            return null;
        }
    }






}
