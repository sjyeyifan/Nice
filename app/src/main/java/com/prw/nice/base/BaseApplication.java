package com.prw.nice.base;

import android.app.Application;
import android.content.Context;

import com.prw.nice.model.ServerAPI;

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
        mContext = this;
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
}
