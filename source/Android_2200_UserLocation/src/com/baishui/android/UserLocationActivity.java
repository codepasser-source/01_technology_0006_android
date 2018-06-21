package com.baishui.android;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class UserLocationActivity extends Activity {

    private Button locationBT;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        locationBT = (Button) findViewById(R.id.locationBT);

        locationBT.setOnClickListener(new LocationButtonOnClick());
    }

    private class LocationButtonOnClick implements OnClickListener {

        @Override
        public void onClick(View v) {
            // 注意：需要配置权限。
            // <!-- 使用Networking 定位权限（精确定位） -->
            // <uses-permission
            // android:name="android.permission.ACCESS_FINE_LOCATION" />
            // <!-- 使用Networking 定位权限（粗略定位） -->
            // <uses-permission
            // android:name="android.permission.ACCESS_COARSE_LOCATION" />
            // 1、得到LocationManager对象。
            LocationManager locationMG = (LocationManager) UserLocationActivity.this
                    .getSystemService(Context.LOCATION_SERVICE);
            // 2、绑定用户定位监听器。
            locationMG.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
                    0, new LocationOnChange());
        }
    }

    private class LocationOnChange implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {

            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            Toast.makeText(UserLocationActivity.this,
                    longitude + "-" + latitude, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub

        }

    }

}