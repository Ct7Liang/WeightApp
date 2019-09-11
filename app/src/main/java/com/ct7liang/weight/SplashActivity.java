package com.ct7liang.weight;

import android.content.Intent;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import com.ct7liang.weight.base.BaseActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends BaseActivity {

    private Disposable subscribe;
    private TextView tvTime;

    @Override
    public int setLayout() {
        setFullScreen();
        return R.layout.activity_splash;
    }

    @Override
    public void initSurface() {
        tvTime = findViewById(R.id.tv_time);
        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (subscribe!=null){
                    subscribe.dispose();
                    startActivity(new Intent(mAct, MainActivity.class));
                    finish();
                }
            }
        });
        subscribe = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 3; i > -1; i--) {
                    emitter.onNext(i);
                    SystemClock.sleep(1000);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                if (integer == 0){
                    startActivity(new Intent(mAct, MainActivity.class));
                    finish();
                }else{
                    tvTime.setText("跳过 "+integer);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscribe != null){
            subscribe.dispose();
        }
    }
}
