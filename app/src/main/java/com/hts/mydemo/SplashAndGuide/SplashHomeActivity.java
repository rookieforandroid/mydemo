package com.hts.mydemo.splashandguide;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hts.mydemo.R;
import com.hts.mydemo.utils.PreferencesUtils;

/**
 * Splash跳转到的主界面
 */
public class SplashHomeActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_home);
        Button btnReset = (Button) findViewById(R.id.btn_reset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferencesUtils.putBoolean(SplashHomeActivity.this,"splash_isFirst",true);
                Toast.makeText(SplashHomeActivity.this,"设置成功",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
