package com.ct7liang.weight;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.ct7liang.tangyuan.utils.ScreenUtil;
import com.ct7liang.tangyuan.utils.SpUtils;
import com.ct7liang.tangyuan.utils.ToastUtils;
import com.ct7liang.weight.base.BaseActivity;

public class SettingActivity extends BaseActivity {

    private EditText editMin;
    private EditText editMax;
    private SpUtils spUtils;

    @Override
    public int setLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void initSurface() {
        initStatusBar();
        spUtils = SpUtils.start();
        editMin = findViewById(R.id.edit_min);
        editMax = findViewById(R.id.edit_max);
        editMin.setText(spUtils.getFloat("minWeight", 45)+"");
        editMax.setText(spUtils.getFloat("maxWeight", 55)+"");
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn:
                setting();
                break;
        }
    }

    @Override
    protected void setStatusBar() {
        findViewById(R.id.title_bar).setPadding(0, ScreenUtil.getUtils().getStatusHeight(this),0,0);
    }

    private void setting(){
        String min = editMin.getText().toString().trim();
        String max = editMax.getText().toString().trim();
        if (TextUtils.isEmpty(min) || TextUtils.isEmpty(max)){
            ToastUtils.showStatic(this, "内容不能为空");
            return;
        }
        float i = Float.parseFloat(max);
        float j = Float.parseFloat(min);
        if (i > j){
            spUtils.saveFloat("minWeight", j);
            spUtils.saveFloat("maxWeight", i);
            ToastUtils.showStatic(this, "设置成功");
        }else{
            ToastUtils.showStatic(this, "上限不能小于或等于下限");
        }
    }
}