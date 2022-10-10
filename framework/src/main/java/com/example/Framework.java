//package com.example;
//
//import com.example.framework.utils.LogUtils;
//
//public class Framework {
//  private volatile static Framework mFramework;
//
//  private Framework () {
//    LogUtils.i("xxxxxx");
//  }
//
//  public static Framework getFramework () {
//    if (mFramework ==null ) {
//      synchronized (Framework.class) {
//        if (mFramework == null) {
//          mFramework = new Framework();
//        }
//      }
//    }
//    return mFramework;
//  }
//}
