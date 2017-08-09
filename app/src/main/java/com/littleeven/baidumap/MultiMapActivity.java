package com.littleeven.baidumap;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.baidu.mapapi.map.LogoPosition;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.SupportMapFragment;
import com.baidu.mapapi.model.LatLng;


public class MultiMapActivity extends FragmentActivity {

    private static final LatLng GEO_BEIJING = new LatLng(39.945, 116.404);
    private static final LatLng GEO_LANZHOU = new LatLng(36.52,103.51);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_multimap);
        /*北京为地图中心，logo在左上角*/
        MapStatusUpdate u1 = MapStatusUpdateFactory.newLatLng(GEO_BEIJING);
        SupportMapFragment map1 = (SupportMapFragment) (getSupportFragmentManager()
                .findFragmentById(R.id.map1));
        map1.getBaiduMap().setMapStatus(u1);
        map1.getMapView().setLogoPosition(LogoPosition.logoPostionleftTop);

        /*兰州为地图中心，logo在左上角*/
        MapStatusUpdate u2 = MapStatusUpdateFactory.newLatLng(GEO_LANZHOU);
        SupportMapFragment map2 = (SupportMapFragment) (getSupportFragmentManager()
                .findFragmentById(R.id.map2));
        map2.getBaiduMap().setMapStatus(u2);
        map2.getMapView().setLogoPosition(LogoPosition.logoPostionleftTop);
    }
}
