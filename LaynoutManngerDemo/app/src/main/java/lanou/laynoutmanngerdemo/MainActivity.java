package lanou.laynoutmanngerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ListView mainLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLv =
                (ListView) findViewById(R.id.main_lv);
        List<Bean> beanList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            Bean bean = new Bean();
            int type = random.nextInt(2);
            bean.setType(type);
            if (type == 0) {
                // 文字的
                bean.setText("这是文字" + i);
            }else  {
                bean.setImgId(R.mipmap.ic_launcher);
            }
            beanList.add(bean);
        }
        ListViewAdapter listViewAdapter = new ListViewAdapter(this);
        listViewAdapter.setBeanList(beanList);
        mainLv.setAdapter(listViewAdapter);

    }
}
