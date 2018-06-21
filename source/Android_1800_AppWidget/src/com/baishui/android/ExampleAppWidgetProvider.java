package com.baishui.android;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

public class ExampleAppWidgetProvider extends AppWidgetProvider {

    // 删除APP时执行方法
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        System.out.println("--->onDeleted");
        super.onDeleted(context, appWidgetIds);
    }

    // 删除APP后执行的方法
    @Override
    public void onDisabled(Context context) {
        System.out.println("--->onDisabled");
        super.onDisabled(context);
    }

    // 第一次执行APP时执行的方法
    @Override
    public void onEnabled(Context context) {
        System.out.println("--->onEnabled");
        super.onEnabled(context);
    }

    // BroadcastReceiver,注册 APPWIDGET_UPDATE动作类型
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("--->onReceive");
        super.onReceive(context, intent);
    }

    // 更新时执行方法
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
            int[] appWidgetIds) {
        System.out.println("--->onUpdate");
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

}
