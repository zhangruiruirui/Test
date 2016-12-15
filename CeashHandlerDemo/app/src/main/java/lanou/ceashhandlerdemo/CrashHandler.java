package lanou.ceashhandlerdemo;

import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ZhangRui on 16/11/28.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String PATH = Environment.
            getExternalStorageDirectory().getPath() + "/CrashTest/log";
    private static final String FILE_NAME = "crash";
    private static final String FILE_NAME_SUFFIX = ".trace";

    private static CrashHandler sInstance = new CrashHandler();
    private Thread.UncaughtExceptionHandler mDEfayitCrashHandler;

    private Context mContext;

    public CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return sInstance;
    }

    public void init(Context context) {

        mDEfayitCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        dumpExceptionToSDCard(throwable);
        if (mDEfayitCrashHandler!=null) {
            mDEfayitCrashHandler.uncaughtException(thread,throwable);
        }else {
            Process.killProcess(Process.myPid());
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void dumpExceptionToSDCard(Throwable e) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            Log.d("CrashHandler", "sd");
        }
        File dir = new File(PATH);
        if (!dir.exists()) {
            dir.mkdir();
        }
        long current = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(current));
        File file = new File(PATH + FILE_NAME + time + FILE_NAME_SUFFIX);

        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            pw.println();
            pw.println(time);
            dumpPhoneInfo(pw);
            e.printStackTrace(pw);
            pw.close();


        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (PackageManager.NameNotFoundException e1) {
            e1.printStackTrace();
        }

    }

    private void dumpPhoneInfo(PrintWriter pw) throws PackageManager.NameNotFoundException {
        PackageManager pm = mContext.getPackageManager();
        PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
        pw.print("App Version: ");
        pw.print(pi.versionName);
        pw.print('_');
        pw.println(pi.versionCode);

        // Android 版本号
        pw.print("Os Version: ");
        pw.print(Build.VERSION.RELEASE);
        pw.print('_');
        pw.println(Build.VERSION.SDK_INT);

        // 手机制造商
        pw.print("Vendor: ");
        pw.print('_');
        pw.println(Build.MANUFACTURER);

        // 手机型号
        pw.print("Model: ");
        pw.print('_');
        pw.println(Build.MODEL);

        // cpu 架构
        pw.print("CPU ABI: ");
        pw.println(Build.CPU_ABI);
    }



}
