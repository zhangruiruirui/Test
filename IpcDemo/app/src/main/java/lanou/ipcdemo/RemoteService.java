package lanou.ipcdemo;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by ZhangRui on 16/12/7.
 */

public class RemoteService extends Service {
    // 使用messenger的方式 实现进程间通信
    private Messenger mMessenger = new Messenger(new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Log.d("RemoteService", "接到了");
            Person person = (Person) msg.obj;
            Toast.makeText(RemoteService.this, person.getName(), Toast.LENGTH_SHORT).show();

            // 拿到Aty的messenger对象
            Messenger atyMessenger = msg.replyTo;
            Message sendToAtyMessage = Message.obtain();
            Bundle bundle = new Bundle();
            person.setName("有妹子的老宋");
            bundle.putParcelable("person",person);
            sendToAtyMessage.setData(bundle);
            try {
                // 发送的Aty里
                atyMessenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            super.handleMessage(msg);
        }
    });
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
