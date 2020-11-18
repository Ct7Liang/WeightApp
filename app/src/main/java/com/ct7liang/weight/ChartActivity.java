package com.ct7liang.weight;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.ct7liang.translatestatusbar.TranslateStatusBar;
import com.ct7liang.weight.bean.Weight;
import com.ct7liang.weight.utils.SpUtils;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

import greendao.ct7liang.weight.WeightDaoUtils;

public class ChartActivity extends Activity {

    private LineChart chart;
    private List<Weight> weightList;
    private List<String> dateList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_chart);

        //透明状态栏
//        TranslateStatusBar.work(this, findViewById(R.id.top_view));

        chart = findViewById(R.id.chart);

    }

    @Override
    protected void onStart() {
        super.onStart();

        float targetWeight = SpUtils.getTargetWeight();

        dateList = new ArrayList<>();

        weightList = WeightDaoUtils.getAllDatas();

//        chart.setOnChartValueSelectedListener(this);

        chart.setDrawGridBackground(false); //是否显示背景

        chart.getDescription().setEnabled(false); //是否设置描述信息
//        chart.getDescription().setText("描述信息"); //设置描述信息内容

        chart.setDrawBorders(false); //是否显示表格边界

        chart.getAxisLeft().setEnabled(true); //是否显示左侧纵坐标
        chart.getAxisLeft().setDrawAxisLine(true); //是否绘制左侧纵坐标的轴线
        chart.getAxisLeft().setDrawGridLines(true); //是否绘制左侧纵坐标的分割线
//        chart.getAxisLeft().setDrawZeroLine(true); //是否绘制左侧纵坐标的零线

        LimitLine ll1 = new LimitLine(targetWeight, "目标体重:"+targetWeight+"kg");
        ll1.setLineWidth(4f);
        ll1.enableDashedLine(10f, 10f, 0f);
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        ll1.setTextSize(10f);
        chart.getAxisLeft().addLimitLine(ll1);


        chart.getAxisRight().setEnabled(false);

        chart.getXAxis().setEnabled(true); //是否显示横坐标
        chart.getXAxis().setLabelCount(10); //一个界面显示16个Lable
        chart.getXAxis().setGranularity(1f); //设置最小间隔，防止当放大时出现重复标签

        chart.getXAxis().setValueFormatter(new MyXAxisValueFormatter());
        chart.getXAxis().setLabelRotationAngle(-25);

//        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM); //设置横坐标位置 下方
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE); //设置横坐标位置 下方 文字在内部
//        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTH_SIDED); //设置上下都显示横坐标
//        chart.getXAxis().setPosition(XAxis.XAxisPosition.TOP); //设置横坐标位置 上方
//        chart.getXAxis().setPosition(XAxis.XAxisPosition.TOP_INSIDE); //设置横坐标位置 上方 文字在内部
        chart.getXAxis().setDrawAxisLine(true); //是否绘制横坐标方向上面的水平线
        chart.getXAxis().setDrawGridLines(true); //是否绘制横坐标上竖直方向的垂线

        chart.setTouchEnabled(true); //设置触摸是否可以生效

        chart.setDragEnabled(true); //设置是否可以拖拽

//        chart.setScaleEnabled(false); //同时设置X和Y方向上是否可以缩放
        chart.setScaleXEnabled(true); //设置X方向上是否可以缩放
        chart.setScaleYEnabled(false); //设置Y方向上是否可以缩放


        chart.setPinchZoom(false);


//        Legend l = chart.getLegend();
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
//        l.setOrientation(Legend.LegendOrientation.VERTICAL);
//        l.setDrawInside(true);

//        chart.resetTracking();

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        ArrayList<Entry> amValues = new ArrayList<>();
        ArrayList<Entry> pmValues = new ArrayList<>();
        for (int i = 0; i < weightList.size(); i++) {
            Weight weight = weightList.get(i);
            dateList.add(weight.getYear() +"-"+weight.getMonth()+"-"+weight.getDay());
            amValues.add(new Entry(i, weight.getAm()));
            pmValues.add(new Entry(i, weight.getPm()));
        }
        LineDataSet amSet = new LineDataSet(amValues, "AM");
        LineDataSet pmSet = new LineDataSet(pmValues, "PM");

        amSet.setLineWidth(2.5f);
        amSet.setCircleRadius(4f);
        amSet.setColor(Color.parseColor("#82AAE8"));
        amSet.setCircleColor(Color.parseColor("#82AAE8"));
        amSet.setDrawFilled(true);
        amSet.setFillColor(Color.parseColor("#82AAE8"));
        amSet.setValueTextSize(10f);

        pmSet.setLineWidth(2.5f);
        pmSet.setCircleRadius(4f);
        pmSet.setColor(Color.parseColor("#FFAA25"));
        pmSet.setCircleColor(Color.parseColor("#FFAA25"));
        pmSet.setDrawFilled(true);
        pmSet.setFillColor(Color.parseColor("#FFAA25"));
        pmSet.setValueTextSize(10f);

        dataSets.add(pmSet);
        dataSets.add(amSet);


        // make the first DataSet dashed
//        ((LineDataSet) dataSets.get(0)).enableDashedLine(10, 10, 0);
//        ((LineDataSet) dataSets.get(0)).setColors(ColorTemplate.VORDIPLOM_COLORS);
//        ((LineDataSet) dataSets.get(0)).setCircleColors(ColorTemplate.VORDIPLOM_COLORS);

        LineData data = new LineData(dataSets);
        chart.setData(data);
        chart.invalidate();

        chart.animateY(2000);
    }

    /**
     * 返回
     * @param view
     */
    public void navigatorBack(View view){
        finish();
    }

    public class MyXAxisValueFormatter extends ValueFormatter {

        @Override
        public String getFormattedValue(float value) {
            return dateList.get((int) value);
        }

    }
}
