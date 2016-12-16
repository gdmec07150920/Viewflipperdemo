package com.example.administrator.viewflipperdemo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    private int[] images={R.drawable.icon1,R.drawable.icon2,
            R.drawable.icon3,R.drawable.icon4,R.drawable.icon5};
    private GestureDetector gestureDetector=null;
    private ViewFlipper viewFlipper=null;
    private static int FLIMG_MIN_DISTANG=100;
    private static int FLING_MIN_VELOCITY=200;
    private Activity mActivity =null;
    private Animation rInAmin,rOutAmin,lInAmin,lOutAmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity=this;
        viewFlipper= (ViewFlipper) findViewById(R.id.viewFlipper);
        gestureDetector=new GestureDetector(this,this);//声明检测手势事件
        rInAmin= AnimationUtils.loadAnimation(mActivity,R.anim.push_right_in);
        rOutAmin= AnimationUtils.loadAnimation(mActivity,R.anim.push_right_out);
        lInAmin= AnimationUtils.loadAnimation(mActivity,R.anim.push_left_in);
        rOutAmin= AnimationUtils.loadAnimation(mActivity,R.anim.push_right_out);
        for(int i=0;i<images.length;i++){
            ImageView iv=new ImageView(this);
            iv.setImageResource(images[i]);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(iv,i,new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        }
        viewFlipper.setAutoStart(true);//设置自动播放功能，(点击事件前，自动播放)
        viewFlipper.setFlipInterval(2000);
        viewFlipper.startFlipping();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewFlipper.stopFlipping();
        viewFlipper.setAutoStart(false);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if(e2.getX()-e1.getX()>FLIMG_MIN_DISTANG&&Math.abs(velocityX)>FLING_MIN_VELOCITY){
            viewFlipper.setInAnimation(lInAmin);
            viewFlipper.setOutAnimation(rOutAmin);
            viewFlipper.showPrevious();
            setTitle("相片编号:"+viewFlipper.getDisplayedChild());
            return true;
        }else if(e1.getX()-e2.getX()>FLIMG_MIN_DISTANG&&Math.abs(velocityX)>FLING_MIN_VELOCITY){
            viewFlipper.setInAnimation(rInAmin);
            viewFlipper.setOutAnimation(lOutAmin);
            viewFlipper.showNext();
            setTitle("相片编号:"+viewFlipper.getDisplayedChild());
            return true;
        }
        return false;
    }
}
