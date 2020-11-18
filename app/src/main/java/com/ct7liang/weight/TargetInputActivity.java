package com.ct7liang.weight;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.ct7liang.translatestatusbar.TranslateStatusBar;
import com.ct7liang.weight.utils.SpUtils;

public class TargetInputActivity extends Activity {

    private EditText etTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_target_input);

        //透明状态栏
        TranslateStatusBar.work(this, findViewById(R.id.top_view));

        etTarget = findViewById(R.id.target);
    }

    @Override
    protected void onStart() {
        super.onStart();
        etTarget.setText(String.valueOf(SpUtils.getTargetWeight()));
    }

    /**
     * 保存目标体重
     * @param view
     */
    public void confirm(View view){
        String target = etTarget.getText().toString().trim();
        if(TextUtils.isEmpty(target)){
            Toast.makeText(this, "请输入目标体重", Toast.LENGTH_LONG).show();
            return;
        }
        SpUtils.setTargetWeight(Float.parseFloat(target));
        finish();
        Toast.makeText(this, "设置成功", Toast.LENGTH_LONG).show();
    }

    /**
     * 返回
     * @param view
     */
    public void navigatorBack(View view){
        finish();
    }
}