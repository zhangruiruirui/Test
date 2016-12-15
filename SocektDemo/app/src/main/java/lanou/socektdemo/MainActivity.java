package lanou.socektdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mMainEt;
    private Button mSendBtn;
    private TextView mMainTv;
    private static final String HOST = "172.16.1.175";
    private static final int PORT = 2333;
    private BufferedReader mBufferedReader;
    private boolean flag = true;
    private PrintStream mPrintStream;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainEt = (EditText) findViewById(R.id.main_et);
        mSendBtn = (Button) findViewById(R.id.sent_btn);
        mMainTv = (TextView) findViewById(R.id.main_tv);
        mSendBtn.setOnClickListener(this);
        // 必须放在子线程里
        new Thread(new Runnable() {
            @Override
            public void run() {
                initSocket();
            }
        }).start();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sent_btn:
                String s = mMainEt.getText().toString();
                Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
                mPrintStream.println(s + "\n");// 输出到服务端
                if ("886".equals(s)) {
                    // 如果发送的是"886" 酒吧socket关闭了
                    closeALL();
                }
                break;

        }
    }

    // 关闭资源
    private void closeALL() {
        flag = false;

    }

    private void initSocket() {
        try {
            flag = true;
            Socket socket = new Socket(HOST, PORT);
            // 设置保持长链接
            socket.setKeepAlive(true);
            // 连接到服务器

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            mBufferedReader = new BufferedReader(inputStreamReader);
            mPrintStream = new PrintStream(outputStream);
            while (flag) {
                String result = "";
                String line = "";
                while (!"".equals(line = mBufferedReader.readLine())) {
                    result += line;
                }
                // 打印服务端的数据
//                System.out.println(result);
                // 显示到textview上
                runOnUiThread(new UIRunnable(result));



            }
            // 关闭资源
            closeIO(mBufferedReader, inputStreamReader, inputStream,
                    mPrintStream, outputStream);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class UIRunnable implements Runnable {
        private String result;

        public UIRunnable(String result) {
            this.result = result;
        }

        @Override
        public void run() {
            mMainTv.setText(result);

        }

    }

    // 关闭所有能关闭的东西
    public static void closeIO(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
