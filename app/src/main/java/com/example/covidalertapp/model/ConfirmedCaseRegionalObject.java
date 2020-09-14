package com.example.covidalertapp.model;

public class ConfirmedCaseRegionalObject {

    /*"loc": "Delhi",
          "confirmedCasesIndian": 4,
          "confirmedCasesForeign": 0,
          "discharged": 0,
          "deaths": 0,
          "totalConfirmed": 4*/


    private String loc;
    private String confirmedCasesIndian;
    private String confirmedCasesForeign;
    private String discharged;
    private String deaths;
    private String totalConfirmed;


    public String getLoc() {
        return loc;
    }

    public String getConfirmedCasesIndian() {
        return confirmedCasesIndian;
    }

    public String getConfirmedCasesForeign() {
        return confirmedCasesForeign;
    }

    public String getDischarged() {
        return discharged;
    }

    public String getDeaths() {
        return deaths;
    }

    public String getTotalConfirmed() {
        return totalConfirmed;
    }
}
