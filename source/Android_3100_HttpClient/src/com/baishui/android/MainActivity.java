package com.baishui.android;

import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.baishui.android.util.CustomerHttpClient;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button postBT = (Button) this.findViewById(R.id.postBT);
        postBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://10.1.128.26/fxdsmobileoption/jsonapi/v1/simpleauth";
                BasicNameValuePair domain = new BasicNameValuePair("domain",
                        "DocuShare");
                BasicNameValuePair userName = new BasicNameValuePair(
                        "username", "admin");
                BasicNameValuePair password = new BasicNameValuePair(
                        "password", "admin");
                String result = CustomerHttpClient.httpPost(url, domain, userName, password);
                System.out.println(result);
            }
        });

        Button getBT = (Button) this.findViewById(R.id.getBT);
        getBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://www.baidu.com";
                String result = CustomerHttpClient.httpGet(url);
                System.out.println(result);
            }
        });
    }
}