package com.example.framework.bmob;

import android.content.Context;

import cn.bmob.v3.Bmob;

/**
 * 作者:Zhu Zhuo
 * Bmom管理类
 */
public class BmobManger {

    private static final String BMOB_SDK_ID = "5707e9844b93d5888f7432cf2a29295c";

    private static volatile BmobManger mInstance = null;

    private BmobManger () {}

    public static BmobManger getInstance() {
        if (mInstance == null) {
            synchronized (BmobManger.class) {
                if (mInstance == null) {
                    mInstance = new BmobManger();
                }
            }
        }
        return mInstance;
    }


    public void initBmob (Context mContext) {
        Bmob.initialize(mContext, "Your Application ID");
    }
}
