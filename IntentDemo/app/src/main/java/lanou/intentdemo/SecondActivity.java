package lanou.intentdemo;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ZhangRui on 16/11/25.
 *
 */
public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView secondBtn2;
    private TextView secondBtn1;
    private TextView secondBtn3;
    private TextView secondBtn4;
    @Bind(R.id.second_btn1)
    Button mButton;
    @Bind(R.id.second_btn_2) Button mButton1;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_second);
        Transition exp = TransitionInflater.from(this).inflateTransition(R.transition.explode);
        getWindow().setExitTransition(exp);
        getWindow().setEnterTransition(exp);
        getWindow().setReenterTransition(exp);


        secondBtn2 = (TextView) findViewById(R.id.second_btn_2);
        secondBtn1 = (TextView) findViewById(R.id.second_btn1);
        secondBtn3 = (TextView) findViewById(R.id.second_btn3);
        secondBtn4 = (TextView) findViewById(R.id.second_btn4);
        ButterKnife.bind(this);

        secondBtn1.setOnClickListener(this);
        secondBtn2.setOnClickListener(this);


             }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.second_btn1:
                Intent intent = new Intent(SecondActivity.this,ThridActivity.class);
                startActivity(intent, ActivityOptionsCompat.makeClipRevealAnimation(this,secondBtn2,"sharedView",));

                break;
            case R.id.second_btn_2:
                break;
        }
    }
}
