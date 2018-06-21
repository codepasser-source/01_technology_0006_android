package com.baishui.android.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baishui.android.R;
import com.baishui.android.entity.ObjectData;

public class ListViewAdapter extends BaseAdapter {

    private List<ObjectData> objectDataList = null;

    private Map<Integer, View> viewMap = new HashMap<Integer, View>();

    private Context context = null;

    public ListViewAdapter(List<ObjectData> objectDataList, Context context) {
        this.objectDataList = objectDataList;
        this.context = context;
    }

    // 返回有多少个Item
    @Override
    public int getCount() {
        return objectDataList.size();
    }

    // 根据position获取Item对象
    @Override
    public Object getItem(int position) {
        return objectDataList.get(position);
    }

    // 根据position获取Item对象的ID,有需要时可以重写
    @Override
    public long getItemId(int position) {
        return objectDataList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = viewMap.get(position);
        if (itemView == null) {
            System.out.println("---getView:" + position);
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            itemView = layoutInflater.inflate(R.layout.list_item, null);
            ImageView itemImgView = (ImageView) itemView
                    .findViewById(R.id.obj_img);
            TextView itemTextView = (TextView) itemView
                    .findViewById(R.id.obj_title);
            Button itemButton = (Button) itemView.findViewById(R.id.obj_check);
            ObjectData objData = (ObjectData) getItem(position);
            itemImgView.setBackgroundResource(objData.getDrawableId());
            itemTextView.setText(objData.getTitle());
            viewMap.put(position, itemView);

            ViewOnClickListener viewOnClickListener = new ViewOnClickListener(
                    objData.getTitle(), context);
            itemButton.setOnClickListener(viewOnClickListener);
        }
        return itemView;
    }

    private class ViewOnClickListener implements View.OnClickListener {
        private String text = "";

        private Context context;

        public ViewOnClickListener(String text, Context context) {
            super();
            this.text = text;
            this.context = context;
        }

        public String getText() {
            return text;
        }

        public Context getContext() {
            return context;
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(getContext(), getText(), Toast.LENGTH_LONG).show();
        }
    }
}
