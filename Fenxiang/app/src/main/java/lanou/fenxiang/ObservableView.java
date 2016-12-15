package lanou.fenxiang;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by ZhangRui on 16/11/14.
 */
public class ObservableView extends ScrollView {
    private ScrollView scrollView = null;
    public ObservableView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public interface scrllviewlisen{
        void onScroll(ObservableView observableView , int x, int y,int oldl,)
    }
}
