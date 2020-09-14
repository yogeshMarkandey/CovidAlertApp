package com.example.covidalertapp.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact_details_table")
public class ContactEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String stateName;

    private String helpLine;


    public ContactEntity(String stateName, String helpLine) {
        this.stateName = stateName;
        this.helpLine = helpLine;
    }

    public ContactEntity (){

    }

    public void setId(int id) {
        this.id = id;
    }

    // getters
    public int getId() {
        return id;
    }

    public String getStateName() {
        return stateName;
    }

    public String getHelpLine() {
        return helpLine;
    }

    //setters

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public void setHelpLine(String helpLine) {
        this.helpLine = helpLine;
    }
}
