package com.example.meet.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.framework.base.BasePageAdapter;
import com.example.framework.utils.AnimUtils;
import com.example.meet.R;
import com.example.meet.databinding.ActivityGuideBinding;
import com.example.meet.manager.MediaPlayerManger;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {
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

    private ImageView iv_guide_start;
    private ImageView iv_guide_night;
    private ImageView iv_guide_smile;

    private ImageView iv_music_switch;
    private TextView tv_guide_skip;

    private ImageView iv_guide_point_1;
    private ImageView iv_guide_point_2;
    private ImageView iv_guide_point_3;

    private ObjectAnimator mAnim;


    private MediaPlayerManger mGuideMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGuideBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        iv_guide_point_1 = (ImageView) binding.ivGuidePoint1;
        iv_guide_point_2 = (ImageView) binding.ivGuidePoint2;
        iv_guide_point_3 = (ImageView) binding.ivGuidePoint3;

        iv_music_switch = binding.ivMusicSwitch;
        tv_guide_skip = binding.tvGuideSkip;

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

        /// 帧动画
        iv_guide_start = view1.findViewById(R.id.iv_guide_star);
        iv_guide_night = view2.findViewById(R.id.iv_guide_night);
        iv_guide_smile = view3.findViewById(R.id.iv_guide_smile);
        AnimationDrawable animStart1 = (AnimationDrawable) iv_guide_start.getBackground();
        animStart1.start();
        AnimationDrawable animNight = (AnimationDrawable) iv_guide_night.getBackground();
        animNight.start();
        AnimationDrawable animSmile = (AnimationDrawable) iv_guide_smile.getBackground();
        animSmile.start();

        /// 小圆点
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectPoint(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        // 歌曲逻辑
        startMusic();

    }

    private void startMusic() {
         mGuideMusic = new MediaPlayerManger();
        mGuideMusic.setLooping(true);

        iv_music_switch.setOnClickListener(this);
        tv_guide_skip.setOnClickListener(this);

        ///
        AssetFileDescriptor file = getResources().openRawResourceFd(R.raw.guide);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mGuideMusic.startPlay(file);
        }

        /// 旋转动画
        mAnim = AnimUtils.rotation(iv_music_switch);
        mAnim.start();
    }

    /**
     * 动态选择小圆点
     *
     * @param position
     */
    private void selectPoint(int position) {
        switch (position) {
            case 0:
                iv_guide_point_1.setImageResource(R.drawable.img_guide_point_p);
                iv_guide_point_2.setImageResource(R.drawable.img_guide_point);
                iv_guide_point_3.setImageResource(R.drawable.img_guide_point);
                break;
            case 1:
                iv_guide_point_1.setImageResource(R.drawable.img_guide_point);
                iv_guide_point_2.setImageResource(R.drawable.img_guide_point_p);
                iv_guide_point_3.setImageResource(R.drawable.img_guide_point);
                break;
            case 2:
                iv_guide_point_1.setImageResource(R.drawable.img_guide_point);
                iv_guide_point_2.setImageResource(R.drawable.img_guide_point);
                iv_guide_point_3.setImageResource(R.drawable.img_guide_point_p);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        System.out.println("GuideActivity.onClick:"+mGuideMusic.MEDIA_STATUS);
        switch (view.getId()) {
            case R.id.iv_music_switch:
                if (mGuideMusic.MEDIA_STATUS == MediaPlayerManger.MEDIA_STATUS_PAUSE) {
                    System.out.println("GuideActivity.onClick: start");
                    mAnim.start();
                    mGuideMusic.continuePlay();
                    iv_music_switch.setImageResource(R.drawable.img_guide_music);
                } else if (mGuideMusic.MEDIA_STATUS == MediaPlayerManger.MEDIA_STATUS_PLAY) {
                    System.out.println("GuideActivity.onClick: pause");
                    mAnim.pause();
                    mGuideMusic.pausePlay();
                    iv_music_switch.setImageResource(R.drawable.img_guide_music_off);
                }
                break;
            case R.id.tv_guide_skip:
                startActivity(new Intent(this, LoginActiveActivity.class));
                finish();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view);
        }
    }
}