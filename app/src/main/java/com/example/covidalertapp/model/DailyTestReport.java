package com.example.covidalertapp.model;

public class DailyTestReport {

    /*
     "day": "2020-03-12",
      "totalSamplesTested": 6500,
      "totalIndividualsTested": 5900,
      "totalPositiveCases": 78,
      "source": "Press_Release_ICMR_13March2020.pdf"*/


    private String day;
    private String totalSamplesTested;
    private String totalIndividualsTested;
    private String totalPositiveCases;
    private String source;

    public String getDay() {
        return day;
    }

    public String getTotalSamplesTested() {
        return totalSamplesTested;
    }

    public String getTotalIndividualsTested() {
        return totalIndividualsTested;
    }

    public String getTotalPositiveCases() {
        return totalPositiveCases;
    }

    public String getSource() {
        return source;
    }
}
