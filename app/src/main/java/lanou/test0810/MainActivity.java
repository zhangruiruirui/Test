package lanou.test0810;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import lanou.test0810.base.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private TextView mainTv;
    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void intiViews() {
        mainTv = bindView(R.id.main_tv);
        setClick(this,mainTv);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {

    }
}
