package com.prw.nice.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.badoo.mobile.util.WeakHandler;
import com.prw.nice.R;
import com.prw.nice.common.RequestHandler;
import com.prw.nice.test.LongTask;
import com.prw.nice.test.LongTask2;
import com.prw.nice.test.Task1;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExampleActivity extends AppCompatActivity {

    //加载库
    static {
        System.loadLibrary("ndkexample");
    }

    private static Leak mLeak;
    private WeakHandler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        ButterKnife.bind(this);

        mLeak = new Leak();
        mHandler = new WeakHandler();
    }

    @OnClick({R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3})
    public void doClick(View view) {
//        NdkExample example = new NdkExample();
//        Toast.makeText(this, "Version:" + example.print(), Toast.LENGTH_LONG)
//                .show();
        switch (view.getId()) {
            case R.id.btn_0:
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ExampleActivity.this, "lalala", Toast
                                .LENGTH_LONG).show();
                    }
                }, 5000);

                Intent intent = new Intent(this, Example2Activity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_1:
                executeTask();
                break;
            case R.id.btn_2:
                cancelTask();
                break;
            case R.id.btn_3:
                stopTask();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        stopTask();
        finish();
        // 杀死进程
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    class Leak {
    }

    private void executeTask() {
        Log.v("BackgroundHandler", "开始执行任务");
        RequestHandler.execute(new Task1());
        RequestHandler.execute(new Task1());
        RequestHandler.execute(new LongTask());
        RequestHandler.execute(new LongTask2());
    }

    private void cancelTask() {
        Log.v("BackgroundHandler", "取消任务");
        RequestHandler.cancelAllRequest();
    }

    private void stopTask() {
        Log.v("BackgroundHandler", "停止任务");
        RequestHandler.shutdownAndAwaitTermination();
    }
}
