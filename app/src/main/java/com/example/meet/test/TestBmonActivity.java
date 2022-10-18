package com.example.meet.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.framework.bmob.MyData;
import com.example.meet.R;
import com.example.meet.databinding.ActivityTestBmonBinding;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class TestBmonActivity extends AppCompatActivity implements View.OnClickListener {


  private ActivityTestBmonBinding binding;

  private Button btn_add;
  private Button btn_del;
  private Button btn_query;
  private Button btn_update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      // 初始化view;
      initView();
      // 绑定事件
      bindingEvent();
    }

  private void bindingEvent() {
    btn_add.setOnClickListener((View.OnClickListener) this);
    btn_del.setOnClickListener((View.OnClickListener) this);
    btn_query.setOnClickListener((View.OnClickListener) this);
    btn_update.setOnClickListener((View.OnClickListener) this);
  }

  private void initView() {
    binding = binding.inflate(getLayoutInflater());
    View view = binding.getRoot();
    setContentView(view);
    btn_add = binding.add;
    btn_del = binding.del;
    btn_query = binding.query;
    btn_update = binding.update;
  }
  @Override
  public void onClick(View view) {
    switch (view.getId()){
      case R.id.add:
        MyData myDate = new MyData();
        myDate.setName("李四");
        myDate.setSex(1);
        myDate.save(new SaveListener<String>() {
          @Override
          public void done(String s, BmobException e) {
            if (e == null){
              System.out.println("新增成功.done");
            }
          }
        });
        break;
      case R.id.del:
        MyData data = new MyData();
        data.setObjectId("d82210a6f9");
        data.delete(new UpdateListener() {
          @Override
          public void done(BmobException e) {
            if (e == null) {
              Toast.makeText(TestBmonActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
            } else {
              Toast.makeText(TestBmonActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
            }

          }
        });
        break;
      case R.id.query:
        BmobQuery<MyData> bmobQuery = new BmobQuery<>();
//        bmobQuery.getObject("271b50f3d5", new QueryListener<MyData>() {
//          @Override
//          public void done(MyData myData, BmobException e) {
//            if (e == null) {
//              System.out.println("mydata:" + myData.getName() + ":" + myData.getSex());
//            } else {
//              System.out.println("mydata:" + e.toString());
//            }
//          }
//        });
        // 2.条件查询
//        bmobQuery.addWhereEqualTo("name", "张三");
//         3.不输入条件, 查询全部;
        bmobQuery.findObjects(new FindListener<MyData>() {
          @Override
          public void done(List<MyData> list, BmobException e) {
            if (e ==null){
              if (list != null && list.size() > 0 ){
                for (int i = 0; i < list.size(); i++) {
                  System.out.println(list.get(i).getName());
                  System.out.println(list.get(i).getSex());
                }
              }
            }
          }
        });
        break;
      case R.id.update:
        MyData my = new MyData();
        my.setName("liSi");
        my.update("abe0cfade3", new UpdateListener() {
          @Override
          public void done(BmobException e) {
            if (e == null) {
              Toast.makeText(TestBmonActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
            } else {
              Toast.makeText(TestBmonActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
            }
          }
        });
        break;
    }
  }

}
