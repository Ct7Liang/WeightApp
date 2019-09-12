package com.ct7liang.weight;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ct7liang.tangyuan.utils.SpUtils;
import com.ct7liang.weight.base.BaseActivity;
import com.ct7liang.weight.bean.Weight;
import com.ct7liang.weight.view.MyMarkerView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import greendao.ct7liang.weight.GreenDaoHelper;
import greendao.ct7liang.weight.WeightDao;

public class WeightDataActivity extends BaseActivity implements OnChartValueSelectedListener {

    private WeightDao weightDao;
    private List<Weight> list;
    private LineChart chart;
    private SpUtils spUtils;

    @Override
    public int setLayout() {
        setFullScreen();
        return R.layout.activity_weight_data;
    }

    @Override
    public void initSurface() {

        spUtils = SpUtils.start();
        weightDao = GreenDaoHelper.getDao().getWeightDao();
        list = weightDao.loadAll();

        chart = findViewById(R.id.line_char);

        chart.setBackgroundColor(Color.WHITE);
        chart.getDescription().setEnabled(false);
        chart.setTouchEnabled(true);
        chart.setOnChartValueSelectedListener(this);
        chart.setDrawGridBackground(false);

        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
        mv.setChartView(chart);
        chart.setMarker(mv);

        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setPinchZoom(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.enableAxisLineDashedLine(10f, 10f, 0f);

        YAxis yAxis = chart.getAxisLeft();
        yAxis.enableAxisLineDashedLine(10f, 10f, 0f);

        //上限界限
        LimitLine ll1 = new LimitLine(spUtils.getFloat("maxWeight", 55), "体重上限");
        ll1.setLineWidth(4f);
        ll1.enableDashedLine(10f, 10f, 0f);
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        ll1.setTextSize(10f);

        //下限界限
        LimitLine ll2 = new LimitLine(spUtils.getFloat("minWeight", 45), "体重下限");
        ll2.setLineWidth(4f);
        ll2.enableDashedLine(10f, 10f, 0f);
        ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        ll2.setTextSize(10f);

        // 在数据后面绘制限制线
        yAxis.setDrawLimitLinesBehindData(true);

        // 在Y轴上添加界限
        yAxis.addLimitLine(ll1);
        yAxis.addLimitLine(ll2);

        setData();

        chart.animateXY(0, 0);

        // get the legend (only possible after setting data)
        Legend l = chart.getLegend();

        // draw legend entries as lines
        l.setForm(Legend.LegendForm.CIRCLE);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    private void setData(){
        ArrayList<Entry> values = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Weight weight = list.get(i);
            values.add(new Entry(i, weight.getWeight(), weight));
        }
        LineDataSet set1 = new LineDataSet(values, "");
        set1.setDrawIcons(false);
        // 折线样式
        set1.enableDashedLine(10f, 0f, 0f);
        // 折线图颜色
        set1.setColor(Color.TRANSPARENT);
        // 折线宽度
        set1.setLineWidth(0.5f);
        // 小点颜色
        set1.setCircleColor(Color.parseColor("#FF5F00"));
        // 小点尺寸
        set1.setCircleRadius(5f);
        // 小点样式 是否是以空心圆展示
        set1.setDrawCircleHole(false);
        // 设置别名折线长度
        set1.setFormSize(0f);
        // 设置文字大小
        set1.setValueTextSize(9f);
        // 设置选择线样式
        set1.enableDashedHighlightLine(10f, 5f, 0f);
        // 设置选择线颜色
        set1.setHighLightColor(Color.parseColor("#FF5F00"));
        // set the filled area
        set1.setDrawFilled(true);
        set1.setFillFormatter(new IFillFormatter() {
            @Override
            public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                return chart.getAxisLeft().getAxisMinimum();
            }
        });
        // set color of filled area
        if (Utils.getSDKInt() >= 18) {
            // drawables only supported on api level 18 and above
            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_red);
            set1.setFillDrawable(drawable);
        } else {
            set1.setFillColor(Color.BLACK);
        }
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1); // add the data sets
        // create a data object with the data sets
        LineData data = new LineData(dataSets);
        // set data
        chart.setData(data);
    }
}
