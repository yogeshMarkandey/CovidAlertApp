package com.example.covidalertapp.model;

public class ConfirmCaseSummaryObject {

    /* {
        "total": 47,
        "confirmedCasesIndian": 31,
        "confirmedCasesForeign": 16,
        "discharged": 0,
        "deaths": 0,
        "confirmedButLocationUnidentified": 0
      },*/


    private String total;
    private String confirmedCasesIndian;
    private String confirmedCasesForeign;
    private String discharged;
    private String deaths;
    private String confirmedButLocationUnidentified;

    public String getTotal() {
        return total;
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

    public String getConfirmedButLocationUnidentified() {
        return confirmedButLocationUnidentified;
    }
}
