package lanou.righttlive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.RelativeLayout;


public class SecondActivity extends AppCompatActivity implements View.OnTouchListener {

    private RelativeLayout relativeLayout;
    private VelocityTracker v;
    private float xDown;
    private float xMove;
    private int scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        relativeLayout = (RelativeLayout) findViewById(R.id.rv);
        relativeLayout.setOnTouchListener(this);

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        v = VelocityTracker.obtain();
        v.addMovement(motionEvent);
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDown = motionEvent.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:
                xMove = motionEvent.getRawX();
                int disTanceX = (int) (xMove - xDown);
                int xPeed = getScroll();
                if (disTanceX > 200 && xPeed > 200){
                    overridePendingTransition(R.anim.in_from_left,R.anim.out_to_right);
                    finish();
                }
                    break;
        }
        return true;
    }

    public int getScroll() {
        v.computeCurrentVelocity(1000);
        int velocity = (int) v.getXVelocity();
        return Math.abs(velocity);
    }
}
