package com.example.meet.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.framework.base.BasePageAdapter;
import com.example.meet.R;
import com.example.meet.databinding.ActivityGuideBinding;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {
    /**
     * 1. viewPager: 适配器｜帧动画
     * 2. 小原点的逻辑
     * 3. 歌曲的播放
     * 4. 属性动画的旋转播放
     * 5. 跳转
     */

    private ActivityGuideBinding binding;

    private View view1;
    private View view2;
    private View view3;

    private ViewPager mViewPager;

    private List<View> mPageList = new ArrayList();

    private BasePageAdapter mPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGuideBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        view1 = View.inflate(this, R.layout.layout_pager_guide_1, null);
        view2 = View.inflate(this, R.layout.layout_pager_guide_2, null);
        view3 = View.inflate(this, R.layout.layout_pager_guide_3, null);
        mViewPager = binding.mViewPager;
        mPageList.add(view1);
        mPageList.add(view2);
        mPageList.add(view3);

        /// 预加载
        mViewPager.setOffscreenPageLimit(mPageList.size());
        mPageAdapter = new BasePageAdapter(mPageList);
        mViewPager.setAdapter(mPageAdapter);
    }

}