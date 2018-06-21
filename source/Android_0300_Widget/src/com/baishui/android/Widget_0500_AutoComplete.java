package com.baishui.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

/****
 * 
 * @author Administrator Android 常用控件的使用方法 例程
 */
public class Widget_0500_AutoComplete extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autocompletetextview);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) this
                .findViewById(R.id.autoCompleteTextViewId);

        // 第一种方式使用Android默认样式
        //参数1：上下文
        //参数2：数组或list对象
        //参数3:布局文件ID，注意布局文件中必须含有一个TextView控件
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.item_array, R.layout.autocomplete_list_item);
        // 第二种方式动态创建
        //注意布局文件autocomplete_list_item中只含有TextView标签
        /*
         * List<String> itemList = new ArrayList<String>();
         * itemList.add("aaa1"); itemList.add("aaa2"); itemList.add("aaa3");
         * ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
         * R.layout.autocomplete_list_item, itemList);
         */

        autoCompleteTextView.setAdapter(adapter);

    }

}