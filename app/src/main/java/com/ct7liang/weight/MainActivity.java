package com.ct7liang.weight;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.ct7liang.tangyuan.recyclerview.OnItemLongClickListener;
import com.ct7liang.tangyuan.utils.ScreenUtil;
import com.ct7liang.tangyuan.utils.ToastUtils;
import com.ct7liang.weight.adapter.WeightHorizontalAdapter;
import com.ct7liang.weight.base.BaseActivity;
import com.ct7liang.weight.bean.Weight;
import com.ct7liang.weight.utils.WindowUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import greendao.ct7liang.weight.GreenDaoHelper;
import greendao.ct7liang.weight.WeightDao;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity implements OnItemLongClickListener {

    private WeightDao weightDao;
    private List<Weight> list;
    private WeightHorizontalAdapter weightAdapter;

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initSurface() {
        initStatusBar();
        weightDao = GreenDaoHelper.getDao().getWeightDao();
        list = weightDao.loadAll();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        weightAdapter = new WeightHorizontalAdapter(this, list);
        weightAdapter.setOnItemLongClickListener(this);
        recyclerView.setAdapter(weightAdapter);

        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.iv_setting).setOnClickListener(this);
        findViewById(R.id.iv_add).setOnClickListener(this);

        RxPermissions rxPermissions = new RxPermissions(this);
        Disposable subscribe = rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_PHONE_STATE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                exitApp();
                break;
            case R.id.iv_setting:
                Intent i = new Intent(mAct, SettingActivity.class);
                startActivityForResult(i, 61);
                break;
            case R.id.iv_add:
                showWindow();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        exitApp();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 61){
            weightAdapter.refreshData();
            weightAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void setStatusBar() {
        findViewById(R.id.title_bar).setPadding(0, ScreenUtil.getUtils().getStatusHeight(this),0,0);
    }

    @Override
    public void onLongClick(View view, final int position) {
        new WindowUtils().create(this, R.layout.window_delete_weight, 0, 0, new WindowUtils.OnContentViewInitListener() {
            @Override
            public void onContentViewInited(final Dialog dialog, View contentView) {
                contentView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                contentView.findViewById(R.id.commit).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Weight weight = list.get(position);
                        weightDao.delete(weight);
                        list.remove(weight);
                        weightAdapter.notifyDataSetChanged();
                        ToastUtils.showStatic(MainActivity.this, "记录删除成功");
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    private void showWindow(){
        new WindowUtils().create(this, R.layout.window_add_weight, 0, 0, new WindowUtils.OnContentViewInitListener() {
            @Override
            public void onContentViewInited(final Dialog dialog, View contentView) {
                final EditText editText = contentView.findViewById(R.id.edit_weight);
                contentView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                contentView.findViewById(R.id.commit).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String trim = editText.getText().toString().trim();
                        if (TextUtils.isEmpty(trim)){
                            return;
                        }
                        Date date = new Date();
                        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.CHINA);
                        String[] split = simpleDateFormat2.format(date).split("-");
                        long time = date.getTime();
                        Weight weight = new Weight(null, Float.parseFloat(trim), time, simpleDateFormat1.format(date), split[0], split[1], split[2], split[3], split[4], split[5]);
                        long insertId = weightDao.insert(weight);
                        weight.setId(insertId);
                        list.add(weight);
                        weightAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                        ToastUtils.showStatic(MainActivity.this, "数据录入成功");
                    }
                });
            }
        });
    }
}
