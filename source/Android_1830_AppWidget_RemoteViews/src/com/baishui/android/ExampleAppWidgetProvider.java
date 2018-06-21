package com.baishui.android;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class ExampleAppWidgetProvider extends AppWidgetProvider {

    private static final String UPDATE_APP_WIDGET = "com.baishui.android.APPWIDGET_UPDATE";

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
        String action = intent.getAction();
        if (UPDATE_APP_WIDGET.equals(action)) {
            System.out.println("onReceive Action : " + UPDATE_APP_WIDGET);
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.example_appwidget);
            remoteViews.setTextViewText(R.id.widgetTextId, "接收到一个广播");
            AppWidgetManager appWidgetManager = AppWidgetManager
                    .getInstance(context);
            ComponentName componentName = new ComponentName(context,
                    ExampleAppWidgetProvider.class);
            appWidgetManager.updateAppWidget(componentName, remoteViews);
        }
        super.onReceive(context, intent);
    }

    // 更新时执行方法
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
            int[] appWidgetIds) {
        System.out.println("--->onUpdate");
        for (int appWidgetId : appWidgetIds) {
            System.out.println("--->id:" + appWidgetId);
            // 创建一个Intent
            Intent intent = new Intent();
            // 设置action
            intent.setAction(UPDATE_APP_WIDGET);

            // 创建PendingIntent,使用getBroadcast方法创建，可以发送Broadcast
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                    0, intent, 0);
            // 创建RemoteViews
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.example_appwidget);
            // 为RemoteViews中的控件注册事件
            // 参数一：指定控件ID
            // 参数二：触发时intent对象
            remoteViews.setOnClickPendingIntent(R.id.widgetButtonId,
                    pendingIntent);
            // 更新当前的App应用，多个的情况分别注册
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

}
