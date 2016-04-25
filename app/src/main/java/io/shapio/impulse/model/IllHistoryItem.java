package io.shapio.impulse.model;

/**
 * Created by TheOSka on 21/4/2016.
 */
public class IllHistoryItem {
    String date;
    String diseaseName;
    String diseaseDesc;
    String illID;

    public void setIllID(String illID) {
        this.illID = illID;
    }

    public String getIllID() {
        return illID;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDiseaseDesc(String diseaseDesc) {
        this.diseaseDesc = diseaseDesc;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getDate() {
        return date;
    }

    public String getDiseaseDesc() {
        return diseaseDesc;
    }

    public String getDiseaseName() {
        return diseaseName;
    }
}
