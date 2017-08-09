package com.littleeven.baidumap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;

public class MainActivity extends AppCompatActivity {

    private MapView mMapView = null;
    private BaiduMap mBaiduMap;
    private Switch mHeatSwitch;
    private Switch mSatalliteSwitch;
    private Switch mTrafficSwitch;
    private Button mMultiMap;
    private Button mBusLine;
    private Button mRoutePlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 在使用 SDK 各组件之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.act_main);

        initId();
        //改变地图的状态，设置地图缩放级别
        //mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(20));
        mHeatSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    mBaiduMap.setBaiduHeatMapEnabled(true);
                } else {
                    mBaiduMap.setBaiduHeatMapEnabled(false);
                }
            }
        });
        mSatalliteSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                } else {
                    mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                }
            }
        });
        mTrafficSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    mBaiduMap.setTrafficEnabled(true);
                } else {
                    mBaiduMap.setTrafficEnabled(false);
                }
            }
        });
        mMultiMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MultiMapActivity.class);
                startActivity(intent);
            }
        });
        mBusLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,BusLineActivity.class);
                startActivity(intent);
            }
        });
        mRoutePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,RoutePlanActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initId() {
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        mHeatSwitch = (Switch) findViewById(R.id.heat_map_switch);
        mSatalliteSwitch = (Switch) findViewById(R.id.satellite_map_switch);
        mTrafficSwitch = (Switch) findViewById(R.id.traffic_map_switch);
        mBusLine = (Button) findViewById(R.id.busline_btn);
        mMultiMap = (Button) findViewById(R.id.multimap_btn);
        mRoutePlan = (Button) findViewById(R.id.routeplan_btn);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
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
}

