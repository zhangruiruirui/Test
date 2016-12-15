package lanou.wasai;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout mActivity;
    private ViewPager mVp;

    private VPAdapter mVpAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVp = (ViewPager) findViewById(R.id.vp);
        mVpAdapter = new VPAdapter(this);
        mActivity = (RelativeLayout) findViewById(R.id.activity_main);
        // clipChilyong来定义他的子控件是否要在它对应有的便捷进行绘制
        // 默认设置true 也就是不润徐进行扩展绘制
        mVp.setClipChildren(false);
        mActivity.setClipChildren(false);
        DisplayMetrics outMetics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(outMetics);
        int screenWidth = outMetics.widthPixels;
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams)mVp.getLayoutParams();
        lp.leftMargin = screenWidth/3;
        lp.rightMargin = screenWidth/3;
        mVp.setLayoutParams(lp);
        mVp.setPageTransformer(true,new ZoomOut());
        mVp.setOffscreenPageLimit(3);
        mVp.setPageMargin(50);
        mActivity.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return mVp.dispatchTouchEvent(motionEvent);
            }
        });
        mVp.setAdapter(mVpAdapter);
    }
}
