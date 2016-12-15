package com.lanou.chenfengyao.okhttp3demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button getBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getBtn = (Button) findViewById(R.id.get_btn);
        getBtn.setOnClickListener(this);
        findViewById(R.id.post_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_btn:
                getTest();
                // HttpUtil.getTest();
                HttpUtil.getTest(1, new ResponseCallBack<TestBean>() {
                    @Override
                    public void onResponse(TestBean testBean) {
                        
                    }

                    @Override
                    public void onError(Exception exception) {

                    }
                });
                break;
            case R.id.post_btn:
                postTest();
                break;
        }
    }

    private void postTest() {
        PostRequestBody
                bean = new PostRequestBody();
        bean.setPlatform("4");
        bean.setApp("efashion");
        bean.setLocale("zh_CN");

        Gson gson = new Gson();
        String s = gson.toJson(bean);
        Log.d("MainActivity", s);


        String postUrl = "http://appserver.jnwtv.com:8080/jnwtv-client/movie/getmoviedetail";

        OkHttpClient client =
                new OkHttpClient();

        //创建post请求 的 body
        //普通的 key-value格式的参数
        //需要创建FormBody
        FormBody formBody =
                new FormBody.Builder()
                        .add("account", "26690576370")
                        .add("episodeNo", "1")
                        .add("mId", "1193")
                        .add("token", "2016101715493688672507925614387226690576370")
                        .add("piId", "10037")
                        .build();

        Request request =
                new Request.Builder()
                .url(postUrl)
                .post(formBody)//把body放进去
                .build();

        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.d("MainActivity", response.body().string());
                    }
                });

    }


    private void getTest() {
        String getUrl = "http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=2&per=10";
        //创建Client对象
        OkHttpClient client =
                new OkHttpClient();
        //创建一个请求
        Request request =
                new Request.Builder()
                        .url(getUrl)
                        .build();

        Call call = client.newCall(request);
        //异步的网络请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //回调并不在主线程,使用的时候,必须配合Handler
                String data = response.body().string();//拿到结果
                Log.d("Sysout", "name:" + Thread.currentThread().getName());
                Log.d("Sysout", data);

            }
        });
    }
}
