package com.example;


import android.content.Context;

import com.example.framework.utils.SpUtils;

public class Framework {
    private volatile static Framework mFramework;

    private Framework() {
//    LogUtils.i("xxxxxx");
    }

    public static Framework getFramework() {
        if (mFramework == null) {
            synchronized (Framework.class) {
                if (mFramework == null) {
                    mFramework = new Framework();
                }
            }
        }
        return mFramework;
    }

    public void initFramework(Context mContext) {
        SpUtils.getInstance().initSp(mContext);
    }
}
