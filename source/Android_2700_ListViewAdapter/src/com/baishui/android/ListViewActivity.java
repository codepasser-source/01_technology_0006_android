package com.baishui.android;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.baishui.android.adapter.ListViewAdapter;
import com.baishui.android.entity.ObjectData;

public class ListViewActivity extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        List<ObjectData> objectList = new ArrayList<ObjectData>();
        ObjectData objectData1 = new ObjectData();
        objectData1.setId(1);
        objectData1.setDrawableId(R.drawable.folder);
        objectData1.setTitle("自定义List Item1");
        ObjectData objectData2 = new ObjectData();
        objectData2.setId(1);
        objectData2.setDrawableId(R.drawable.audio);
        objectData2.setTitle("自定义List Item2");
        ObjectData objectData3 = new ObjectData();
        objectData3.setId(1);
        objectData3.setDrawableId(R.drawable.pdf);
        objectData3.setTitle("自定义List Item3");
        objectList.add(objectData1);
        objectList.add(objectData2);
        objectList.add(objectData3);

        ListViewAdapter listViewAdapter = new ListViewAdapter(objectList, this);
        ListView listView = getListView();
        listView.setAdapter(listViewAdapter);
    }
}