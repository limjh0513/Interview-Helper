package kr.hs.dgsw.juyeop.interview.widget.manager

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesManager {
    private const val USER_ID = "userId"

    fun getUserId(context: Context): String? {
        return getDefaultSharedPreferences(context).getString(USER_ID, null)
    }
    fun setUserId(context: Context, userId: String){
        getDefaultSharedPreferences(context).edit().putString(USER_ID, userId).apply()
    }

    private fun getDefaultSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(getDefaultSharedPreferencesName(context), getDefaultSharedPreferencesMode())
    }
    private fun getDefaultSharedPreferencesName(context: Context): String {
        return context.packageName.toString() + "_preferences"
    }
    private fun getDefaultSharedPreferencesMode(): Int {
        return Context.MODE_PRIVATE
    }
}