package com.example.framework.bmob;

import cn.bmob.v3.BmobObject;

/**
 * 作者:Zhu Zhuo
 */
public class MyData extends BmobObject {
//    public
  private String name;
  private int sex;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getSex() {
    return sex;
  }

  public void setSex(int sex) {
    this.sex = sex;
  }
}
