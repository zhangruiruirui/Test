package lanou.onetime;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private boolean first;
    private PopupWindow pop;
    private TextView tv;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                Intent intent = new Intent(MainActivity.this, TwoActivity.class);
                startActivity(intent);
            } else {
                pop.showAsDropDown(tv,0,0);

            }

            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        first = sp.getBoolean("1", true);
        if (!first) {
            handler.sendEmptyMessageDelayed(1, 3000);
        } else {
            pop = creatPop();
            handler.sendEmptyMessageDelayed(2,3000);
        }
        first = false;
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("1",false);
        editor.commit();

    }
    private PopupWindow creatPop() {
        pop = new PopupWindow(this);
        View c = LayoutInflater.from(this).inflate(R.layout.activity_i,null);
        pop.setHeight(300);
        pop.setWidth(300);
        pop.setContentView(c);
        pop.setFocusable(true);
        return pop;
    }
}
