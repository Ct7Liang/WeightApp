package com.ct7liang.weight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.ct7liang.translatestatusbar.TranslateStatusBar;
import com.ct7liang.weight.adapter.CalendarAdapter;
import com.ct7liang.weight.bean.MonthDays;
import com.ct7liang.weight.bean.Weight;
import com.ct7liang.weight.utils.TimeUtils;

import java.util.Locale;

import greendao.ct7liang.weight.WeightDaoUtils;

public class CalendarActivity extends AppCompatActivity implements CalendarAdapter.OnDayClickListener {

    private TextView tvSelectTime;
    private TextView tvAm;
    private TextView tvPm;
    private int currentYear;
    private int currentMonth;
    private int currentDay;
    private int selectYear;
    private int selectMonth;
    private int selectDay;
    private final MonthDays monthDays = new MonthDays();
    private CalendarAdapter calendarAdapter;
    private Weight weightObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_calendar);

        //透明状态栏
        TranslateStatusBar.work(this, findViewById(R.id.top_view));

        tvSelectTime = findViewById(R.id.selectTime);
        tvAm = findViewById(R.id.am);
        tvPm = findViewById(R.id.pm);

        calendarAdapter = new CalendarAdapter(this, monthDays);
        calendarAdapter.setOnDayClickListener(this);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 7, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(calendarAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //获取时间
        int[] currentDateArr = TimeUtils.getCurrentDateArr();
        currentYear = currentDateArr[0];
        currentMonth = currentDateArr[1];
        currentDay = currentDateArr[2];
        if(selectYear == 0 || selectMonth == 0 || selectDay == 0){
            selectYear = currentYear;
            selectMonth = currentMonth;
            selectDay = currentDay;
        }
        //设置时间
        tvSelectTime.setText(String.format(Locale.CHINA,"%d年%d月%d日", selectYear, selectMonth, selectDay));
        //获取有数据的天数
        monthDays.haveDataDays = WeightDaoUtils.getMonthWeight(selectYear, selectMonth);
        //设置日历
        TimeUtils.getMonthDays(monthDays, selectYear, selectMonth, selectDay);
        calendarAdapter.notifyDataSetChanged();
        setDataToView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消透明状态栏
        TranslateStatusBar.cancel(this);
    }

    /**
     * 查询数据,并设置数据到界面上
     */
    public void setDataToView(){
        weightObject = WeightDaoUtils.getWeightByDate(selectYear, selectMonth, selectDay);
        if(weightObject != null){
            float am = weightObject.getAm();
            tvAm.setText(am <= 0 ? "--" : String.format(Locale.CHINA,"%.2fkg", am));
            float pm = weightObject.getPm();
            tvPm.setText(pm <= 0 ? "--" : String.format(Locale.CHINA,"%.2fkg", pm));
        }else{
            tvAm.setText("--");
            tvPm.setText("--");
        }
    }

    /**
     * 新增或修改上午数据
     */
    public void changeData(View view){
        Intent i = new Intent(this, WeightInputActivity.class);
        if(weightObject == null){
            //新增
            i.putExtra("year", selectYear);
            i.putExtra("month", selectMonth);
            i.putExtra("day", selectDay);
        }else{
            //修改
            i.putExtra("Weight", weightObject);
        }
        startActivity(i);
    }

    /**
     * 回到今天
     */
    public void returnToday(View view){
        tvSelectTime.setText(String.format(Locale.CHINA,"%d年%d月%d日", currentYear, currentMonth, currentDay));
        selectYear = currentYear;
        selectMonth = currentMonth;
        selectDay = currentDay;
        TimeUtils.getMonthDays(monthDays, currentYear, currentMonth, currentDay);
        //获取有数据的天数
        monthDays.haveDataDays = WeightDaoUtils.getMonthWeight(selectYear, selectMonth);
        calendarAdapter.notifyDataSetChanged();

        setDataToView();
    }

    /**
     * 切换下一个月
     */
    public void nextMonth(View view){
        if(selectMonth < 12){
            selectMonth  = selectMonth + 1;
        }else{
            selectMonth = 1;
            selectYear = selectYear + 1;
        }
        TimeUtils.getMonthDays(monthDays, selectYear, selectMonth, selectDay);
        //获取有数据的天数
        monthDays.haveDataDays = WeightDaoUtils.getMonthWeight(selectYear, selectMonth);
        calendarAdapter.notifyDataSetChanged();

        selectDay = calendarAdapter.getCurrentPosition() + 1;
        tvSelectTime.setText(String.format(Locale.CHINA,"%d年%d月%d日", selectYear, selectMonth, selectDay));
        setDataToView();
    }

    /**
     * 切换上一个月
     */
    public void preMonth(View view){
        if(selectMonth > 1){
            selectMonth  = selectMonth - 1;
        }else{
            selectMonth = 12;
            selectYear = selectYear - 1;
        }
        TimeUtils.getMonthDays(monthDays, selectYear, selectMonth, selectDay);
        //获取有数据的天数
        monthDays.haveDataDays = WeightDaoUtils.getMonthWeight(selectYear, selectMonth);
        calendarAdapter.notifyDataSetChanged();

        selectDay = calendarAdapter.getCurrentPosition() + 1;
        tvSelectTime.setText(String.format(Locale.CHINA,"%d年%d月%d日", selectYear, selectMonth, selectDay));
        setDataToView();
    }

    /**
     * 返回
     */
    public void navigatorBack(View view){
        finish();
    }

    /**
     * 日历中天的点击事件
     * @param v View
     * @param position int
     * @param day object
     */
    @Override
    public void onClick(View v, int position, MonthDays.Day day) {
        selectDay = day.day;
        tvSelectTime.setText(String.format(Locale.CHINA,"%d年%d月%d日", selectYear, selectMonth, selectDay));
        setDataToView();
    }
}