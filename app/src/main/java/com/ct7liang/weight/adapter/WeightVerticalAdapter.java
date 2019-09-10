package com.ct7liang.weight.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ct7liang.tangyuan.recyclerview.BaseRecyclerViewAdapter;
import com.ct7liang.weight.R;
import com.ct7liang.weight.bean.Weight;
import com.ct7liang.weight.view.LinearVerticalView;

import java.util.List;

public class WeightVerticalAdapter extends BaseRecyclerViewAdapter {

    private final LayoutInflater inflater;
    private List<Weight> list;

    public WeightVerticalAdapter(Context context, List<Weight> list) {
        super(context);
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.item_weight_vertical, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder){
            ((MyViewHolder) holder).lvv.setSize(85-(position%10));
        }
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    private class MyViewHolder extends ContentViewHolder{

        private LinearVerticalView lvv;

        MyViewHolder(View itemView) {
            super(itemView);
            lvv = itemView.findViewById(R.id.linear_vertical_view);
        }

        @Override
        public void onClick(View v) {

        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
}
