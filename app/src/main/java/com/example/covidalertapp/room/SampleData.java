package com.example.covidalertapp.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


/*
* This is an Entity class
* this is the object which '
* the SQLite Database will
* store.
*
*  */


@Entity(tableName = "sample_testing_table")
public class SampleData {



    // this Member variables will be the column in database..
    @PrimaryKey(autoGenerate = true) // This will make it a primary key through which we can get a particular column.
    private int id;
    private String reportedOn;
    private String ageEstimate;
    private String gender;
    private String state;
    private String status;

    //Constructor for this class..
    public SampleData(){

    }

    public SampleData(String reportedOn, String ageEstimate, String gender, String state, String status) {
        this.reportedOn = reportedOn;
        this.ageEstimate = ageEstimate;
        this.gender = gender;
        this.state = state;
        this.status = status;
    }

    // Setter Method for id only because it is not included in constructor (Primary key autoincrement)
    public void setId(int id) {
        this.id = id;
    }


    // Getter methods for the variables... since they are private
    public int getId() {
        return id;
    }

    public String getReportedOn() {
        return reportedOn;
    }

    public String getAgeEstimate() {
        return ageEstimate;
    }

    public String getGender() {
        return gender;
    }

    public String getState() {
        return state;
    }

    public String getStatus() {
        return status;
    }

    // setters for all the variables

    public void setReportedOn(String reportedOn) {
        this.reportedOn = reportedOn;
    }

    public void setAgeEstimate(String ageEstimate) {
        this.ageEstimate = ageEstimate;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
