package com.example.hp.ajcegosafe;

/**
 * Created by hp on 18-11-2017.
 */

public class OutpassHistoryAdmin {
    String date;
    String purpose;
    String status;
    String admno;
    String count;
    Integer oid;

    public OutpassHistoryAdmin(String Date, String Pur, String Stus, String Adno, String Count, Integer Oid) {
        date = Date;
        purpose = Pur;
        status = Stus;
        admno = Adno;
        count = Count;
        oid = Oid;
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

    public String getAdmno() {
        return admno;
    }

    public void setAdmno(String admno) {
        this.admno = admno;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }
}
