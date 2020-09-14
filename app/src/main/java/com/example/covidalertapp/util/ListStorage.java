package com.example.covidalertapp.util;

import android.graphics.HardwareRenderer;

import com.example.covidalertapp.model.ContactDetails;
import com.example.covidalertapp.model.HospitalRegionalData;
import com.example.covidalertapp.model.MedicalCollegesObject;
import com.example.covidalertapp.model.NotificationObject;
import com.example.covidalertapp.room.SampleData;

import java.util.ArrayList;
import java.util.List;

public class ListStorage  {

    public static ListStorage instance  = null;

    private List<HospitalRegionalData> hospitalList = new ArrayList<>();
    private List<MedicalCollegesObject> collegeList = new ArrayList<>();
    private List<ContactDetails> helplineList = new ArrayList<>();
    private List<NotificationObject> notificationObjectList = new ArrayList<>();
    private List<SampleData> deceasedList = new ArrayList<>();
    private List<SampleData> recoveredList = new ArrayList<>();
    private List<SampleData> hospitalizedList = new ArrayList<>();
    private List<SampleData> graphData = new ArrayList<>();

    private String filter_info;

    private ListStorage(){

    }

    public static synchronized ListStorage getListInstance(){

        if (instance == null){
            instance = new ListStorage();
        }


        return instance;
    }


    public List<MedicalCollegesObject> getCollegeList() {
        return collegeList;
    }

    public void setCollegeList(List<MedicalCollegesObject> collegeList) {
        this.collegeList = collegeList;
    }

    public List<HospitalRegionalData> getHospitalList() {
        return hospitalList;
    }

    public void setHospitalList(List<HospitalRegionalData> hospitalList) {
        this.hospitalList = hospitalList;
    }

    public List<ContactDetails> getHelplineList() {
        return helplineList;
    }

    public void setHelplineList(List<ContactDetails> helplineList) {
        this.helplineList = helplineList;
    }

    public List<NotificationObject> getNotificationObjectList() {
        return notificationObjectList;
    }

    public void setNotificationObjectList(List<NotificationObject> notificationObjectList) {
        this.notificationObjectList = notificationObjectList;
    }

    public List<SampleData> getDeceasedList() {
        return deceasedList;
    }

    public void setDeceasedList(List<SampleData> deceasedList) {
        this.deceasedList = deceasedList;
    }

    public List<SampleData> getRecoveredList() {
        return recoveredList;
    }

    public void setRecoveredList(List<SampleData> recoveredList) {
        this.recoveredList = recoveredList;
    }

    public List<SampleData> getHospitalizedList() {
        return hospitalizedList;
    }

    public void setHospitalizedList(List<SampleData> hospitalizedList) {
        this.hospitalizedList = hospitalizedList;
    }

    public List<SampleData> getGraphData() {
        return graphData;
    }

    public void setGraphData(List<SampleData> graphData) {
        this.graphData = graphData;
    }

    public void setFilter_info(String filter_info) {
        this.filter_info = filter_info;
    }

    public String getFilter_info() {
        return filter_info;
    }
}
