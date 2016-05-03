package com.hts.mydemo.MD5;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hts.mydemo.R;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5Activity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md5);
        initView();
    }

    private void initView() {
        final EditText etIn = (EditText) findViewById(R.id.et_in);
        final TextView tvOut = (TextView) findViewById(R.id.tv_out);
        Button btnConvert = (Button) findViewById(R.id.btn_convert);
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String in = etIn.getText().toString().trim();
                tvOut.setText(getMd5(in));
            }
        });
    }

    private String getMd5(String in) {
        byte[] result = new byte[1024];
        try {
            result = MessageDigest.getInstance("MD5").digest(in.getBytes("UTF-8"));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : result) {
            int number = b & 0xff;
            String str = Integer.toHexString(number);
            if (str.length() == 1) {
                sb.append("0");
            }
            sb.append(str);
        }
        return sb.toString();
    }
}
