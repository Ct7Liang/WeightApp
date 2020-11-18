package com.ct7liang.weight;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ct7liang.translatestatusbar.TranslateStatusBar;
import com.ct7liang.weight.bean.Weight;

import java.util.Date;
import java.util.Locale;

import greendao.ct7liang.weight.WeightDaoUtils;

public class WeightInputActivity extends Activity {

    private TextView tvTitle;
    private EditText etAm;
    private EditText etPm;
    private Weight weight;
    private int year;
    private int month;
    private int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_weight_input);

        //透明状态栏
        TranslateStatusBar.work(this, findViewById(R.id.top_view));

        tvTitle = findViewById(R.id.title);
        etAm = findViewById(R.id.am);
        etPm = findViewById(R.id.pm);
    }

    @Override
    protected void onStart() {
        super.onStart();
        weight = (Weight) getIntent().getSerializableExtra("Weight");
        if(weight == null){
            //新增
            year = getIntent().getIntExtra("year", 0);
            month = getIntent().getIntExtra("month", 0);
            day = getIntent().getIntExtra("day", 0);
            tvTitle.setText(String.format(Locale.CHINA,"%d年%d月%d日", year, month, day));
        }else{
            //修改
            tvTitle.setText(String.format(Locale.CHINA,"%s年%s月%s日", weight.getYear(), weight.getMonth(), weight.getDay()));
            etAm.setText(String.valueOf(weight.getAm()));
            etPm.setText(String.valueOf(weight.getPm()));
        }
    }

    /**
     * 保存数据
     */
    public void save(View view){
        String amData = etAm.getText().toString().trim();
        String pmData = etPm.getText().toString().trim();
        amData = TextUtils.isEmpty(amData) ? "0" : amData;
        pmData = TextUtils.isEmpty(pmData) ? "0" : pmData;
        if(weight == null){
            //新增
            long time = new Date(year, month - 1, day).getTime();
            WeightDaoUtils.insertWeight(year, month, day, Float.parseFloat(amData), Float.parseFloat(pmData), time);
            Toast.makeText(this, "新增成功", Toast.LENGTH_SHORT).show();
        }else{
            //修改
            weight.setAm(Float.parseFloat(amData));
            weight.setPm(Float.parseFloat(pmData));
            WeightDaoUtils.updateWeight(weight);
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    /**
     * 返回
     * @param view View
     */
    public void navigatorBack(View view){
        finish();
    }
}