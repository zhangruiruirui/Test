package lanou.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {

    private EditText mMainEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainEt = (EditText) findViewById(R.id.main_et);
        Observable.create(new EtObservable(mMainEt)).debounce(500, TimeUnit.MILLISECONDS).filter(new Func1<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                // 字符串不为空, 才继续向下执行
                // 为空的字符串就被过滤掉了
                return TextUtils.isEmpty(s.trim());
            }
        })
                .subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d("MainActivity", "数据是"+s);
            }
        });
    }
}
