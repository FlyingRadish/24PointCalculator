package org.houxg.monkeyhey.component;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;


import org.houxg.monkeyhey.util.logger.Log;

import de.greenrobot.event.EventBus;

/**
 * Created by houxg on 2014/12/19.
 */
public class Master extends Application implements Thread.UncaughtExceptionHandler {


    private static EventBus eventBus;
    private final static String TAG = Master.class.getSimpleName();
    private final static String LEANCLOUD_APPID = "df4knebd21153r0zzd0b9it6rpy8mbcjh7ugz8qv9pvgq6fl";
    private final static String LEANCLOUD_APPKEY = "xlajl0go172vrq5i97ur40eojwy1m4ipao2fg1b79lb9wmv8";

    public Master() {
        eventBus = new EventBus();
    }

    public static EventBus getEventBus() {
        if (eventBus == null) {
            eventBus = new EventBus();
            Log.wtf("houxg", "EventBus re-create?");
        }
        return eventBus;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AVOSCloud.initialize(this, LEANCLOUD_APPID, LEANCLOUD_APPKEY);
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        Log.i(TAG, "uncaughtException:" + ex.getMessage());
        ex.printStackTrace();
        Log.wtf(getPackageName(), Log.getStackTraceString(ex));
    }
}
