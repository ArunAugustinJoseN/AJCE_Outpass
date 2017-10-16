package com.example.hp.ajcegosafe;

/**
 * Created by hp on 16-10-2017.
 */

public class LogDetailsContent {
    String adno;
    String password;
    String count;

    public LogDetailsContent(String ADNO, String Password, String Count){
        adno = ADNO;
        password = Password;
        count = Count;
    }

    public String getAdno() {
        return adno;
    }

    public void setAdno(String adno) {
        this.adno = adno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
