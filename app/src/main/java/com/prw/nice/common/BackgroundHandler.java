/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.prw.nice.common;

import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BackgroundHandler {

    protected static final String TAG = "BackgroundHandler";
    public static HandlerThread sLooperThread;
    // 线程池ExecutorService对象
    public static ExecutorService mThreadPool;

    static {
        sLooperThread = new HandlerThread("BackgroundHandler", HandlerThread
                .MIN_PRIORITY);
        sLooperThread.start();
        mThreadPool = Executors.newCachedThreadPool();
    }

    public static void execute(Runnable runnable) {
        mThreadPool.execute(runnable);
    }

    public static Looper getLooper() {
        return sLooperThread.getLooper();
    }

    public static void shutdownAndAwaitTermination() {
        mThreadPool.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!mThreadPool.awaitTermination(1, TimeUnit.SECONDS)) {
                Log.v(TAG, "线程池还未关闭");
                mThreadPool.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!mThreadPool.awaitTermination(5, TimeUnit.SECONDS)) {
                    Log.e(TAG, "Pool did not terminate");
                } else {
                    Log.e(TAG, "线程池已经关闭了");
                }
            }
        } catch (InterruptedException ie) {
            Log.v(TAG, "------------");
            // (Re-)Cancel if current thread also interrupted
            mThreadPool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }
}
