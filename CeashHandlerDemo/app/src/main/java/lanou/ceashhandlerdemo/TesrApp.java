package lanou.ceashhandlerdemo;

import android.app.Application;

/**
 * Created by ZhangRui on 16/11/28.
 */

public class TesrApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
    }
}
