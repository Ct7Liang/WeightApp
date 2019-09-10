package com.ct7liang.weight.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Weight {

    @Id(autoincrement = true)
    private Long id;

    private float weight;
    private long time;
    private String date;
    private String year;
    private String month;
    private String day;
    private String hour;
    private String minute;
    private String second;
    @Generated(hash = 1326852731)
    public Weight(Long id, float weight, long time, String date, String year,
            String month, String day, String hour, String minute, String second) {
        this.id = id;
        this.weight = weight;
        this.time = time;
        this.date = date;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
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
    public float getWeight() {
        return this.weight;
    }
    public void setWeight(float weight) {
        this.weight = weight;
    }
    public long getTime() {
        return this.time;
    }
    public void setTime(long time) {
        this.time = time;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
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
    public String getHour() {
        return this.hour;
    }
    public void setHour(String hour) {
        this.hour = hour;
    }
    public String getMinute() {
        return this.minute;
    }
    public void setMinute(String minute) {
        this.minute = minute;
    }
    public String getSecond() {
        return this.second;
    }
    public void setSecond(String second) {
        this.second = second;
    }


}
