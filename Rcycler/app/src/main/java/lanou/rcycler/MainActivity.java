package lanou.rcycler;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private ArrayList<String> arrayList;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.main_rerfsh);
        recyclerView = (RecyclerView) findViewById(R.id.main_rc);
        arrayList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            arrayList.add("item"+i);

        }
        adapter = new MyAdapter(this);
        adapter.setArrayList(arrayList);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.addOnScrollListener(new EndLess(manager) {
            @Override
            protected void onLoadMore(int curentPage) {
                arrayList.add("加载出来的");
                adapter.notifyDataSetChanged();
            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                arrayList.add(0,"刷新出来的");
                adapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
            }
        });

    }
}
