package com.baishui.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/****
 * 
 * @author Administrator Android 常用控件的使用方法 例程
 */
public class Widget_0300_Spinner extends Activity {

    // 声明Spinner对象
    private Spinner spinner;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinner);

        // 获取Spinner对象
        spinner = (Spinner) this.findViewById(R.id.spinnerId);
        // 使用ArrayAdapter绑定数据
        // 参数1：上下文
        // 参数2：数据数组
        // 参数3：布局文件，使用的时android默认定义的布局文件
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.item_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // 动态创建spinner item
        /*
         * List<String> itemList = new ArrayList<String>();
         * itemList.add("item7"); itemList.add("item8"); ArrayAdapter<String>
         * adapter = new ArrayAdapter<String>( this,
         * R.layout.spinner_list_item,R.id.itemTextView,itemList);
         */

        spinner.setAdapter(adapter);
        spinner.setPrompt("测试Spinner");
        spinner.setOnItemSelectedListener(new SpinnerItemClickListener());
    }

    // 列表选项监听器
    class SpinnerItemClickListener implements OnItemSelectedListener {

        // 选项被选中时
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view,
                int position, long id) {
            String selected = adapterView.getItemAtPosition(position)
                    .toString();
            System.out.println(selected);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            System.out.println("nothing selected");
        }

    }
}