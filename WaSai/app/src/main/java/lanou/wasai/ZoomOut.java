package lanou.wasai;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by ZhangRui on 16/11/30.
 */
public class ZoomOut implements ViewPager.PageTransformer {
    private static final float MAX_SCALE = 1.4f;
    private static final float MIN_SCALE = 0.8f;

    @Override
    public void transformPage(View page, float position) {
        if (position < -1) {
            page.setScaleX(MIN_SCALE);
            page.setScaleX(MAX_SCALE);

        } else if (position <= 1) {
            float scaleFactor = MIN_SCALE + (1 - Math.abs(position))
                    * (MAX_SCALE - MIN_SCALE);
            page.setScaleX(scaleFactor);
            if (position > 0) {
                page.setTranslationX(-scaleFactor * 2);

            } else if (position < 0) {
                page.setTranslationX(scaleFactor * 2);
            }
            page.setScaleY(scaleFactor);
        } else {
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
        }
    }
}
