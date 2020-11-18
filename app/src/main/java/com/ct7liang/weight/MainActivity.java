package com.ct7liang.weight;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.ct7liang.translatestatusbar.TranslateStatusBar;
import com.ct7liang.weight.bean.Weight;
import com.ct7liang.weight.utils.SpUtils;
import com.ct7liang.weight.utils.TimeUtils;

import java.util.Locale;

import greendao.ct7liang.weight.WeightDaoUtils;

public class MainActivity extends Activity {

    private TextView tvTitle;
    private TextView tvTarget;
    private TextView tvAm;
    private TextView tvAmDis;
    private TextView tvPm;
    private TextView tvPmDis;
    private float targetWeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //透明状态栏
        TranslateStatusBar.work(this, findViewById(R.id.top_view));

        tvTitle = findViewById(R.id.title);
        tvTarget = findViewById(R.id.target);
        tvAm = findViewById(R.id.am);
        tvAmDis = findViewById(R.id.am_dis);
        tvPm = findViewById(R.id.pm);
        tvPmDis = findViewById(R.id.pm_dis);
    }

    @Override
    protected void onStart() {
        super.onStart();
        tvTitle.setText(TimeUtils.getCurrentDateStr());
        targetWeight = SpUtils.getTargetWeight();
        tvTarget.setText(String.valueOf(targetWeight));
        setDataToView();
    }

    /**
     * 查询数据,并设置数据到界面上
     */
    public void setDataToView(){
        int[] currentDateArr = TimeUtils.getCurrentDateArr();
        Weight weight = WeightDaoUtils.getWeightByDate(currentDateArr[0], currentDateArr[1], currentDateArr[2]);
        if(weight != null){
            float am = weight.getAm();
            tvAm.setText(am <= 0 ? "--" : String.format(Locale.CHINA,"%.2fkg", am));
            tvAmDis.setText(am <= 0 ? "--" : String.format(Locale.CHINA,"%.2fkg", am - targetWeight));
            float pm = weight.getPm();
            tvPm.setText(pm <= 0 ? "--" : String.format(Locale.CHINA,"%.2fkg", pm));
            tvPmDis.setText(pm <= 0 ? "--" : String.format(Locale.CHINA,"%.2fkg", pm - targetWeight));
        }else{
            tvAm.setText("--");
            tvAmDis.setText("--");
            tvPm.setText("--");
            tvPmDis.setText("--");
        }
    }

    /**
     * 进入日历页面
     * @param view View
     */
    public void navigatorToCalendar(View view){
        startActivity(new Intent(this, CalendarActivity.class));
    }

    /**
     * 进入图表页面
     * @param view View
     */
    public void navigatorToChart(View view){
        startActivity(new Intent(this, ChartActivity.class));
    }

    /**
     * 进入目标体重页面
     * @param view view
     */
    public void navigateToTarget(View view){
        startActivity(new Intent(this, TargetInputActivity.class));
    }
}
