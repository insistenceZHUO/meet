package com.example.meet.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.framework.base.BaseUIActivity;
import com.example.framework.bmob.MyData;
import com.example.framework.view.TouchPictureV;
import com.example.meet.R;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class TestActivity extends BaseUIActivity  {

    private TouchPictureV touchPictureV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
    }

    private void initView () {
        touchPictureV = (TouchPictureV) findViewById(R.id.item_touch_helper_previous_elevation);
        touchPictureV.setViewResultListener(new TouchPictureV.OnViewResultListener() {
            @Override
            public void onResult() {
                Toast.makeText(TestActivity.this, "验证通过", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
