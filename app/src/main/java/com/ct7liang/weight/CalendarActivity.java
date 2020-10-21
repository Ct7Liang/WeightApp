package com.ct7liang.weight;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.ct7liang.translatestatusbar.TranslateStatusBar;
import com.ct7liang.weight.adapter.CalendarAdapter;
import com.ct7liang.weight.bean.MonthDays;

import static com.ct7liang.weight.utils.TimeUtils.getCurrentDateArr;
import static com.ct7liang.weight.utils.TimeUtils.getMonthDays;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_selectTime;
    private int currentYear;
    private int currentMonth;
    private int currentDay;
    private int selectYear;
    private int selectMonth;
    private int selectDay;
    private MonthDays monthDays = new MonthDays();
    private CalendarAdapter calendarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_calendar);

        //透明状态栏
        TranslateStatusBar.work(this, findViewById(R.id.top_view));

        tv_selectTime = findViewById(R.id.selectTime);
        int[] currentDateArr = getCurrentDateArr();
        currentYear = currentDateArr[0];
        currentMonth = currentDateArr[1];
        currentDay = currentDateArr[2];
        selectYear = currentDateArr[0];
        selectMonth = currentDateArr[1];
        selectDay = currentDateArr[2];
        tv_selectTime.setText(selectYear + "年" + selectMonth + "月" + selectDay + "日");
        monthDays = getMonthDays(monthDays, selectYear, selectMonth, selectDay);
        calendarAdapter = new CalendarAdapter(this, monthDays);
        calendarAdapter.setOnDayClickListener(new CalendarAdapter.OnDayClickListener() {
            @Override
            public void onClick(View v, int position, MonthDays.Day day) {
                selectDay = day.day;
                tv_selectTime.setText(selectYear + "年" + selectMonth + "月" + selectDay + "日");
            }
        });
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 7, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(calendarAdapter);

        findViewById(R.id.pre).setOnClickListener(this);
        findViewById(R.id.next).setOnClickListener(this);
        findViewById(R.id.selectTime).setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消透明状态栏
        TranslateStatusBar.cancel(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pre:
                if(selectMonth > 1){
                    selectMonth  = selectMonth - 1;
                }else{
                    selectMonth = 12;
                    selectYear = selectYear - 1;
                }
                tv_selectTime.setText(selectYear + "年" + selectMonth + "月" + selectDay + "日");
                monthDays = getMonthDays(monthDays, selectYear, selectMonth, selectDay);
                calendarAdapter.notifyDataSetChanged();
                break;
            case R.id.next:
                if(selectMonth < 12){
                    selectMonth  = selectMonth + 1;
                }else{
                    selectMonth = 1;
                    selectYear = selectYear + 1;
                }
                tv_selectTime.setText(selectYear + "年" + selectMonth + "月" + selectDay + "日");
                monthDays = getMonthDays(monthDays, selectYear, selectMonth, selectDay);
                calendarAdapter.notifyDataSetChanged();
                break;
            case R.id.selectTime:
                tv_selectTime.setText(currentYear + "年" + currentMonth + "月" + currentDay + "日");
                selectYear = currentYear;
                selectMonth = currentMonth;
                selectDay = currentDay;
                monthDays = getMonthDays(monthDays, currentYear, currentMonth, currentDay);
                calendarAdapter.notifyDataSetChanged();
                break;
        }
    }
}