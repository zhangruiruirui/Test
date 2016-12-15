package lanou.handlerdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.lang.ref.SoftReference;

public class Main2Activity extends AppCompatActivity {

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHandler = new MyHandler();
        mHandler.set(this);


    }
    // 将handler变成静态内部类, 他就不会持有, 外部类对象了
    // activity就可以正常的销毁了
     static class MyHandler extends Handler{
        // 加上软引用
        private SoftReference<Main2Activity> mReference;
        public void setHandlerActivity(Main2Activity handlerActivity) {
            mReference = new SoftReference<Main2Activity>(handlerActivity);

        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Main2Activity main2Activity = mReference.get();
            if (main2Activity != null) {
                main2Activity.mTextView.setText("aaa");
            }
        }
    }
}
