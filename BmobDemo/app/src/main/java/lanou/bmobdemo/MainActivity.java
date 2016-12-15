package lanou.bmobdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button loginBtn;
    private Button loadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginBtn = (Button) findViewById(R.id.login_btn);
        loadBtn = (Button) findViewById(R.id.up_load_btn);
        loadBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);

        //第一：默认初始化 建议初始化放到Application里面,MyApp类
        Bmob.initialize(this, "e147211defaeeba1e36874ca529491a3");

        // 创建用户
//        BmobUser bmobUser = new BmobUser();
//        bmobUser.setUsername("ZhangRui");// 用户名
//        bmobUser.setPassword("www1380466com");//密码
//        bmobUser.signUp(new SaveListener<BmobUser>() {
//            @Override
//            public void done(BmobUser bmobUser, BmobException e) {
//                if (e == null) {
//                    Toast.makeText(MainActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
//                } else {
//                    // 注册失败
//                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                    Log.d("MainActivity", e.getMessage());
//                }
//            }
//        });
//        BmobUser bmobUser1 = BmobUser.getCurrentUser();
//        if (bmobUser1 != null) {
//            Toast.makeText(this, "已经登录过了", Toast.LENGTH_SHORT).show();
//        }else  {
//            bmobUser1 = new BmobUser();
//            bmobUser1.setUsername("ZhangRui");
//            bmobUser1.setPassword("www1380466com");
//            bmobUser1.login(new SaveListener<BmobUser>() {
//                @Override
//                public void done(BmobUser bmobUser, BmobException e) {
//                    if (e == null) {
//                        Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
//                    } else  {
//                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                        Log.d("MainActivity", e.getMessage());
//                    }
//                }
//            });
//        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                login();
                break;
            case R.id.up_load_btn:
                upLoadIcon();
                break;
        }
    }

    private void upLoadIcon() {
        final MyUser myUser = MyUser.getCurrentUser(MyUser.class);
        if (myUser == null) {
            Toast.makeText(this, "先登录", Toast.LENGTH_SHORT).show();
        } else {
            // 已经登陆过
            // 上传头像
            // 拿到图片
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
            // getCacheDir是Android提供的缓存路径
            // 位置 包名/cache
            // 该方法 是context的方法, 可以使用Application的context
            File cacheDir = getCacheDir();
            if (!cacheDir.exists()) {
                // 如果这个路径不存在
                cacheDir.mkdir();// 就创建这个文件夹
            }
            // 文件名加上时间为了防止 文件名重复
            long time = System.currentTimeMillis();
            File iconFile = new File(cacheDir, myUser.getUsername() + time + ".png");
            if (iconFile.exists()) {
                // 如果文件不存在
                try {
                    // 创建文件
                    iconFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                // 创建一个文件输出流
                FileOutputStream fileOutputStream = new FileOutputStream(iconFile);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                try {
                    fileOutputStream.close();
                    // 这样图片就存到了File里面了
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // 上传File
            final BmobFile bmobFile = new BmobFile(iconFile);
            // 上传
            bmobFile.uploadblock(new UploadFileListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        Toast.makeText(MainActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                        // 拿到图片的url
                        String fileUrl = bmobFile.getFileUrl();
                        Log.d("MainActivity", fileUrl);
                        // 把图片的URL存储到用户表里
                        myUser.setIcon(fileUrl);
                        myUser.update(new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                    Toast.makeText(MainActivity.this, "存储URL成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "存储URL失败", Toast.LENGTH_SHORT).show();
                                    Log.d("MainActivity", e.getMessage());
                                }
                            }
                        });
                    } else {
                        Toast.makeText(MainActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
                        Log.d("MainActivity", e.getMessage());
                    }
                }
            });

        }
    }

    // 登录
    private void login() {
        MyUser myUser = new MyUser();
        myUser.setUsername("ZhangRui");
        myUser.setPassword("www1380466com");
        myUser.login(new SaveListener<MyUser>() {
            @Override
            public void done(MyUser myUser, BmobException e) {
                if (e == null) {
                    Log.d("MainActivity", "登陆成功");
                } else {
                    Log.d("MainActivity", "登录失败");
                    Log.d("MainActivity", e.getMessage());
                }
            }
        });
    }
}
