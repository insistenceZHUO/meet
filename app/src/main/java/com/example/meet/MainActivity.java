package com.example.meet;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.framework.base.BaseUIActivity;
import com.example.meet.manager.MediaPlayerManger;


public class MainActivity extends BaseUIActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MediaPlayerManger mediaPlayerManger = new MediaPlayerManger();

        /// 获取文件对象;
        AssetFileDescriptor fileDescriptor = getResources().openRawResourceFd(R.raw.guide);
        mediaPlayerManger.startPlay(fileDescriptor);

        mediaPlayerManger.setOnProgressListener(new MediaPlayerManger.OnMusicProgressList() {
            @Override
            public void onProgress(int progress, int pos) {
            }
        });
    }
}
