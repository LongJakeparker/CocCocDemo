package com.jakeparker.coccocdemo

import android.content.Context
import android.content.SharedPreferences

/**
 * @author Long Tran
 * @since 02/01/2021
 */
class Preferences private constructor(val context: Context) {

    companion object {
        private const val PREF_NAME = BuildConfig.APPLICATION_ID + "app.local.Preferences"
        private const val IS_DARK_MODE = "IS_DARK_MODE"

        @Volatile
        private var instance: Preferences? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: Preferences(context).also { instance = it }
            }
    }

    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun setDarkModeFlag(isDarkMode: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(IS_DARK_MODE, isDarkMode)
        editor.apply()
    }

    fun getDarkModeFlag(): Boolean {
        return sharedPreferences.getBoolean(IS_DARK_MODE, false)
    }
}