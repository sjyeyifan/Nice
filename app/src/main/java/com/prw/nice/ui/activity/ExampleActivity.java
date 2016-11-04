package com.prw.nice.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.badoo.mobile.util.WeakHandler;
import com.prw.nice.R;

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

    @OnClick(R.id.btn_version)
    public void doClick() {
//        NdkExample example = new NdkExample();
//        Toast.makeText(this, "Version:" + example.print(), Toast.LENGTH_LONG)
//                .show();
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
    }

    class Leak {
    }
}
