package com.prw.nice.common;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * 请求助手, 控制重复请求/请求完成后移除请求
 *
 * @author ye.jian
 */
public class RequestHandler extends BackgroundHandler {

    // 任务名称列表
    static List<String> mRequestNameList = new ArrayList<String>();
    // 任务句柄列表(任务名称, 事件)
    static ConcurrentHashMap<String, Future> mRequestFutureMap = new
            ConcurrentHashMap<String, Future>();

    public static ExecutorService getExecutorService() {
        return mThreadPool;
    }

    /**
     * web接口调用
     *
     * @param request
     */
    public static Future execute(Callable
                                         request) {
        Future future = null;
        String requestName = request.getClass().getSimpleName();
        if (!mRequestNameList.contains(requestName)) {
            // 将任务追加到任务列表中
            mRequestNameList.add(requestName);
            // 使用ExecutorService执行Callable类型的任务, 并将结果保存在future变量中
            future = mThreadPool.submit(request);
            mRequestFutureMap.put(requestName, future);
        } else {
            // 相同的任务未执行完成, 不再响应任务.
            Log.e(TAG, "[" + requestName + "] same task is executing, " +
                    "ignore.");
        }
        return future;
    }

    /**
     * 从任务列表中移除任务
     *
     * @return
     */
    public static void removeRequest(String name) {
        mRequestNameList.remove(name);
        mRequestFutureMap.remove(name);
    }

    /**
     * 取消任务
     */
    public static boolean cancelRequest(String name) {
        boolean result = false;
        Future future = mRequestFutureMap.get(name);
        if (future != null) {
            if (!future.isCancelled() && !future.isDone()) {
                result = future.cancel(true);
                Log.v(TAG, "request name:" + name + ", result:" + result);
            } else {
                Log.v(TAG, "request name:" + name + ", has done or " +
                        "cancelled.");
            }
        }
        // 从列表中移除
        removeRequest(name);

        return result;
    }

    /**
     * 取消全部的任务
     */
    public static void cancelAllRequest() {
        if (mRequestFutureMap == null || mRequestFutureMap.size() == 0) {
            return;
        }

        Set<String> mRequestTaskNames = mRequestFutureMap.keySet();
        Log.v(TAG, "#----cancel request start----#");
        for (String name : mRequestTaskNames) {
            cancelRequest(name);
        }
        Log.v(TAG, "#----cancel request end----#");
    }
}
