package com.ct7liang.weight;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.ct7liang.translatestatusbar.TranslateStatusBar;

import static com.ct7liang.weight.utils.TimeUtils.getCurrentDateStr;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //透明状态栏
        TranslateStatusBar.work(this, findViewById(R.id.top_view));

        ((TextView)findViewById(R.id.title)).setText(getCurrentDateStr());

        findViewById(R.id.go_calendar).setOnClickListener(this);
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
            case R.id.go_calendar:
                startActivity(new Intent(this, CalendarActivity.class));
                break;
        }
    }
}
