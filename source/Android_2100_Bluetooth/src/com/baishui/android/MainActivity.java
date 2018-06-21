package com.baishui.android;

import java.util.Iterator;
import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    private Button searchBT;
    private Button visibleBT;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        visibleBT = (Button) findViewById(R.id.visibleBT);
        visibleBT.setOnClickListener(new VisibleOnClickListener());

        searchBT = (Button) findViewById(R.id.searchBT);
        searchBT.setOnClickListener(new SearchOnClickListener());
    }

    private class VisibleOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            // 设置蓝牙可见
            Intent intent = new Intent(
                    BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            // 设置蓝牙可见时间
            intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 200);
            startActivity(intent);
        }
    }

    private class SearchOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            // 1、得到BluetoothAdapter
            BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
            if (adapter != null) {
                Toast.makeText(MainActivity.this, "本机拥有蓝牙设备",
                        Toast.LENGTH_SHORT).show();
                // 2、判断蓝牙状态
                if (!adapter.isEnabled()) {
                    // 3、启动蓝牙设备
                    Intent intent = new Intent(
                            BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivity(intent);
                }
                // 4、扫描设备，每扫描到一个设备就发送一次广播
                adapter.startDiscovery();
                // 5、获取设备
                Set<BluetoothDevice> devices = adapter.getBondedDevices();
                if (devices.size() > 0) {
                    for (Iterator<BluetoothDevice> iterator = devices
                            .iterator(); iterator.hasNext();) {
                        BluetoothDevice deveice = iterator.next();
                        Toast.makeText(MainActivity.this, deveice.getAddress(),
                                Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(MainActivity.this, "本机没有蓝牙设备",
                        Toast.LENGTH_SHORT).show();
            }

        }
    }
}