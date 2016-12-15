package lanou.bitmapressize;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.main_iv);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.test);
        // bitmap 原始的宽高 采样率
        Log.d("MainActivity", "bitmap.getWidth():" + bitmap.getWidth());
        Log.d("MainActivity", "bitmap.getHeight():" + bitmap.getHeight());
        iv.setImageBitmap(bitmap);
        Bitmap smallBitmap = resize(100,100);
        Log.d("MainActivity", "smallBitmap.getWidth():" + smallBitmap.getWidth());
        Log.d("MainActivity", "smallBitmap.getHeight():" + smallBitmap.getHeight());
        iv.setImageBitmap(smallBitmap);
        Bitmap smallBitmapAccurate = resizeAccurate(300,300,bitmap);
        Log.d("MainActivity", "smallBitmapAccurate.getHeight():" + smallBitmapAccurate.getHeight());
        Log.d("MainActivity", "smallBitmapAccurate.getWidth():" + smallBitmapAccurate.getWidth());
        iv.setImageBitmap(smallBitmapAccurate);

    }
    // 精确压缩到指定的宽高
    // 在使用的时候为了图片的比例正常, 会计算一下压缩比例, 然后把宽高, 按照压缩比例去压缩
    // 缺点, 需要一张原图, 原图会加载内存
    // 目标宽
    // 目标高
    // 原始图片
    // 压缩后的图片

    private Bitmap resizeAccurate(int reqW,int reqH,Bitmap bitmap){
        bitmap = Bitmap.createScaledBitmap(bitmap,reqW,reqH,false);
        return bitmap;
    }
    // 原图不加载进内存, 通过采样率来压缩图片
    // 采样率压缩的好处是, 原图不加载进内存
    // 但是不能做到精确的压缩目标宽高, 采样率的有效值只能是2的整数次幂如果不是就会自动改成接近的值
    // 要求的宽度(目标宽)
    // 要求的高
    // 压缩后的图片
    private Bitmap resize(int reqW,int reqH) {
        // 加载图片的设置类, 可以设置怎么加载图片
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 只解码图片的宽高, 使用这个options不会真正把图片加载进内存,只会读取图片的宽高信息
        options.inJustDecodeBounds = true;
        // 使用这个options来加载图片
        BitmapFactory.decodeResource(getResources(),R.mipmap.test,options);
        // 拿到图片的宽高信息
        int w = options.outWidth;
        int h = options.outHeight;
        // 设定采样率, 由宽/除以目标宽 和 高/除以目标高的最大值决定
        // 采样率越大图片越小
        options.inSampleSize = Math.max(w/reqW,h/reqH);
        // 不止解析宽高信息
        options.inJustDecodeBounds = false;
        // 获得压缩后的图片
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.test,options);

        return bitmap;
    }
}
