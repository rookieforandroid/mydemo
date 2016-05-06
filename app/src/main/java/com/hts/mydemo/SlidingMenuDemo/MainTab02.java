package com.hts.mydemo.slidingmenudemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hts.mydemo.R;

public class MainTab02 extends Fragment
{

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View messageLayout = inflater.inflate(R.layout.slidingdemo_tab_02, container, false);
		return messageLayout;
	}

}
