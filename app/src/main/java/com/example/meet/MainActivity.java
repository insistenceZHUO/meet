package com.example.meet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.framework.utils.LogUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      LogUtils.i("hello world");
      LogUtils.e("hello world");
    }
}
