package com.baishui.android;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class UserLocationCriteriaActivity extends Activity {

    private Button allproviderBT;
    private Button providerBT;
    private LocationManager locationManager;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        locationManager = (LocationManager) UserLocationCriteriaActivity.this
                .getSystemService(Context.LOCATION_SERVICE);

        allproviderBT = (Button) this.findViewById(R.id.AllProviderBT);
        allproviderBT.setOnClickListener(new AllProviderBTOnClick());

        providerBT = (Button) this.findViewById(R.id.ProviderBT);
        providerBT.setOnClickListener(new ProviderBTOnClick());
    }

    private class AllProviderBTOnClick implements OnClickListener {

        @Override
        public void onClick(View v) {
            List<String> providerList = locationManager.getAllProviders();
            for (String provider : providerList) {
                System.out.println(provider);
            }
        }
    }

    private class ProviderBTOnClick implements OnClickListener {

        @Override
        public void onClick(View v) {
            // 创建Criteria对象.
            Criteria criteria = new Criteria();
            // 设置查询条件.
            criteria.setAccuracy(Criteria.ACCURACY_FINE);
            criteria.setPowerRequirement(Criteria.POWER_HIGH);
            criteria.setAltitudeRequired(false);
            criteria.setSpeedRequired(false);
            criteria.setCostAllowed(false);
            // enableonly 是否忽略Provide状态
            String bestProvider = locationManager.getBestProvider(criteria,
                    false);
            System.out.println(bestProvider);
            locationManager.requestLocationUpdates(bestProvider, 5000, 5000,
                    new OnLocationListener());
        }
    }

    private class OnLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            // TODO Auto-generated method stub
            System.out.println(location.getLatitude() + "-"
                    + location.getLongitude());
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