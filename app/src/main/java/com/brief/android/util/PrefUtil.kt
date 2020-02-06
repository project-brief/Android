package com.brief.android.util

import android.content.Context
import android.content.SharedPreferences
import com.brief.android.data.Config
import com.google.gson.Gson
import kotlin.reflect.KClass

/**
 * Created by JJH on 2020-02-06
 */
class PrefUtil(private val mContext: Context) {
    private val mSharedPreferences: SharedPreferences by lazy {
        mContext.getSharedPreferences(Config.Pref.SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
    }
    companion object {
        @Volatile private var instance: PrefUtil? = null

        fun getInstance(context : Context): PrefUtil =
            instance ?: synchronized(this) {
                instance ?: PrefUtil(context.applicationContext).also { instance = it }
            }
    }

    fun setInt(name: String, value: Int) = with(mSharedPreferences.edit()){
        putInt(name, value)
        apply()
    }

    fun getInt(name: String): Int = mSharedPreferences.getInt(name, 0)

    fun setBoolean(name: String, value: Boolean) = with(mSharedPreferences.edit()){
        putBoolean(name, value)
        apply()
    }

    fun getBoolean(name: String): Boolean = mSharedPreferences.getBoolean(name, false)

    fun setString(name: String, value: String) = with(mSharedPreferences.edit()) {
        putString(name, value)
        apply()
    }

    fun getString(name: String): String = mSharedPreferences.getString(name, "")


    fun setStringSet(name: String, value: Set<String>) = with(mSharedPreferences.edit()){
        putStringSet(name, value)
        apply()
    }

    fun getStringSet(name: String): Set<String>? = mSharedPreferences.getStringSet(name, null)

    fun setFloat(name: String, value: Float) = with(mSharedPreferences.edit()){
        putFloat(name, value)
        apply()
    }

    fun getFloat(name: String): Float = mSharedPreferences.getFloat(name, 0f)

    fun setLong(name: String, value: Long) = with(mSharedPreferences.edit()){
        putLong(name, value)
        apply()
    }

    fun getLong(name: String): Long = mSharedPreferences.getLong(name, 0)

    fun <T> setAny(name: String, value: T) = with(mSharedPreferences.edit()){
        putString(name, Gson().toJson(value))
        apply()
    }

    fun <T:Any> getAny(name : String, kClass:KClass<T>) : KClass<T> = Gson().fromJson(mSharedPreferences.getString(name, ""), kClass::class.java)
}