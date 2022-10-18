package com.example.framework.manager;

import android.content.Context;
import android.view.Gravity;

import com.example.framework.R;
import com.example.framework.view.DialogView;

public class DialogManger {

  private static volatile DialogManger mInstance = null;

  private DialogManger() {
  }

  public static DialogManger getInstance() {
    if (mInstance == null) {
      synchronized (DialogManger.class) {
        if (mInstance == null) {
          mInstance = new DialogManger();
        }
      }
    }
    return mInstance;
  }

  public DialogView initView(Context mContext, int layout) {
    return new DialogView(mContext, layout, R.style.Theme_Dialog, Gravity.CENTER);
  }

  public DialogView initView(Context mContext, int layout, int gravity) {
    return new DialogView(mContext, layout, R.style.Theme_Dialog, gravity);
  }


  public void show(DialogView view) {
    if (view != null && !view.isShowing()) {
      view.show();
    }
  }

  public void hide(DialogView view) {
    if (view != null && view.isShowing()) {
      view.hide();
    }
  }
}
