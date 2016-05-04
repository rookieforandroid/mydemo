package com.hts.mydemo.SplashAndGuide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hts.mydemo.R;

import java.util.ArrayList;

/**
 * 引导界面
 */
public class SplashGuideActivity extends Activity {

    private ViewPager mViewPager;
    private LinearLayout llContainer;
    private ArrayList<ImageView> mImageViewList;
    private ArrayList<ImageView> mGuideDotList;
    private Button btnStart;
    //小红点
    private ImageView ivRedPoint;
    //小红点移动距离
    private int mPointDis;
    //引导页图片id数组
    private int[] mImageIds = new int[]{
            R.drawable.splash_guide_1, R.drawable.splash_guide_2, R.drawable.splash_guide_3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_guide);
        mViewPager = (ViewPager) findViewById(R.id.vp_guide);
        llContainer = (LinearLayout) findViewById(R.id.ll_container);
        btnStart = (Button) findViewById(R.id.btn_start);
        ivRedPoint = (ImageView) findViewById(R.id.iv_point_red);
        initData();
        mViewPager.setAdapter(new GuideAdapter());
        //开始体验按钮点击事件
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转主页面
                startActivity(new Intent(SplashGuideActivity.this, SplashHomeActivity.class));
                finish();
            }
        });

        //圆点间距计算 = 第二个圆点left值 -  第一个圆点left值
        //measure->layout->draw(onCreate方法执行结束之后才走此流程)
        //监听layout方法结束的时间，位置确定好之后再获取圆点间距
        ivRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    //layout方法执行结束的回调
                    @Override
                    public void onGlobalLayout() {
                        ivRedPoint.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        mPointDis = llContainer.getChildAt(1).getLeft()
                                - llContainer.getChildAt(0).getLeft();
                        System.out.println("圆点距离" + mPointDis);
                        //计算得出我的手机 圆点间距30
                    }
                });

        //页面滑动监听器
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             * 页面滑动过程中的回调
             * @param position 当前页面
             * @param positionOffset 移动偏移百分比
             * @param positionOffsetPixels 移动偏移像素
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //跟随页面移动更新小红点位置进行移动效果

                //计算小红点当前的左边距
                int leftMargin = (int) (mPointDis * positionOffset) + position * mPointDis;
                RelativeLayout.LayoutParams params = (
                        RelativeLayout.LayoutParams) ivRedPoint.getLayoutParams();
                //修改布局左边距
                params.leftMargin = leftMargin;
                //重新设置小红点布局参数
                ivRedPoint.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                //某个页面被选中,不设置固定参数，把图片列表长度作比较
                if (position==mImageViewList.size()-1){
                    btnStart.setVisibility(View.VISIBLE);
                }else {
                    btnStart.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //页面状态发生变化的回调
                //0什么都没做，1正在滑动，2滑动完毕，状态改变。
            }

        });


    }


    /**
     * 初始化数据
     */
    private void initData() {
        mImageViewList = new ArrayList<>();
        for (int i = 0; i < mImageIds.length; i++) {
            ImageView view = new ImageView(this);
            //设置图片填充布局，如果只是设置图片不填充用setImageResource
            view.setBackgroundResource(mImageIds[i]);
            //图片添加到图片列表里
            mImageViewList.add(view);

            //初始化小圆点
            ImageView point = new ImageView(this);
            //设置小圆点图片(实际是形状)
            point.setImageResource(R.drawable.shape_guide_point_gray);
            //初始化小圆点布局参数，宽高包裹内容，父控件是谁，就是谁声明的布局参数
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //从第二个小圆点开始设置边距
            if (i > 0) {
                //设置左边距
                params.leftMargin = 10;
            }
            point.setLayoutParams(params);
            //容器添加小圆点
            llContainer.addView(point);
        }
    }


    /**
     * 引导页面适配器
     */
    private class GuideAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mImageViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //初始化item的布局
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = mImageViewList.get(position);
            container.addView(view);
            return view;
        }

        //销毁item
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mImageViewList.get(position));
        }
    }

}
