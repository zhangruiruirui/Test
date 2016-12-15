package lanou.drawable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.Iv);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.mp9182484_1427966882958_3_th_fv23);
        CircleDrawable circleDrawable = new CircleDrawable(bitmap);
        iv.setImageDrawable(circleDrawable);
    }
}
