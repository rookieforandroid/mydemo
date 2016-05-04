package com.hts.mydemo.SplashAndGuide;

import android.app.Activity;
import android.os.Bundle;

import com.hts.mydemo.R;

/**
 * Splash跳转到的主界面
 */
public class SplashHomeActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_home);
    }
}
