package lanou.bitmapressize;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by ZhangRui on 16/11/2.
 * 工具类里的方法我们通常会写上static调用起来比较简单
 * 工具类通常没有成员变量只有方法
 */
public class BitMapTool {
    // 不指定宽高
    public static final int UNDEFINE = -1;
    // 给定imageview 来拿到imageviwe的宽高
    // 如果imageview 没有给宽高信息, 或者 拿不到imageview的宽高信息
    // 就是用屏幕的宽高信息, 因为一张图的像素点个数
    // 超出屏幕的像素点个数, 是没有意义的
    public static WidthAndHeight getReqWH(ImageView imageView) {
        int reqW, reqH;
        reqW = imageView.getWidth();
        reqH = imageView.getHeight();
        if (reqH <= 0 || reqW <= 0) {
            // imageview 没有给宽高信息
            // 获得屏幕宽高
            Context context = imageView.getContext();
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            reqH = displayMetrics.heightPixels;
            reqW = displayMetrics.widthPixels;
        }
        // 把图片的宽高信息返回
        WidthAndHeight widthAndHeight = new WidthAndHeight();
        widthAndHeight.setHeight(reqH);
        widthAndHeight.setWidth(reqW);
        return widthAndHeight;
    }
    public static class WidthAndHeight{
        int height,width;

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }
    // 精确压缩到指定的宽高
    // 在使用的时候为了图片的比例正常, 会计算一下压缩比例, 然后把宽高, 按照压缩比例去压缩
    // 缺点, 需要一张原图, 原图会加载内存
    // 可以直接给目标宽高的值, 就会把图片压缩到目标宽高
    // 如果目标宽高给定的是UNDEFINE代表目标宽高是要根据imageview的宽高 动态计算出来的
    // 目标宽
    // 目标高
    // 原始图片
    // 压缩后的图片

    public static Bitmap resizeAccurate(int reqW, int reqH, Bitmap bitmap,ImageView imageView){
        if (reqH == UNDEFINE || reqW == UNDEFINE) {
           WidthAndHeight reqWH  = getReqWH(imageView);
            // 防止图片呗拉伸, 所以选择图片宽高和 计算出狂傲的最小值
            reqH = Math.min(bitmap.getHeight(),reqWH.getHeight());
            reqW = Math.min(bitmap.getWidth(),reqWH.getWidth());
        }
        //TODO 还要加上 保证比例的计算代码
        bitmap = Bitmap.createScaledBitmap(bitmap,reqW,reqH,false);
        return bitmap;
    }
}
