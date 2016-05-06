package com.hts.mydemo.slidingmenudemo;

import android.app.PendingIntent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.print.PrintHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hts.mydemo.R;

/**
 * 右侧边栏fragment
 * Created by Chao on 2016/5/5.
 */
public class MenuRightFragment extends Fragment {

    private View mView;

    private TextView tvUserInfo, tvQRCode, tvTCWeibo, tvAddFriend, tvUserName, tvBelieveSelf, tvTalkBackgroud, tvSetting, tvPlugIn, tvBlackNumber, tvTraffic, tvSystemInforms, tvPrivacy, tvHelpFeedback, tvAboutWeixin, tvDeleteAll;

    private Button btnExit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.slidingdemo_right_menu, container, false);
        }
        initView(mView);
        return mView;
    }

    private void initView(View mView) {
        myClickListener listener = new myClickListener();
        tvUserInfo = (TextView) mView.findViewById(R.id.tv_user_info);
        tvQRCode = (TextView) mView.findViewById(R.id.tv_qr_code);
        tvTCWeibo = (TextView) mView.findViewById(R.id.tv_tc_weibo);
        tvAddFriend = (TextView) mView.findViewById(R.id.tv_add_friend);
        tvUserName = (TextView) mView.findViewById(R.id.tv_my_user_name);
        tvBelieveSelf = (TextView) mView.findViewById(R.id.tv_believe_self);
        tvTalkBackgroud = (TextView) mView.findViewById(R.id.tv_talk_backgroud);
        tvSetting = (TextView) mView.findViewById(R.id.tv_setting);
        tvPlugIn = (TextView) mView.findViewById(R.id.tv_plug_in);
        tvBlackNumber = (TextView) mView.findViewById(R.id.tv_black_number);
        tvTraffic = (TextView) mView.findViewById(R.id.tv_traffic);
        tvSystemInforms = (TextView) mView.findViewById(R.id.tv_system_informs);
        tvPrivacy = (TextView) mView.findViewById(R.id.tv_privacy);
        tvHelpFeedback = (TextView) mView.findViewById(R.id.tv_help_feedback);
        tvAboutWeixin = (TextView) mView.findViewById(R.id.tv_about_weixin);
        tvDeleteAll = (TextView) mView.findViewById(R.id.tv_delete_all);
        btnExit = (Button) mView.findViewById(R.id.btn_exit);

        //点击事件监听注册
        tvUserInfo.setOnClickListener(listener);
        tvQRCode.setOnClickListener(listener);
        tvTCWeibo.setOnClickListener(listener);
        tvAddFriend.setOnClickListener(listener);
        tvUserName.setOnClickListener(listener);
        tvBelieveSelf.setOnClickListener(listener);
        tvTalkBackgroud.setOnClickListener(listener);
        tvSetting.setOnClickListener(listener);
        tvPlugIn.setOnClickListener(listener);
        tvBlackNumber.setOnClickListener(listener);
        tvTraffic.setOnClickListener(listener);
        tvSystemInforms.setOnClickListener(listener);
        tvPrivacy.setOnClickListener(listener);
        tvHelpFeedback.setOnClickListener(listener);
        tvAboutWeixin.setOnClickListener(listener);
        tvDeleteAll.setOnClickListener(listener);
        btnExit.setOnClickListener(listener);
    }

    private class myClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tv_user_info:
                    Toast.makeText(getActivity(), tvUserInfo.getText().toString(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_qr_code:
                    Toast.makeText(getActivity(), tvQRCode.getText().toString(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_tc_weibo:
                    Toast.makeText(getActivity(), tvTCWeibo.getText().toString(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_add_friend:
                    Toast.makeText(getActivity(), tvAddFriend.getText().toString(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_my_user_name:
                    Toast.makeText(getActivity(), tvUserName.getText().toString(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_believe_self:
                    Toast.makeText(getActivity(), tvBelieveSelf.getText().toString(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_talk_backgroud:
                    Toast.makeText(getActivity(), tvTalkBackgroud.getText().toString(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_setting:
                    Toast.makeText(getActivity(), tvSetting.getText().toString(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_plug_in:
                    Toast.makeText(getActivity(), tvPlugIn.getText().toString(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_traffic:
                    Toast.makeText(getActivity(), tvTraffic.getText().toString(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_system_informs:
                    Toast.makeText(getActivity(), tvSystemInforms.getText().toString(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_privacy:
                    Toast.makeText(getActivity(), tvPrivacy.getText().toString(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_help_feedback:
                    Toast.makeText(getActivity(), tvHelpFeedback.getText().toString(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_about_weixin:
                    Toast.makeText(getActivity(), tvAboutWeixin.getText().toString(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_delete_all:
                    Toast.makeText(getActivity(), tvDeleteAll.getText().toString(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_exit:
                    Toast.makeText(getActivity(), btnExit.getText().toString(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
