package com.ct7liang.weight.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ct7liang.tangyuan.recyclerview.BaseRecyclerViewAdapter;
import com.ct7liang.tangyuan.utils.SpUtils;
import com.ct7liang.weight.R;
import com.ct7liang.weight.bean.Weight;
import com.ct7liang.weight.view.LinearHorizontalView;

import java.util.List;

public class WeightHorizontalAdapter extends BaseRecyclerViewAdapter {

    private final LayoutInflater inflater;
    private List<Weight> list;
    private float minW = 45;
    private float maxW = 55;
    private int pW;

    public WeightHorizontalAdapter(Context context, List<Weight> list) {
        super(context);
        this.list = list;
        inflater = LayoutInflater.from(context);

        refreshData();
    }

    public void refreshData(){
        minW = SpUtils.start().getFloat("minWeight", 45);
        maxW = SpUtils.start().getFloat("maxWeight", 55);
        pW = (int) ((maxW - minW)*10);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.item_weight_horizontal, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder){
            Weight weight = list.get(position);
            ((MyViewHolder) holder).title.setText(weight.getDate() + "  " + weight.getWeight() + "kg");
            ((MyViewHolder) holder).lvv.setSize(weight.getWeight(), minW, maxW, pW);
            ((MyViewHolder) holder).empty.setVisibility(position == list.size()-1 ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class MyViewHolder extends ContentViewHolder{

        private TextView title;
        private LinearHorizontalView lvv;
        private View empty;

        MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_content);
            lvv = itemView.findViewById(R.id.linear_vertical_view);
            empty = itemView.findViewById(R.id.empty);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }

        @Override
        public boolean onLongClick(View v) {
            if (mLongListener!=null){
                mLongListener.onLongClick(v, getAdapterPosition());
            }
            return false;
        }
    }
}
