package com.baishui.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/****
 * 
 * @author Administrator Android 常用控件的使用方法 例程
 */
public class Widget_0100_EditText extends Activity {
    private EditText edit_text1;
    private EditText edit_text2;
    private TextView textView;
    private Button button;

    public EditText getEdit_text1() {
        return edit_text1;
    }

    public void setEdit_text1(EditText editText1) {
        edit_text1 = editText1;
    }

    public EditText getEdit_text2() {
        return edit_text2;
    }

    public void setEdit_text2(EditText editText2) {
        edit_text2 = editText2;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edittext);

        this.edit_text1 = (EditText) this.findViewById(R.id.edit_text1);
        this.edit_text2 = (EditText) this.findViewById(R.id.edit_text2);
        this.button = (Button) this.findViewById(R.id.commit_button);

        this.button.setOnClickListener(new Mylisteners());
    }

    // 点击按钮事件
    class Mylisteners implements OnClickListener {
        @Override
        public void onClick(View v) {

            String para1 = edit_text1.getText().toString();
            String para2 = edit_text2.getText().toString();
            String value = Integer.toString(Integer.parseInt(para1)
                    * Integer.parseInt(para2));
            Toast.makeText(Widget_0100_EditText.this, value, Toast.LENGTH_LONG)
                    .show();
        }
    }

    /***
     * 点击menu 按钮时 会调用此方法 复写onCreateOptionsMenu方法 可以创建自定义菜单
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 参数 ： 组id 本身id 排序位置 显示的title
        menu.add(0, 1, 1, R.string.exit_caption);
        menu.add(0, 2, 2, R.string.about_caption);

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 复写onOptionsItemSelected 点击自创建的菜单项时 就会执行此方法
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // 通过点击的item本身的ID来判断点击的是哪一个按钮
        if (item.getItemId() == 1) {
            finish(); // 退出应用程序的方法
        } else if (item.getItemId() == 2) {

        }
        return super.onOptionsItemSelected(item);
    }

}