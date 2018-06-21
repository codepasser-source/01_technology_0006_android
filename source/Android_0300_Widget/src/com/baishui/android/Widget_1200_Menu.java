package com.baishui.android;

import com.baishui.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Button;
import android.widget.Toast;

public class Widget_1200_Menu extends Activity {

    private Button menuBT;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        menuBT = (Button) this.findViewById(R.id.menuBT);

        // 为文本域组册上下文菜单
        this.registerForContextMenu(menuBT);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        super.onCreateContextMenu(menu, v, menuInfo);
        // 此方法在每次调用上下文菜单时都会被调用一次
        if (v == findViewById(R.id.menuBT)) {
            menu.add(0, 1, 0, "菜单项1");
            menu.add(0, 2, 0, "菜单项2");
            menu.add(0, 3, 0, "菜单项3");
            menu.add(0, 4, 0, "菜单项4");
            menu.add(0, 5, 0, "菜单项5");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case 1:
            Toast.makeText(Widget_1200_Menu.this, "" + item.getItemId(),
                    Toast.LENGTH_SHORT).show();
            break;
        case 2:
            Toast.makeText(Widget_1200_Menu.this, "" + item.getItemId(),
                    Toast.LENGTH_SHORT).show();
            break;
        case 3:
            Toast.makeText(Widget_1200_Menu.this, "" + item.getItemId(),
                    Toast.LENGTH_SHORT).show();
            break;
        case 4:
            Toast.makeText(Widget_1200_Menu.this, "" + item.getItemId(),
                    Toast.LENGTH_SHORT).show();
            break;
        case 5:
            Toast.makeText(Widget_1200_Menu.this, "" + item.getItemId(),
                    Toast.LENGTH_SHORT).show();
            break;
        }

        // TODO Auto-generated method stub
        return super.onContextItemSelected(item);
    }

}