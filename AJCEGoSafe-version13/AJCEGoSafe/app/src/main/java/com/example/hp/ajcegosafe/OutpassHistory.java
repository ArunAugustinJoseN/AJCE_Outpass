package com.example.hp.ajcegosafe;

/**
 * Created by hp on 28-09-2017.
 */

public class OutpassHistory {
    String date;
    String purpose;
    String status;
    String count;

    public OutpassHistory(String Date, String Pur, String Stus, String Count){
        date = Date;
        purpose = Pur;
        status = Stus;
        count = Count;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
