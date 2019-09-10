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

    @Override
    public int setLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void initSurface() {
        initStatusBar();
        editMin = findViewById(R.id.edit_min);
        editMax = findViewById(R.id.edit_max);
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
        int i = Integer.parseInt(max);
        int j = Integer.parseInt(min);
        if (i > j){
            SpUtils.start().saveFloat("minWeight", j);
            SpUtils.start().saveFloat("maxWeight", i);
        }else{
            ToastUtils.showStatic(this, "上限不能小于或等于下限");
        }
    }
}
