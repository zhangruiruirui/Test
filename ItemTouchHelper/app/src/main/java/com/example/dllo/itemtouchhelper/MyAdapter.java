package com.example.dllo.itemtouchhelper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by dllo on 16/11/3.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList<String> arrayList;
    Context context;
    OnItemTouchOnClickListener onItemTouchOnClickListener;

    public void setOnItemTouchOnClickListener(OnItemTouchOnClickListener onItemTouchOnClickListener) {
        this.onItemTouchOnClickListener = onItemTouchOnClickListener;
    }

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    public MyAdapter(Context context) {

        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    //交换数据类里 指定位置的两条数据
    public void move(int from,int to){
        //交换
        Collections.swap(arrayList,from,to);
        notifyItemMoved(from,to);
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tv.setText(arrayList.get(position).toString());
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            onItemTouchOnClickListener.Onclick
                 (holder.getLayoutPosition(),holder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;
        private LinearLayout ll;

        public MyViewHolder(View itemView) {
            super(itemView);
            ll = (LinearLayout) itemView.findViewById(R.id.ll);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
