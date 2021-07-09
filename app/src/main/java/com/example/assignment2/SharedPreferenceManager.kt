package com.example.assignment2

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import java.security.Key

object SharedPreferenceManager {
    private const val NAME = "note_pref"
    private const val MODE = Context.MODE_PRIVATE

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(NAME, MODE)
    }

    fun getStrValue(context: Context?, KEY: String, defaultValue: String? = null): String? {
        return getSharedPreferences(context!!).getString(KEY, defaultValue)
    }

    fun getIntValue(context: Context?, KEY: String, defaultValue: Int? = 0): Int? {
        return defaultValue?.let { getSharedPreferences(context!!).getInt(KEY, it)}
    }

    fun putStrValue(context: Context?, KEY: String, valueString: String?) {
        val editor = getSharedPreferences(context!!).edit()
        editor.putString(KEY, valueString)
        editor.apply()
    }

    fun putIntValue(context: Context?, KEY: String, valueInt: Int?) {
        val editor = getSharedPreferences(context!!).edit()
        valueInt?.let {
            editor.putInt(KEY, it)
        }
        editor.apply()
    }

    fun clearAllValue(context: Context?) {
        val editor = getSharedPreferences(context!!).edit()
        editor.clear()
        editor.apply()
    }

    fun putObject(context: Context?, KEY: String, valueObject: RecyclerItem?) {
        val editor = getSharedPreferences(context!!).edit()
        val gson = Gson()
        val json: String = gson.toJson(valueObject)
        Log.d("putObject() json ", json)
        editor.putString(KEY, json)
        editor.apply()
    }

    fun getObject(context: Context?, KEY: String, defaultValue: RecyclerItem?): RecyclerItem {
        val gson = Gson()
        val editor = getSharedPreferences(context!!)
        val json: String? = editor.getString(KEY, gson.toJson(defaultValue))
        return gson.fromJson(json, RecyclerItem::class.java)
    }
}