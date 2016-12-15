package lanou.myapplicationds;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    private LinearLayout lL;
    private ImageView mainIv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lL = (LinearLayout) findViewById(R.id.lL);
        mainIv = (ImageView) findViewById(R.id.mainIv);
        lL.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
       Bitmap bitmap = createViewBitmao(view);
        mainIv.setImageBitmap(bitmap);
    }

    private Bitmap createViewBitmao(View view) {
        Bitmap bitmap Bitmap.createBitmap(view.getWidth()),
        view.getHeight(),

        return null;
    }
}
