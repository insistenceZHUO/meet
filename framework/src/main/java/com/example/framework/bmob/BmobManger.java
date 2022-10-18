package com.example.framework.bmob;

import android.content.Context;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;

/**
 * 作者:Zhu Zhuo
 *
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
        Bmob.initialize(mContext, BMOB_SDK_ID);
    }

  /**
   * 发送短信验证码
   *
   * @param phone
   * @param listener
   */
    public void resultSMS (String phone, QueryListener<Integer> listener) {
      BmobSMS.requestSMSCode(phone,"", listener);
    }

  /**
   * 通过手机号码进行登陆或者注册
   *
   * @param phone
   * @param code
   * @param listener
   */
    public void signOrLoginByMobilePhone (String phone, String code, LogInListener<ImUser> listener) {
      BmobUser.signOrLoginByMobilePhone(phone, code, listener);
    }

  /**
   * 获取本地对象
   * @return
   */
  public ImUser getImUser () {
      return BmobUser.getCurrentUser(ImUser.class);
    }
}
