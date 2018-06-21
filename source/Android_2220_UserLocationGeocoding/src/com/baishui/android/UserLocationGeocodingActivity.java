package com.baishui.android;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class UserLocationGeocodingActivity extends Activity {

    private Button getAddressBT;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        getAddressBT = (Button) this.findViewById(R.id.GetAddressBT);
        getAddressBT.setOnClickListener(new AddressBTOnClick());
    }

    private class AddressBTOnClick implements OnClickListener {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            new AddressTask().execute();
        }

    }

    private class AddressTask extends AsyncTask<Integer, Integer, Integer> {

        @Override
        protected Integer doInBackground(Integer... params) {
            try {
                Geocoder geocoding = new Geocoder(
                        UserLocationGeocodingActivity.this, Locale.CHINESE);
                /*
                 * List<Address> addresses =
                 * geocoding.getFromLocation(40.714221, -73.961452, 1);
                 */
                List<Address> addresses = geocoding
                        .getFromLocationName("大连", 1);
                // 或者通过Http请求做到这一点。
                // http://maps.googleapis.com/maps/api/geocode/json?address=%E5%A4%A7%E8%BF%9E&sensor=false
                for (Address address : addresses) {
                    System.out.println(address);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
        }

    }
}
