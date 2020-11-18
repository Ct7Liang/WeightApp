package com.ct7liang.weight.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ct7liang.weight.R;
import com.ct7liang.weight.bean.MonthDays;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.DayViewHolder> {

    private Context act;
    private final MonthDays monthDays;

    public CalendarAdapter(Context context, MonthDays monthDays) {
        this.act = context;
        this.monthDays = monthDays;
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new DayViewHolder(View.inflate(act, R.layout.view_calendar_day, null));
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder dayViewHolder, int i) {
        if(i < monthDays.weekIndex - 1){
            dayViewHolder.tv.setText("");
        }else{
            String s = String.valueOf(monthDays.list.get(i - monthDays.weekIndex + 1).day);
            dayViewHolder.tv.setText(s);
            if(monthDays.haveDataDays.contains(s)){
                Log.i("ct7liang123", s + ": true");
                dayViewHolder.tv.setTextColor(Color.parseColor("#FFFFFF"));
                dayViewHolder.tv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            }else{
                Log.i("ct7liang123", s + ": false");
                dayViewHolder.tv.setTextColor(Color.parseColor("#CDCDCD"));
                dayViewHolder.tv.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            }
        }
        if(i - monthDays.weekIndex + 1 == monthDays.currentIndex){
            dayViewHolder.view.setBackgroundResource(R.drawable.shape_calendar_day_bg1);
        }else{
            dayViewHolder.view.setBackgroundResource(R.drawable.shape_calendar_day_bg2);
        }
    }

    @Override
    public int getItemCount() {
        return monthDays.list.size() + monthDays.weekIndex - 1;
    }

    protected class DayViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv;
        View view;
        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            view = itemView.findViewById(R.id.item);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            int position =  getAdapterPosition() - monthDays.weekIndex + 1;
            if(onDayClickListener != null){
                onDayClickListener.onClick(v, position, monthDays.list.get(position));
            }
            monthDays.currentIndex = position;
            notifyDataSetChanged();
        }
    }

    public OnDayClickListener onDayClickListener;
    public void setOnDayClickListener(OnDayClickListener listener){
        this.onDayClickListener = listener;
    }
    public interface OnDayClickListener {
        void onClick(View v, int position, MonthDays.Day day);
    }

    /**
     * 获取适配器更新之后,选中的日期,防止上次选中为31号,下次选中后为28号
     * @return position
     */
    public int getCurrentPosition(){
        return this.monthDays.currentIndex;
    }
}
