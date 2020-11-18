package com.ct7liang.weight.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

@Entity
public class Weight implements Serializable {

    private static final long serialVersionUID = 202011181610L;

    @Id(autoincrement = true)
    private Long id;

    private float am;
    private float pm;
    private String year;
    private String month;
    private String day;
    private long time;
    @Generated(hash = 305274258)
    public Weight(Long id, float am, float pm, String year, String month,
            String day, long time) {
        this.id = id;
        this.am = am;
        this.pm = pm;
        this.year = year;
        this.month = month;
        this.day = day;
        this.time = time;
    }
    @Generated(hash = 1956860650)
    public Weight() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public float getAm() {
        return this.am;
    }
    public void setAm(float am) {
        this.am = am;
    }
    public float getPm() {
        return this.pm;
    }
    public void setPm(float pm) {
        this.pm = pm;
    }
    public String getYear() {
        return this.year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public String getMonth() {
        return this.month;
    }
    public void setMonth(String month) {
        this.month = month;
    }
    public String getDay() {
        return this.day;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public long getTime() {
        return this.time;
    }
    public void setTime(long time) {
        this.time = time;
    }
}
