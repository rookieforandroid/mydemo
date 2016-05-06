package com.hts.mydemo.slidingmenudemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.hts.mydemo.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 左侧边栏fragment
 * Created by Chao on 2016/5/5.
 */
public class MenuLeftFragment extends ListFragment {

    private View mView;
    private ArrayAdapter<String> adapter;
    private List<String> mDatas = Arrays
            .asList("聊天", "发现", "通讯录", "朋友圈", "订阅号"
                    , "f1", "f2", "f3", "f4", "f5", "f6", "f7"
                    , "f8", "f9", "f10", "f11", "f12");

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //定义一个数组
        List<String> data = new ArrayList<String>();
        for (int i = 0; i < mDatas.size(); i++) {
            data.add(mDatas.get(i));
        }
        //将数组加到ArrayAdapter当中
        adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, data);
        //绑定适配器时，必须通过ListFragment.setListAdapter()接口，而不是ListView.setAdapter()或其它方法
        setListAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.slidingdemo_left_menu, container, false);
        }
        return mView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String item = adapter.getItem(position);
        Toast.makeText(getActivity(), item, Toast.LENGTH_SHORT).show();
    }
}
