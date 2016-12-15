package lanou.rxjava;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by ZhangRui on 16/12/1.
 * 当edittext数据改变的时候,可以发射数据
 */

public class EtObservable// 泛型代表要发射的数据类型
        implements Observable.OnSubscribe<String>{
    private EditText mEditText;

    public EtObservable(EditText editText) {
        mEditText = editText;
    }

    @Override
    public void call(final Subscriber<? super String> subscriber) {
        // 对editext 添加监听
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                // 改变之后的文字信息
                // 调用观察者的onnext方法
                // 把edittext里的值 传递出去
                subscriber.onNext(editable.toString());
            }
        });

    }
}
