package com.application.addlocation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class Coordinate extends AppCompatActivity {

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinate);
        text = (TextView) findViewById(R.id.location);
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,//指定GPS定位的提供者
                1000,//间隔时间
                1,//位置间隔1米
                new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {

                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                    }
                }
        );
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);//获取最新的定位信息
        locationUpdates(location);
    }

    private void locationUpdates(Location location) {
        if (location != null) {
            //创建一个字符串构建器，用于记录定位信息
            String stringBuilder = "当前的位置信息：\n" +
                    "经度：" + location.getLongitude() + "\n" +
                    "纬度：" + location.getLatitude() + "\n" +
                    "高度：" + location.getAltitude() + "\n" +
                    "速度：" + location.getSpeed() + "\n" +
                    "方向：" + location.getBearing() + "\n" +
                    "定位精度：" + location.getAccuracy() + "\n";
            text.setText(stringBuilder);//显示到页面上
        } else {
            text.setText("没有获取到gps信息");//如果你经常显示，请把代码在手机上跑一下然后去室外去跑，建议关闭流量WiFi，经实证，是会得到经纬度信息的。
        }
    }
}