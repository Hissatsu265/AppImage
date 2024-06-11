package com.example.siapp.Repository

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CacheRepository(private val context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyCache", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveToCache(list1: List<String>, list2: List<String>, variableString: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        val list1Json = gson.toJson(list1)
        val list2Json = gson.toJson(list2)

        editor.putString("urls", list1Json)
        editor.putString("imgs", list2Json)
        editor.putString("key", variableString)
        editor.apply()
    }

    fun getVariableString(): String? {
        return sharedPreferences.getString("key", null)
    }
    fun getlist_url():List<String>{
        val json = sharedPreferences.getString("urls", null)
        return gson.fromJson(json, object : TypeToken<List<String>>() {}.type)
    }
    fun getlist_img():List<String>{
        val json = sharedPreferences.getString("imgs", null)
        return gson.fromJson(json, object : TypeToken<List<String>>() {}.type)
    }
}
