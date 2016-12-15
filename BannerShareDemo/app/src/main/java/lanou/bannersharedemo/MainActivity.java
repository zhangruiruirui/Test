package lanou.bannersharedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.transformer.TabletTransformer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
ArrayList<String> arrayList = new ArrayList<>();
    String url0 = "http://data.corp.36kr.com/api/ws?project=default";
    String url1 = "http://pic29.nipic.com/20130506/3822951_102005116000_2.jpg";
    String url2 = "http://pic36.nipic.com/20131125/8821914_090743677000_2.jpg";
    private Banner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayList.add(url0);
        arrayList.add(url1);
        arrayList.add(url2);
        banner = (Banner) findViewById(R.id.bannerB);
        // 设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        // 设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        // 设置组图片集合
        banner.setImages(arrayList);
        // 设置动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        // 标题
        banner.setBannerTitles(Arrays.asList("a","b","c"));
        // 自动轮播
        banner.isAutoPlay(true);
        // 轮播时间
        banner.setDelayTime(1000);
        // 指示器位置
        banner.setIndicatorGravity(BannerConfig.CENTER);
        // 轮播开始
        banner.start();


    }
}
