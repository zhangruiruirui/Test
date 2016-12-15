package lanou.ipcdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            serviceMessrnger = new Messenger(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
    private Messenger serviceMessrnger;
    private TextView mTv;
    private Messenger atyMessenger = new Messenger(new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 接受信息
            Bundle bundle = msg.getData();
            bundle.setClassLoader(MainActivity.this.getClassLoader());
            Person person = bundle.getParcelable("person");
            Toast.makeText(MainActivity.this, person.getName(), Toast.LENGTH_SHORT).show();
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = (TextView) findViewById(R.id.main_tv);
        mTv.setOnClickListener(this);
        Intent intent = new Intent(this,RemoteService.class);
        bindService(intent,mConnection,BIND_AUTO_CREATE);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_tv:
                // 与服务进行通信
                Message message = Message.obtain();
                message.what = 1;
                message.arg1 = 100;
                Person person = new Person();
                person.setName("张三");
                person.setAge(18);
                message.obj = person;
                // 吧acty的messenger对象 放到message里面
                message.replyTo = atyMessenger;
                try {
                    // 把消息发送到另一个进程的服务里
                    serviceMessrnger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }


    }
}
