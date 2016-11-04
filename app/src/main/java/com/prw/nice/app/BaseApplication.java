package com.prw.nice.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.prw.nice.model.ServerAPI;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */

public class BaseApplication extends Application {
    private Context mContext;
    private static ServerAPI serverAPI;

    @Override
    public void onCreate() {
        super.onCreate();
        // 内存泄露监测
        if (LeakCanary.isInAnalyzerProcess(this)) {
            Log.v("prw", "------------------");
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }

        // 内存泄露监测
        refWatcher = LeakCanary.install(this);

        // ------------------------------------------------
        mContext = this;
        // 接口
        serverAPI = new Retrofit.Builder()
                .baseUrl(ServerAPI.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build().create(ServerAPI.class);
    }

    public static ServerAPI getServerAPI() {
        return serverAPI;
    }

    public Context getAppContext() {
        return mContext;
    }

    // -----------------------------------------
    // # LeakCanary
    // -----------------------------------------
    private RefWatcher refWatcher;

    public static RefWatcher getRefWatcher(Context context) {
        BaseApplication application = (BaseApplication) context
                .getApplicationContext();
        return application.refWatcher;
    }
}
