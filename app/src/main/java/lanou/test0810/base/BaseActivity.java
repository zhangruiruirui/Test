package lanou.test0810.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import lanou.test0810.R;

/**
 * Created by ZhangRui on 16/10/21.
 * Activity的鸡肋
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 绑定布局
        setContentView(getLayout());
        // 初始化组件
        intiViews();
        // 初始化数据
        initData();

    }
    protected abstract int getLayout();
    // 初始化组件, 各种findViewById
    protected abstract void intiViews();
    // 初始化数据, 基本上就是 拉去网络数据
    protected abstract void initData();
    // 简化findViewById 省去墙砖的过程
    protected <T extends View> T bindView (int id) {
        return (T) findViewById(id);

    }
    protected void setClick(View.OnClickListener clickListener,
                            View ... views) {
        for (View view : views) {
            view.setOnClickListener(clickListener);
        }
    }
}
