package com.baishui.android;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Widget_0900_ListView extends ListActivity {

    private ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
    private HashMap<String, String> dataMap1 = new HashMap<String, String>();
    private HashMap<String, String> dataMap2 = new HashMap<String, String>();
    private HashMap<String, String> dataMap3 = new HashMap<String, String>();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listviewactivity);

        dataMap1.put("user_name", "张三");
        dataMap1.put("user_ip", "192.168.1.2");
        dataMap2.put("user_name", "李四");
        dataMap2.put("user_ip", "192.168.1.3");
        dataMap3.put("user_name", "王五");
        dataMap3.put("user_ip", "192.168.1.4");

        dataList.add(dataMap1);
        dataList.add(dataMap2);
        dataList.add(dataMap3);

        SimpleAdapter listAdapter = new SimpleAdapter(this, dataList,
                R.layout.listview, new String[] { "user_name", "user_ip" },
                new int[] { R.id.user_name, R.id.user_ip });
        setListAdapter(listAdapter);

    }

    /**
     * ListView list list对象 View view 被点击的对象 int position 被点击 int id id
     */
    @Override
    protected void onListItemClick(ListView listView, View view, int position,
            long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(listView, view, position, id);
        Toast.makeText(
                Widget_0900_ListView.this,
                "ip:" + dataList.get(position).get("user_ip") + "name:"
                        + dataList.get(position).get("user_name"),
                Toast.LENGTH_SHORT).show();
    }

}