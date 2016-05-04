package com.hts.mydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hts.mydemo.BaiduMapDemo.BaiduMapActivity;
import com.hts.mydemo.Banner.BannerActivity;
import com.hts.mydemo.MD5.MD5Activity;
import com.hts.mydemo.SplashAndGuide.SplashActivity;
import com.hts.mydemo.ViewPagerDemo.ViewPagerDemo;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        MyClickListener listener;
        listener = new MyClickListener();
        Button btnBanner = (Button) findViewById(R.id.btn_banner);
        Button btnBaiduMap = (Button) findViewById(R.id.btn_baidumap);
        Button btnMD5 = (Button) findViewById(R.id.btn_md5);
        Button btnViewPagerDemo = (Button) findViewById(R.id.btn_viewpager_demo);
        Button btnSplash = (Button) findViewById(R.id.btn_splash);
        btnBanner.setOnClickListener(listener);
        btnBaiduMap.setOnClickListener(listener);
        btnMD5.setOnClickListener(listener);
        btnViewPagerDemo.setOnClickListener(listener);
        btnSplash.setOnClickListener(listener);
    }

    private class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = null;
            switch (view.getId()) {
                case R.id.btn_banner:
                    intent = new Intent(MainActivity.this, BannerActivity.class);
                    break;
                case R.id.btn_baidumap:
                    intent = new Intent(MainActivity.this, BaiduMapActivity.class);
                    break;
                case R.id.btn_md5:
                    intent = new Intent(MainActivity.this, MD5Activity.class);
                    break;
                case R.id.btn_viewpager_demo:
                    intent = new Intent(MainActivity.this, ViewPagerDemo.class);
                    break;
                case R.id.btn_splash:
                    intent = new Intent(MainActivity.this, SplashActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }

}
