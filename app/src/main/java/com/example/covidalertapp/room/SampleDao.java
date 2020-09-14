package com.example.covidalertapp.room;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/*DAO should be either interface
* or abstract class because we don't
* provide method body.
*
* We just create it an and then
* annotate it and room will generate all
* necessary code for them.
*
* In this interface we wil create methods
* for all the operations we will do in the
* database.
*
*
* Room does not have already made annotation
* for all the database operations so in that
* case we use " @Query " annotation and
* pass the Query.
*
*
* LiveData<T> -- in this we can observe the data
* change in database and can notify various component
* */
@Dao
public interface SampleDao {
    @Insert
    void insert(SampleData sampleData);
    @Update
    void update(SampleData sampleData);
    @Delete
    void delete(SampleData sampleData);
    @Query("DELETE FROM sample_testing_table")
    void deleteAll();

    @Query("SELECT * FROM sample_testing_table ")
    List<SampleData> getAllNotes();

    @Query("SELECT * FROM sample_testing_table WHERE status == 'Deceased' AND ageEstimate != \"null\"")
    List<SampleData> getAllDeceasedPeople();
    @Query("SELECT * FROM sample_testing_table WHERE status == 'Recovered' AND ageEstimate != \"null\"")
    List<SampleData> getAllRecoveredPeople();
    @Query("SELECT * FROM sample_testing_table WHERE status == 'Hospitalized' AND ageEstimate != \"null\"")
    List<SampleData> getAllHospitalisedPeople();


}
