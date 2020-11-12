package kr.hs.dgsw.juyeop.interview.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import kr.hs.dgsw.juyeop.interview.R

class WidgetProvider : AppWidgetProvider() {

    private val MY_ACTION = "android.action.MY_ACTION"

    override fun onUpdate(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        appWidgetIds?.forEach { appWidgetId ->
            val views: RemoteViews = addViews(context)
            appWidgetManager?.updateAppWidget(appWidgetId, views)
        }
    }
    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
    }
    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
    }
    override fun onDisabled(context: Context?) {
        super.onDisabled(context)
    }
    override fun onDeleted(context: Context?, appWidgetIds: IntArray?) {
        super.onDeleted(context, appWidgetIds)
    }

    private fun addViews(context: Context?): RemoteViews {
        val views = RemoteViews(context?.packageName, R.layout.widget)
        return views
    }
}