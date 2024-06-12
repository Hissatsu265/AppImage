package com.example.siapp.Repository

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CacheRepository(private val context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyCache", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveToCache(list1: List<String>, list2: List<String>, list3: List<String>, variableString: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        val list1Json = gson.toJson(list1)
        val list2Json = gson.toJson(list2)
        val list3Json = gson.toJson(list3)

        editor.putString("urls"+variableString, list1Json)
        editor.putString("imgs"+variableString, list2Json)
        editor.putString("titles"+variableString, list3Json)
        editor.putString(variableString, variableString)
        editor.apply()
    }

    fun getVariableString(s:String): String? {
        return sharedPreferences.getString(s, null)
    }
    fun getlist_url(s:String):List<String>{
        val json = sharedPreferences.getString("urls"+s, null)
        return gson.fromJson(json, object : TypeToken<List<String>>() {}.type)
    }
    fun getlist_img(s:String):List<String>{
        val json = sharedPreferences.getString("imgs"+s, null)
        return gson.fromJson(json, object : TypeToken<List<String>>() {}.type)
    }
    fun getlist_title(s:String):List<String>{
        val json = sharedPreferences.getString("titles"+s, null)
        return gson.fromJson(json, object : TypeToken<List<String>>() {}.type)
    }
}
