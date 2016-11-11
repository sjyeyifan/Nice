package com.prw.nice.test;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created by Administrator on 2016/11/8.
 */

public class Task1 implements Callable<String> {
    @Override
    public String call() throws Exception {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 100; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
