package com.example.asafl.calci;

/**
 * Created by Lincoln on 15/01/16.
 */
public class BirthDay {
    private String name, fname, date;

    public BirthDay() {
    }

    public BirthDay(String name, String fname, String date) {
        this.name = name;
        this.fname = fname;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }
}
