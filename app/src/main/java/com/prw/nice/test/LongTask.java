package com.prw.nice.test;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/11/8.
 */

public class LongTask implements Callable<String> {
    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(10);
        return "success";
    }
}
