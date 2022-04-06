package kr.hs.dgsw.juyeop.interview.widget.manager

import android.content.Context
import android.content.SharedPreferences


object SharedPreferencesManager {
    private const val USER_ID = "userId"
    private const val USER_SOLUTION = "userSolution"

    fun getUserId(context: Context): String? {
        return getDefaultSharedPreferences(context).getString(USER_ID, null)
    }
    fun setUserId(context: Context, userId: String?) {
        getDefaultSharedPreferences(context).edit().putString(USER_ID, userId).apply()
    }

    fun getUserSolution(context: Context): Int? {
        return getDefaultSharedPreferences(context).getInt(USER_SOLUTION, 0)
    }
    fun setUserSolution(context: Context, userSolution: Int) {
        getDefaultSharedPreferences(context).edit().putInt(USER_SOLUTION, userSolution).apply()
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