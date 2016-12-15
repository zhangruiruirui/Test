package lanou.xq;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mLv;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLv = (ListView) findViewById(R.id.main_lv);
        mAdapter = new MyAdapter();
        mLv.setAdapter(mAdapter);

//        mLv.setOnItemClickListener(this);

        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(MainActivity.this,Q.class);
                        intent.putExtra("key","abc");
                        startActivity(intent);
                        break;
                }
            }
        });


    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        switch (position){
//            case 0:
//                Intent intent = new Intent(MainActivity.this,Q.class);
//                intent.putExtra("a","a");
//                startActivity(intent);
//                break;
//        }
//    }






}
