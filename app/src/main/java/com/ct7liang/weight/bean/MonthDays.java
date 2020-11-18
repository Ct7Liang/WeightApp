package com.ct7liang.weight.bean;

import java.util.List;

public class MonthDays {

    public int weekIndex = 1;

    public int currentIndex = 1;

    public List<Day> list;

    public List<String> haveDataDays;


    public static class Day{

        public Day(int day, int state){
            this.day = day;
            this.state = state;
        }

        public int day;
        public int state; //0: 没有 1: 不全 2: 完整
    }
}



