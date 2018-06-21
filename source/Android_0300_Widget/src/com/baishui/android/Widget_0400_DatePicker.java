package com.baishui.android;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

/****
 * 
 * @author Administrator Android 常用控件的使用方法 例程
 */
public class Widget_0400_DatePicker extends Activity {
    // DataPickerDialog ID
    private static final int DATAPICKERDIALOG_ID = 1;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datepicker);
        Button datepickerBtn = (Button) this.findViewById(R.id.datepickerBtn);
        datepickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATAPICKERDIALOG_ID);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
        case DATAPICKERDIALOG_ID:
            return new DatePickerDialog(Widget_0400_DatePicker.this,
                    onDateSetListener, 2012, 7, 13);
            //注意月份是从0开始，其他从1开始
        }
        return null;
    }

    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear,
                int dayOfMonth) {
            Toast.makeText(Widget_0400_DatePicker.this,
                    year + "-" + monthOfYear + "-" + dayOfMonth,
                    Toast.LENGTH_LONG).show();
        }
    };

}