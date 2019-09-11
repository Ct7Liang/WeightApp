package com.ct7liang.weight.base;

import android.app.Application;

import com.ct7liang.tangyuan.utils.LogUtils;
import com.ct7liang.tangyuan.utils.SpUtils;
import com.tencent.bugly.crashreport.CrashReport;

import greendao.ct7liang.weight.GreenDaoHelper;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SpUtils.init(this);

        LogUtils.setTag("Weight"); //设置日志标签
        LogUtils.setLogEnable(true); //后台日志 默认为true

        GreenDaoHelper.init(this, "MyWeights");

        CrashReport.initCrashReport(getApplicationContext(), "68a67ff35a", false);
    }
}
