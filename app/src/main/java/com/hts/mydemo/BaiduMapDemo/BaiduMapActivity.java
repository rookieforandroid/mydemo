package com.hts.mydemo.baidumapdemo;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.hts.mydemo.R;
import com.hts.mydemo.baidumapdemo.MyOrientationListener.OnOrientationListener;


public class BaiduMapActivity extends Activity {

    private Context context;

    private MapView mMapView;
    private BaiduMap mBaiduMap;

    //定位相关
    private LocationClient mLocationClient;
    private MyLocationListener mLocationListener;
    private boolean isFirstIn = true;
    private double mLatitude;   //维度
    private double mLongtitude; //经度
    //自定义定位图标
    private BitmapDescriptor mIconLocation;
    private MyOrientationListener myOrientationListener;
    //记录当前方向
    private float mCurrentX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_baidumap);
        //初始化context
        this.context = this;
        initView();
        //初始化定位
        initLocation();
    }

    private void initView() {
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        //地图状态更新，比例设置
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
        mBaiduMap.setMapStatus(msu);
    }

    private void initLocation() {
        mLocationClient = new LocationClient(this);
        mLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(mLocationListener);
        //实例化客户端定位参数
        LocationClientOption options = new LocationClientOption();
        options.setCoorType("bd09ll");  //返回定位结果
        options.setOpenGps(true);   //gps定位
        options.setIsNeedAddress(true); //设置定位结果中是否含地址信息，默认无
        options.setScanSpan(5000);  //设置定位扫描间隔时间

        mLocationClient.setLocOption(options);
        //初始化图标
        mIconLocation = BitmapDescriptorFactory.fromResource(R.drawable.baidudemo_orientation);
        //初始化传感监听器
        myOrientationListener = new MyOrientationListener(context);
        myOrientationListener.setOnOrientationListener(new OnOrientationListener() {
            @Override
            public void onOrientationChanged(float x) {
                mCurrentX = x;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_baidu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.map_type:
                if (mBaiduMap.getMapType() == BaiduMap.MAP_TYPE_NORMAL) {
                    mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                    item.setTitle("普通地图");
                } else if (mBaiduMap.getMapType() == BaiduMap.MAP_TYPE_SATELLITE) {
                    mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                    item.setTitle("卫星地图");
                }
                break;

            case R.id.map_traffic:
                if (mBaiduMap.isTrafficEnabled()) {
                    mBaiduMap.setTrafficEnabled(false);
                    item.setTitle("实时交通(on)");
                } else {
                    mBaiduMap.setTrafficEnabled(true);
                    item.setTitle("实时交通(off)");
                }
                break;

            case R.id.map_location:
                centerToMyLocation();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 定位监听器
     */
    private class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            //构造定位数据
            MyLocationData data = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius())   //设置位置的精度信息
                    .direction(mCurrentX)   //设置方向
                    .latitude(bdLocation.getLatitude()) //设置定位数据的纬度
                    .longitude(bdLocation.getLongitude())   //设置定位数据的经度
                    .build();
            mBaiduMap.setMyLocationData(data);

            //设置自定义图标
            MyLocationConfiguration config = new MyLocationConfiguration(
                    LocationMode.NORMAL, true, mIconLocation);
            mBaiduMap.setMyLocationConfigeration(config);

            //获取经纬度
            mLatitude = bdLocation.getLatitude();
            mLongtitude = bdLocation.getLongitude();

            //第一次定位
            if (isFirstIn) {
                centerToMyLocation();
                isFirstIn = false;
                //第一次定位弹出位置地址
                Toast.makeText(context, bdLocation.getAddrStr(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 定位到我的位置
     */
    private void centerToMyLocation() {
        LatLng latLng = new LatLng(mLatitude, mLongtitude);
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
        mBaiduMap.animateMapStatus(msu);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //开启定位允许
        mBaiduMap.setMyLocationEnabled(true);
        //是否已经开启定位，没有则开启
        if (!mLocationClient.isStarted()) {
            //开启定位
            mLocationClient.start();
        }
        //开启方向传感器监听
        myOrientationListener.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //关闭定位允许
        mBaiduMap.setMyLocationEnabled(false);
        //停止定位
        mLocationClient.stop();
        //停止方向传感器监听
        myOrientationListener.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

}
