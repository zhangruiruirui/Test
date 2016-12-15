package com.example.dllo.itemtouchhelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnItemTouchOnClickListener {
    private LinearLayout ll;
    private RecyclerView rv;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView) findViewById(R.id.rv);
        ll = (LinearLayout) findViewById(R.id.ll);

        adapter = new MyAdapter(this);
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            arrayList.add("item" + i);
        }

        ItemTouchHelper.Callback callback = mCallback();
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(rv);

        adapter.setArrayList(arrayList);
        rv.setAdapter(adapter);

        //GridLayoutManager manager = new GridLayoutManager(this, 3);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
    }

    private ItemTouchHelper.Callback mCallback() {
        return new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                //instanceof
                if(layoutManager instanceof GridLayoutManager){
                    return ItemTouchHelper.Callback.makeMovementFlags(
                            ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                                    ItemTouchHelper.START | ItemTouchHelper.END, 0
                    );
                }else {
                    return ItemTouchHelper.Callback.makeMovementFlags(
                            ItemTouchHelper.UP | ItemTouchHelper.DOWN
                                   , 0
                    );
                }


            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                adapter.move(from, to);
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                    //滑动
            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                super.onSelectedChanged(viewHolder, actionState);
                if(actionState != ItemTouchHelper.ACTION_STATE_IDLE){
                    //当前Item的状态不为空闲的时候
                    View view = viewHolder.itemView;
                    view.setBackgroundColor(0xFFFF0000);//把背景设置成红色
                }
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                //还原颜色
                viewHolder.itemView.setBackgroundColor(0xFFCCCCCC);

            }
        };


    }


    @Override
    public void Onclick(int position, MyAdapter.MyViewHolder viewHolder) {

    }
}
