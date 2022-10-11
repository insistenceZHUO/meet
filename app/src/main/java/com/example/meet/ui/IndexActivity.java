package com.example.meet.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.framework.entity.Constants;
import com.example.framework.utils.SpUtils;
import com.example.meet.R;


/**
 * 启动页面
 */
public class IndexActivity extends AppCompatActivity {

    /**
     * 1.把启动页全屏
     * 2.根据进入主页
     * 3.根据具体逻辑, 是进入主页还是引导页还是登陆页
     * 4.适配刘海屏
     */

    private static final int SKIP_MAIN = 1000;

    private Handler mHeader = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case SKIP_MAIN:
                    startMain();
                    break;
            }
            return false;
        }
    });

    private void startMain() {
        // 判断app是否是第一次启动
        Boolean a = SpUtils.getInstance().getBoolean(Constants.SP_IS_FIRST_APP, true);
        System.out.println("IndexActivity.startMain:" + a);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        mHeader.sendEmptyMessageDelayed(SKIP_MAIN, 2 * 1000);
    }
}