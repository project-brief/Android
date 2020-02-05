package com.brief.android.network

import android.content.Context
import com.brief.android.network.data.OriginToShortData
import com.brief.android.network.data.ShortToOriginData
import com.brief.android.network.data.VersionData
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by JJH on 2020-02-01
 */
class NetworkManager(context : Context) : NetworkInfo(context) {

    companion object {
        @Volatile private var instance: NetworkManager? = null

        fun getInstance(context : Context): NetworkManager =
            instance ?: synchronized(this) {
                instance ?: NetworkManager(context.applicationContext).also { instance = it }
            }
    }

    // SERVER API VERSION 조회 API
    fun getVersion() {
        mNetworkApi.getVersion(mHeadersMap).enqueue(object : Callback<VersionData> {
            override fun onFailure(call: Call<VersionData>, t: Throwable) {
            }
            override fun onResponse(call: Call<VersionData>, response: Response<VersionData>) {
            }
        })
    }

    // SHORT TO ORIGIN API
    fun getOriginUrl(shortUrl : String) {
        mNetworkApi.getOriginUrl(mHeadersMap, shortUrl).enqueue(object : Callback<ShortToOriginData> {
            override fun onFailure(call: Call<ShortToOriginData>, t: Throwable) {
            }
            override fun onResponse(call: Call<ShortToOriginData>, response: Response<ShortToOriginData>) {
            }
        })
    }

    // ORIGIN TO SHORT API
    fun getShortUrl(originUrl : String, callback: NetworkCallback<OriginToShortData>) {
        val requestJsonData = JSONObject()
        requestJsonData.put("original_url", originUrl)
        val bodyRequest : RequestBody = RequestBody.create("application/json; charset=utf-8".toMediaType(), requestJsonData.toString())

        mNetworkApi.getShortUrl(mHeadersMap, bodyRequest).enqueue(object : Callback<OriginToShortData> {
            override fun onFailure(call: Call<OriginToShortData>, t: Throwable) {
                callback.onFail(t.message)
            }
            override fun onResponse(call: Call<OriginToShortData>, response: Response<OriginToShortData>) {
                callback.onSuccess(response.body()!!)
            }
        })
    }
}