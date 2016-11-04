package com.prw.nice.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.prw.nice.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Example2Activity extends AppCompatActivity {

    static {
        Log.v("yejian", "static eample2 activity.");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_2);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_click)
    public void doClick() {
        Toast.makeText(this, "Click Here.", Toast.LENGTH_LONG)
                .show();
    }
}
