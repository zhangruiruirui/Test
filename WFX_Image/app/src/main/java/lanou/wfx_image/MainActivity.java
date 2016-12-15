package lanou.wfx_image;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView img;
    private RelativeLayout rl;
    private RelativeLayout.LayoutParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rl = (RelativeLayout) findViewById(R.id.rl);
        img = (ImageView) findViewById(R.id.img);

        params = (RelativeLayout.LayoutParams) img.getLayoutParams();
        rl.setOnTouchListener(new View.OnTouchListener() {
            float currenDistance;
            float lastDiatance = -1;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    // 手指按下没有离开
                    case  MotionEvent.ACTION_DOWN:
                        Log.d("MainActivity", "手指按下没有离开");
                        break;
                    // 当手指移动的时候
                    case MotionEvent.ACTION_MOVE:
                        if (motionEvent.getPointerCount() > 1) {
                            Log.d("MainActivity", "当手指移动的时候");

                        }
                        // 两个手指间的缩放
                        if (motionEvent.getPointerCount() >= 2) {
                            float offsetX = motionEvent.getX(0) - motionEvent.getX(1);
                            float offsetY = motionEvent.getY(0) - motionEvent.getY(1);
                            // 两个手指之间的距离
                            currenDistance = (float) Math.sqrt(offsetX * offsetX + offsetY * offsetY);
                            // 如果上一次的距离小于0,上一次距离等与当前距离
                            if (lastDiatance < 0) {
                                lastDiatance = currenDistance;
                            }else  {
                                // 前辈们说, 两只手之间有缝隙, 所以要5的距离比较好
                                if (currenDistance - lastDiatance > 5){
                                    lastDiatance = currenDistance;
                                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) img.getLayoutParams();
                                    layoutParams.width = (int) (1.1f * img.getWidth());
                                    layoutParams.height = (int) (1.1f * img.getHeight());
                                    img.setLayoutParams(layoutParams);
                                }else if (lastDiatance - currenDistance > 5) {
                                    lastDiatance = currenDistance;
                                    if (img.getWidth() > 100){
                                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) img.getLayoutParams();
                                        layoutParams.width = (int) (0.9f * img.getWidth());
                                        layoutParams.height = (int) (0.9f * img.getHeight());
                                        img.setLayoutParams(layoutParams);
                                    }
                                }
                            }
                        }
                        break;
                    // 当手指离开的时候
                    case MotionEvent.ACTION_UP:
                        Log.d("MainActivity", "当手指离开的时候");
                        break;
                }


                return true;
            }
        });
    }
}
