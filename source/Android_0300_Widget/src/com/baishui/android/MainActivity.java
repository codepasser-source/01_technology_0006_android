package com.baishui.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button editTextTestBT = (Button) this.findViewById(R.id.editTextTestBT);
        editTextTestBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Widget_0100_EditText.class);
                MainActivity.this.startActivity(intent);
            }
        });

        Button imageButtonTestBT = (Button) this
                .findViewById(R.id.imageButtonTestBT);
        imageButtonTestBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,
                        Widget_0200_ImageButton.class);
                MainActivity.this.startActivity(intent);
            }
        });

        Button spinnerTestBT = (Button) this.findViewById(R.id.spinnerTestBT);
        spinnerTestBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Widget_0300_Spinner.class);
                MainActivity.this.startActivity(intent);
            }
        });

        Button datePickerTestBT = (Button) this
                .findViewById(R.id.datePickerTestBT);
        datePickerTestBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Widget_0400_DatePicker.class);
                MainActivity.this.startActivity(intent);
            }
        });

        Button autocompleteTestBT = (Button) this
                .findViewById(R.id.autocompleteTestBT);
        autocompleteTestBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,
                        Widget_0500_AutoComplete.class);
                MainActivity.this.startActivity(intent);
            }
        });

        Button radioGroupTestBT = (Button) this
                .findViewById(R.id.radioGroupTestBT);
        radioGroupTestBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Widget_0600_RadioGroup.class);
                MainActivity.this.startActivity(intent);
            }
        });

        Button checkBoxTestBT = (Button) this.findViewById(R.id.checkBoxTestBT);
        checkBoxTestBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Widget_0700_CheckBox.class);
                MainActivity.this.startActivity(intent);
            }
        });

        Button progressBarTestBT = (Button) this
                .findViewById(R.id.progressBarTestBT);
        progressBarTestBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,
                        Widget_0800_ProgressBar.class);
                MainActivity.this.startActivity(intent);
            }
        });

        Button listViewTestBT = (Button) this.findViewById(R.id.listViewTestBT);
        listViewTestBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Widget_0900_ListView.class);
                MainActivity.this.startActivity(intent);
            }
        });

        Button videoViewTestBT = (Button) this
                .findViewById(R.id.videoViewTestBT);
        videoViewTestBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Widget_1000_VideoView.class);
                MainActivity.this.startActivity(intent);
            }
        });

        Button surfaceViewTestBT = (Button) this
                .findViewById(R.id.surfaceViewTestBT);
        surfaceViewTestBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,
                        Widget_1100_SurfaceView.class);
                MainActivity.this.startActivity(intent);
            }
        });

        Button menuTestBT = (Button) this.findViewById(R.id.menuTestBT);
        menuTestBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Widget_1200_Menu.class);
                MainActivity.this.startActivity(intent);
            }
        });

        Button expandableListTestBT = (Button) this
                .findViewById(R.id.expandableListTestBT);
        expandableListTestBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,
                        Widget_1300_ExpandableList.class);
                MainActivity.this.startActivity(intent);
            }
        });

        Button seekBarTestBT = (Button) this.findViewById(R.id.seekBarTestBT);
        seekBarTestBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Widget_1400_SeekBar.class);
                MainActivity.this.startActivity(intent);
            }
        });

        Button ratingBarTestBT = (Button) this.findViewById(R.id.ratingBarTestBT);
        ratingBarTestBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Widget_1500_RatingBar.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }
}
