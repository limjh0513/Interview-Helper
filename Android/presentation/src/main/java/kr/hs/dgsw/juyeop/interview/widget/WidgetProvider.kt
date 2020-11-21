package kr.hs.dgsw.juyeop.interview.widget

import android.app.AlarmManager
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.util.Log
import android.view.View
import android.widget.RemoteViews
import android.widget.Toast
import kr.hs.dgsw.juyeop.interview.R
import kr.hs.dgsw.juyeop.interview.view.activity.SplashActivity
import kr.hs.dgsw.juyeop.interview.widget.manager.SharedPreferencesManager

class WidgetProvider: AppWidgetProvider() {

    private val UPDATE = "android.appwidget.action.APPWIDGET_UPDATE"
    private val DISABLED = "android.appwidget.action.APPWIDGET_DISABLED"
    private val ENTER = "android.intent.action.enter"

    private lateinit var pendingIntent: PendingIntent
    private lateinit var alarmManager: AlarmManager

    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)

        when (intent!!.action) {
            UPDATE -> {
                removePreviousAlarm()

                val firstTime = System.currentTimeMillis()
                pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
                alarmManager = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                alarmManager.set(AlarmManager.RTC, firstTime, pendingIntent)
            }
            DISABLED -> {
                removePreviousAlarm()
            }
            ENTER -> {
                context!!.startActivity(Intent(context, SplashActivity::class.java).addFlags(FLAG_ACTIVITY_NEW_TASK))
            }
        }
    }

    override fun onUpdate(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)

        appWidgetIds?.forEach { appWidgetId ->
            val views: RemoteViews = addViews(context)
            appWidgetManager?.updateAppWidget(appWidgetId, views)
        }
    }

    override fun onDeleted(context: Context?, appWidgetIds: IntArray?) {
        super.onDeleted(context, appWidgetIds)
    }

    override fun onDisabled(context: Context?) {
        super.onDisabled(context)
    }

    private fun enterAction(context: Context?): PendingIntent {
        val intent = Intent(context, WidgetProvider::class.java)
        intent.action = ENTER

        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun addViews(context: Context?): RemoteViews {
        val views = RemoteViews(context?.packageName, R.layout.widget)

        if (SharedPreferencesManager.getUserId(context!!).isNullOrEmpty()) {
            views.setTextViewText(R.id.questionInfoTextView, "서비스를 로그인 후\n이용해주시기 바랍니다.")
            views.setViewVisibility(R.id.questionCountTextView, View.INVISIBLE)
        } else {
            views.setTextViewText(R.id.questionInfoTextView, "${SharedPreferencesManager.getUserId(context)}님이 답변한\n인터뷰 헬퍼의 면접 질문 수")
            views.setViewVisibility(R.id.questionCountTextView, View.VISIBLE)
            views.setTextViewText(R.id.questionCountTextView, "${SharedPreferencesManager.getUserSolution(context)}개")
        }
        views.setOnClickPendingIntent(R.id.layout, enterAction(context))
        return views
    }

    private fun removePreviousAlarm() {
        if (this::pendingIntent.isInitialized && this::alarmManager.isInitialized) {
            pendingIntent.cancel()
            alarmManager.cancel(pendingIntent)
        }
    }
}