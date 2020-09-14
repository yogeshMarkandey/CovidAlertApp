package com.example.covidalertapp.model;

import java.util.List;

public class DayWiseConfirmedCases {

    private String date ;

    private ConfirmCaseSummaryObject summaryObject;

    private List<ConfirmedCaseRegionalObject> listOfRegionalStats;

    public String getDate() {
        return date;
    }

    public ConfirmCaseSummaryObject getSummaryObject() {
        return summaryObject;
    }

    public List<ConfirmedCaseRegionalObject> getListOfRegionalStats() {
        return listOfRegionalStats;
    }
}
