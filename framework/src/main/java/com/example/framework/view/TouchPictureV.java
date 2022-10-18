package com.example.framework.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.framework.R;

public class TouchPictureV extends View {
    // 背景
    private Bitmap bgBitmap;
    // 背景画笔
    private Paint mPaintBg;
    // 空白块
    private Bitmap mNullBitmap;
    // 空白块画笔
    private Paint mPaintNull;
    // 移动方块
    private Bitmap mMoveBitmap;
    // 移动画笔
    private Paint mMovePaint;
    // view宽高
    private int mWidth;
    private int mHeight;

    // 方块大小
    private int CARD_SIZE = 200;
    // 方块的坐标
    private int LINE_W = 0,LINE_H = 0;
    /// 移动方块的横坐标
    private int moveX = 200;

    /// 误差值
    private int errorValues = 10;

    private OnViewResultListener viewResultListener;
    public TouchPictureV(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setViewResultListener(OnViewResultListener viewResultListener) {
        this.viewResultListener = viewResultListener;
    }

    public TouchPictureV(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    private void init () {
        mPaintBg = new Paint();
        mPaintNull = new Paint();
        mMovePaint = new Paint();
    }

    /**
     * 绘制一个背景;
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBg(canvas);
        drawNullCard(canvas);
        drawMoveCard(canvas);
    }

    private void drawMoveCard(Canvas canvas) {
        /// 截取空白位置的坐标的Bitmap图像;
        mMoveBitmap = Bitmap.createBitmap(bgBitmap, LINE_W, LINE_H, CARD_SIZE,CARD_SIZE);
        /// 绘制到view上面，如果使用LINE_W,LINE_W, 那会跟空白区域重叠;
        canvas.drawBitmap(mMoveBitmap, moveX, LINE_H, mMovePaint);
    }

    private void drawNullCard(Canvas canvas) {
        // 1.获取图片
        mNullBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_null_card);
        // 1.计算值
        CARD_SIZE = mNullBitmap.getWidth();
        // 99 / 3 * 2
        LINE_W = mWidth / 3 * 2;

        LINE_H = mHeight /2 - (CARD_SIZE /2);
        canvas.drawBitmap(mNullBitmap,LINE_W,LINE_H,mPaintNull);
    }

    private void drawBg(Canvas canvas) {
        /// 获取图片
        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_bg);
        /// 创建一个空的Bitmap Bitmap的宽高等于V的宽高
        bgBitmap = Bitmap.createBitmap(mWidth,mHeight, Bitmap.Config.ARGB_8888);
        /// 将图片绘制到空的bitMap上面去
        Canvas bgCanvas = new Canvas(bgBitmap);
        bgCanvas.drawBitmap(mBitmap, null, new Rect(0,0,mWidth,mHeight), mPaintBg);
        /// 将bitmap绘制到view里面去
        canvas.drawBitmap(bgBitmap, null, new Rect(0,0,mWidth,mHeight), mPaintBg);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                //防止越界
                if (event.getX() > 0 && event.getX() < (mWidth - CARD_SIZE)){
                    moveX = (int) event.getX();
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                /**
                 * 抬起之后做验证
                 * moveX = LINE_W
                 */
                if (moveX >(LINE_W - errorValues) && moveX <= LINE_W +errorValues) {
                    if(viewResultListener != null) {
                        viewResultListener.onResult();
                        // 重置
                      moveX = 200;
                      invalidate();
                    }
                }
                break;
        }

        return true;
    }

    public interface OnViewResultListener {
        void onResult();
    }
}
