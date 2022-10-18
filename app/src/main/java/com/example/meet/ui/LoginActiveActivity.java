package com.example.meet.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.framework.bmob.BmobManger;
import com.example.framework.bmob.ImUser;
import com.example.framework.entity.Constants;
import com.example.framework.manager.DialogManger;
import com.example.framework.utils.SpUtils;
import com.example.framework.view.DialogView;
import com.example.framework.view.TouchPictureV;
import com.example.meet.MainActivity;
import com.example.meet.R;
import com.example.meet.databinding.ActivityLoginActiveBinding;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;

public class LoginActiveActivity extends AppCompatActivity implements View.OnClickListener {
  /**
   * 1.
   * 2.发送验证码，按钮变为不可点击，同时开始倒计时，倒计时完成后，文字变为"发送"。
   * 3.通过手机号和验证码进行登陆。
   * 4.登陆成功后获取本地对象。
   */

  private ActivityLoginActiveBinding binding;

  private EditText et_phone;
  private EditText et_code;
  private Button btn_send_code;
  private Button btn_login;

  private DialogView mCodeView;

  private TouchPictureV mPictureV;

  private static final int H_TIME = 1001;
  // 60秒倒计时
  private static int TIME = 60;
  private Handler mHandler = new Handler(new Handler.Callback() {
    @Override
    public boolean handleMessage(@NonNull Message message) {
      switch (message.what) {
        case H_TIME:
          TIME--;
          if ( TIME > 0) {
            btn_send_code.setText(TIME + "s");
            mHandler.sendEmptyMessageDelayed(H_TIME, 1000);
          } else {
            btn_send_code.setEnabled(true);
            btn_send_code.setText(R.string.text_login_send);
          }
          break;
      }
      return false;
    }
  });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

  private void initView() {
      binding = binding.inflate(getLayoutInflater());
      View view = binding.getRoot();
      setContentView(view);
      initDialogView();
      et_phone = binding.etPhone;
      et_code = binding.etCode;
      btn_send_code = binding.btnSendCode;
      btn_login = binding.btnLogin;

      btn_login.setOnClickListener(this);
      btn_send_code.setOnClickListener(this);

      String phone = SpUtils.getInstance().getString(Constants.SP_PHONE, "");
      if (!TextUtils.isEmpty(phone)) {
        et_phone.setText(phone);
      }
//    setContentView(R.layout.activity_login_active);
  }

  private void initDialogView() {
    mCodeView = DialogManger.getInstance().initView(this, R.layout.dialog_code_view);
    mPictureV = mCodeView.findViewById(R.id.mPictureV);
    mPictureV.setViewResultListener(new TouchPictureV.OnViewResultListener() {
      @Override
      public void onResult() {
        sendSMS();
      }
    });
  }


  @Override
  public void onClick(View view) {
      switch (view.getId()) {
        case R.id.btn_send_code:
          DialogManger.getInstance().show(mCodeView);
          break;
        case R.id.btn_login:
          Login();
          break;
      }
  }

  /**
   * 登陆
   */
  private void Login() {
    /// 1. 获取手机电话号码
    String phone = et_phone.getText().toString().trim();
    if (TextUtils.isEmpty(phone)) {
      Toast.makeText(LoginActiveActivity.this,getString(R.string.text_login_phone_null), Toast.LENGTH_SHORT).show();
      return;
    }

    /// 2. 获取验证码
    String code = et_code.getText().toString().trim();
    if (TextUtils.isEmpty(code)) {
      Toast.makeText(LoginActiveActivity.this,getString(R.string.text_login_phone_null), Toast.LENGTH_SHORT).show();
      return;
    }
    System.out.println("111111.Login");
    BmobManger.getInstance().signOrLoginByMobilePhone(phone, code, new LogInListener<ImUser>() {
      @Override
      public void done(ImUser imUser, BmobException e) {
        if (e == null ){
          System.out.println("222222.Login");
          // 登陆成功
          startActivity(new Intent(LoginActiveActivity.this, MainActivity.class));
          SpUtils.getInstance().putString(Constants.SP_PHONE, phone);
          finish();
        } else {
          Toast.makeText(LoginActiveActivity.this, "error:"+ e.toString(), Toast.LENGTH_SHORT).show();
        }
      }
    });
  }

  /**
   * 发送短信验证码
   */
  private void sendSMS() {
    /// 1. 获取手机电话号码
    String phone = et_phone.getText().toString().trim();
    if (TextUtils.isEmpty(phone)) {
      Toast.makeText(LoginActiveActivity.this,getString(R.string.text_login_phone_null), Toast.LENGTH_SHORT).show();
      return;
    }
    // 2.请求短信验证码
    BmobManger.getInstance().resultSMS(phone, new QueryListener<Integer>() {
      @Override
      public void done(Integer integer, BmobException e) {
        if (e == null) {
          btn_send_code.setEnabled(false);
          mHandler.sendEmptyMessage(H_TIME);
          Toast.makeText(LoginActiveActivity.this,getString(R.string.text_user_result_succeed_login), Toast.LENGTH_SHORT).show();
        }else {
          Toast.makeText(LoginActiveActivity.this,getString(R.string.text_user_result_fail), Toast.LENGTH_SHORT).show();
        }
      }
    });
  }

}
