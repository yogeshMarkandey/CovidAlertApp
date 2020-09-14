package com.example.covidalertapp.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CantactDetailesDao {

    @Insert
    void insert(ContactEntity contactEntity);
    @Query("SELECT * FROM contact_details_table")
    List<ContactEntity> getAllContact();

    @Query("DELETE FROM contact_details_table")
    void deleteAllData();



}
