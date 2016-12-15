package lanou.angle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{
ViewFlipper flipper;
    GestureDetector detector;
    Animation[] animation = new Animation[5];
    final  int FLIP_DISTANCE = 50;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flipper = (ViewFlipper) findViewById(R.id.filper);
        detector = new GestureDetector(this,this);
        flipper.addView(addImagView(R.mipmap.ic_launcher));
        flipper.addView(addImagView(R.mipmap.ic_launcher));
        flipper.addView(addImagView(R.mipmap.ic_launcher));
        flipper.addView(addImagView(R.mipmap.ic_launcher));
        flipper.addView(addImagView(R.mipmap.ic_launcher));
        animation[0] = AnimationUtils.loadAnimation(this,R.anim.left_in);
        animation[1] = AnimationUtils.loadAnimation(this,R.anim.left_out);
        animation[2] = AnimationUtils.loadAnimation(this,R.anim.right_in);
        animation[3] = AnimationUtils.loadAnimation(this,R.anim.right_out);
    }

    private View addImagView(int ic_launcher) {
        ImageView imageview = new ImageView(this);
        imageview.setImageResource(ic_launcher);
        imageview.setScaleType(ImageView.ScaleType.CENTER);

        return imageview;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        if (motionEvent.getX() - motionEvent1.getX() > FLIP_DISTANCE) {
            flipper.setInAnimation(animation[0]);
            flipper.setOutAnimation(animation[1]);
            flipper.showPrevious();
            return true;
        }else if (motionEvent1.getX() - motionEvent.getX() > FLIP_DISTANCE){
            flipper.setInAnimation(animation[2]);
            flipper.setOutAnimation(animation[3]);
            flipper.showNext();
            return true;
        }
        return false;
    }
}
