package com.example.framework.view;


import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.text.Layout;
import android.view.Window;
import android.view.WindowId;
import android.view.WindowManager;

import androidx.annotation.NonNull;


public class DialogView  extends Dialog {

  public DialogView(@NonNull Context context, int layout, int style, int gravity) {
    super(context, style);
    setContentView(layout);
    Window window = getWindow();
    WindowManager.LayoutParams layoutParams = window.getAttributes();
    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
    layoutParams.gravity = WindowManager.LayoutParams.WRAP_CONTENT;
    layoutParams.gravity = gravity;
    window.setAttributes(layoutParams);
  }
}
