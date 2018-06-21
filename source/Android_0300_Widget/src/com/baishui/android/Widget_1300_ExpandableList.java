package com.baishui.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.widget.SimpleExpandableListAdapter;

public class Widget_1300_ExpandableList extends ExpandableListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expandablelist);
        List<Map<String, String>> groups = new ArrayList<Map<String, String>>();

        Map<String, String> group1 = new HashMap<String, String>();
        group1.put("groupName", "group1");
        Map<String, String> group2 = new HashMap<String, String>();
        group2.put("groupName", "group2");
        Map<String, String> group3 = new HashMap<String, String>();
        group3.put("groupName", "group3");

        groups.add(group1);
        groups.add(group2);
        groups.add(group3);

        List<Map<String, String>> group1Children = new ArrayList<Map<String, String>>();
        Map<String, String> group1ChildData1 = new HashMap<String, String>();
        group1ChildData1.put("childName", "group1 child1");
        Map<String, String> group1ChildData2 = new HashMap<String, String>();
        group1ChildData2.put("childName", "group1 child2");
        Map<String, String> group1ChildData3 = new HashMap<String, String>();
        group1ChildData3.put("childName", "group1 child3");
        group1Children.add(group1ChildData1);
        group1Children.add(group1ChildData2);
        group1Children.add(group1ChildData3);

        List<Map<String, String>> group2Children = new ArrayList<Map<String, String>>();
        Map<String, String> group2ChildData1 = new HashMap<String, String>();
        group2ChildData1.put("childName", "group1 child1");
        Map<String, String> group2ChildData2 = new HashMap<String, String>();
        group2ChildData2.put("childName", "group1 child2");
        Map<String, String> group2ChildData3 = new HashMap<String, String>();
        group2ChildData3.put("childName", "group1 child3");
        group2Children.add(group2ChildData1);
        group2Children.add(group2ChildData2);
        group2Children.add(group2ChildData3);

        List<Map<String, String>> group3Children = new ArrayList<Map<String, String>>();
        Map<String, String> group3ChildData1 = new HashMap<String, String>();
        group3ChildData1.put("childName", "group1 child1");
        Map<String, String> group3ChildData2 = new HashMap<String, String>();
        group3ChildData2.put("childName", "group1 child2");
        Map<String, String> group3ChildData3 = new HashMap<String, String>();
        group3ChildData3.put("childName", "group1 child3");
        group3Children.add(group3ChildData1);
        group3Children.add(group3ChildData2);
        group3Children.add(group3ChildData3);

        List<List<Map<String, String>>> children = new ArrayList<List<Map<String, String>>>();
        children.add(group1Children);
        children.add(group2Children);
        children.add(group3Children);

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this, groups, R.layout.group, new String[] { "groupName" },
                new int[] { R.id.groupTo }, children, R.layout.child,
                new String[] { "childName" }, new int[] { R.id.childTo });
        setListAdapter(adapter);
    }
}