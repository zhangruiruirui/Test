package lanou.scancode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;
    public static final int REQUES = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.main_btn);
        textView = (TextView) findViewById(R.id.main_tv);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 点击跳转扫描界面
                Intent intent = new Intent(MainActivity.this,ScanActivity.class);
                startActivityForResult(intent,REQUES);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK && requestCode == ScanActivity.RESULT){
            Bundle bundle = data.getBundleExtra("bundle");
            String result = bundle.getString(ScanActivity.SCAN_RESULT_KEK);
            textView.setText(result);
        }
    }
}
