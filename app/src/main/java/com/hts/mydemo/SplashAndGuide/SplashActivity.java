package com.hts.mydemo.SplashAndGuide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.hts.mydemo.R;
import com.hts.mydemo.utils.PreferencesUtils;

/**
 * 闪屏页面
 */
public class SplashActivity extends Activity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        this.mContext = this;
        initView();
    }

    private void initView() {
        RelativeLayout rlRoot = (RelativeLayout) findViewById(R.id.rl_root);
        // 旋转动画 从0到360度旋转
        RotateAnimation animRotate = new RotateAnimation(
                0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animRotate.setDuration(1000);   //动画时间
        animRotate.setFillAfter(true);  //保持动画结束状态
        //缩放动画 从无到完整（0到1）
        ScaleAnimation animScale = new ScaleAnimation(
                0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animScale.setDuration(1000);
        animScale.setFillAfter(true);
        //渐变动画 从隐藏到全显 （0到1）
        AlphaAnimation animAlpha = new AlphaAnimation(0, 1);
        animAlpha.setDuration(2000);
        animAlpha.setFillAfter(true);

        //动画集合
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(animRotate);
        set.addAnimation(animScale);
        set.addAnimation(animAlpha);
        //启动动画
        rlRoot.startAnimation(set);
        //动画监听器，动画播放完毕跳转页面
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画结束，跳转界面，判断是否是第一次进入
                boolean isFirstEnter = PreferencesUtils.getBoolean(mContext, "splash_isFirst", true);

                if (isFirstEnter) {
                    //第一次进入则跳转引导界面
                    startActivity(new Intent(SplashActivity.this, SplashGuideActivity.class));
                    PreferencesUtils.putBoolean(mContext, "splash_isFirst", false);
                } else {
                    //不是第一次进入则跳转主界面
                    startActivity(new Intent(SplashActivity.this, SplashHomeActivity.class));
                }
                //关闭闪屏页面
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
