package com.baishui.android;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

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

    // BroadcastReceiver,注册
    // APPWIDGET_UPDATE动作类型，分发功能执行onDeleted、onDisabled、onEnabled、onUpdate方法
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
        for (int appWidgetId : appWidgetIds) {
            System.out.println("--->id:" + appWidgetId);
            // 创建一个Intent
            Intent intent = new Intent(context, TargetActivity.class);
            // 创建PendingIntent,使用getActivity方法创建
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                    intent, 0);
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
