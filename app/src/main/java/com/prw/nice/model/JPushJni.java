package com.prw.nice.model;

import com.jovision.jpush.EventHandler;
import com.jovision.jpush.JPushClient;

/**
 * 推送库的工具类(Jovision Push)
 *
 * @author ye.jian
 */
public class JPushJni implements EventHandler {

    private static final String TAG = "JPushJni";
    // -------------------------------------
    // ## 推送库基本方法↓
    // -------------------------------------
    // 服务器地址
    private final String mServerUrl = "http://acct-cn.jovcloud" +
            ".com:6798/pub/push_servers";
    // 库是否初始化
    private boolean isInitSdk;

    private JPushJni() {
        // 默认值
        setDefaultValue();
    }

    public static JPushJni getInstance() {
        return SingletonLoader.INSTANCE;
    }

    /**
     * 设置默认值
     */
    private void setDefaultValue() {
    }

    /**
     * 初始化SDK
     */
    public String getVersion() {
        return JPushClient.JPushCli_GetVersion();
    }

    @Override
    public void OnEvent(int i, String s, int i1) {

    }


    // -------------------------------------
    // ## 其它↓
    // -------------------------------------

    /**
     * 当前类(JPushJni.java)的单例
     */
    private static class SingletonLoader {
        private static final JPushJni INSTANCE = new JPushJni();
    }

    /**
     * 是否初始化推送库
     */
    public boolean isInitSdk() {
        return isInitSdk;
    }
}
