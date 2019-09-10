package com.ct7liang.weight.utils;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.ct7liang.tangyuan.utils.LogUtils;
import com.ct7liang.weight.R;

public class WindowUtils {

    public void create(Activity activity, int layoutResId, int width, int height, OnContentViewInitListener contentViewInitListener){

        final Dialog dialog = new Dialog(activity, R.style.dialog_bg);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View contentView = View.inflate(activity, layoutResId, null);
        if (contentViewInitListener != null){
            contentViewInitListener.onContentViewInited(dialog, contentView);
        }
        width = width==0?LinearLayout.LayoutParams.MATCH_PARENT:width;
        height = height==0?LinearLayout.LayoutParams.WRAP_CONTENT:height;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, height);
        dialog.addContentView(contentView, lp);
        try {
            dialog.show();
        }catch (Exception e){
            LogUtils.write("弹出框打开失败: " + e.getMessage());
        }
    }

    public interface OnContentViewInitListener{
        void onContentViewInited(final Dialog dialog, View contentView);
    }
}