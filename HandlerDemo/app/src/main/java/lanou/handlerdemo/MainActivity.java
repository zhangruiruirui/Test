package lanou.handlerdemo;

import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mBtn;
    private ThreadLocal<Boolean> mThreadLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mThreadLocal = new ThreadLocal<>();
        mThreadLocal.set(true);
        // 测试
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testThreadLocal();
            }


        });
    }



    // 测试
    private void testThreadLocal() {
        Log.d("MainActivity", Thread.currentThread().getName() + mThreadLocal.get());
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("MainActivity", Thread.currentThread().getName() + mThreadLocal.get());

                mThreadLocal.set(false);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                mThreadLocal.set(false);
                Log.d("MainActivity", Thread.currentThread().getName() + mThreadLocal.get());

            }
        }).start();
        try {
            Thread.sleep(1000);
            Log.d("MainActivity", Thread.currentThread().getName() + mThreadLocal.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
