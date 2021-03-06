package com.littleeven.baidumap;

import android.graphics.Color;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.busline.BusLineResult;

import java.util.ArrayList;
import java.util.List;


//显示一条公交详情结果
public class BusLineOverlay extends OverlayManager {

    private BusLineResult mBusLineResult = null;

    // 构造函数 baiduMap 该BusLineOverlay所引用的 BaiduMap 对象
    public BusLineOverlay(BaiduMap baiduMap) {
        super(baiduMap);
    }

    //设置公交线数据
    public void setData(BusLineResult result) {
        this.mBusLineResult = result;
    }

    @Override
    public final List<OverlayOptions> getOverlayOptions() {

        if (mBusLineResult == null || mBusLineResult.getStations() == null) {
            return null;
        }
        List<OverlayOptions> overlayOptionses = new ArrayList<OverlayOptions>();
        for (BusLineResult.BusStation station : mBusLineResult.getStations()) {
            overlayOptionses.add(new MarkerOptions()
                    .position(station.getLocation())
                    .zIndex(10)
                    .anchor(0.5f, 0.5f)
                    .icon(BitmapDescriptorFactory.fromAssetWithDpi("Icon_bus_station.png")));
        }
        List<LatLng> points = new ArrayList<LatLng>();
        for (BusLineResult.BusStep step : mBusLineResult.getSteps()) {
            if (step.getWayPoints() != null) {
                points.addAll(step.getWayPoints());
            }
        }
        if (points.size() > 0) {
            overlayOptionses.add(new PolylineOptions().width(10)
                    .color(Color.argb(178, 0, 78, 255)).zIndex(0)
                    .points(points));
        }
        return overlayOptionses;
    }

    //覆写此方法以改变默认点击行为,返回是否处理了该点击事件
    public boolean onBusStationClick(int index) {
        if (mBusLineResult.getStations() != null
                && mBusLineResult.getStations().get(index) != null) {
        }
        return false;
    }

    public final boolean onMarkerClick(Marker marker) {
        if (mOverlayList != null && mOverlayList.contains(marker)) {
            return onBusStationClick(mOverlayList.indexOf(marker));
        } else {
            return false;
        }
    }

    @Override
    public boolean onPolylineClick(Polyline polyline) {
        return false;
    }
}