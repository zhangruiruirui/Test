package lanou.a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRv = (RecyclerView) findViewById(R.id.main_rv);
        MyAdapter adapter = new MyAdapter();
        View headView = LayoutInflater.from(this).inflate(R.layout.headview,null);
        // 吧之前写好的adapter放到headadpter里包装
        HeaderAdapter headerAdapter = new HeaderAdapter(adapter);
        // 添加头布局
        headerAdapter.addHeadView(headView);
        // 使用包装后的headadpter
        mRv.setAdapter(headerAdapter);
        mRv.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv.setLayoutManager(manager);
    }
}
