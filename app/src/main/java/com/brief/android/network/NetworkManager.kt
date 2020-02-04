package com.brief.android.network

import android.content.Context
import com.brief.android.network.data.OriginToShortData
import com.brief.android.network.data.ShortToOriginData
import com.brief.android.network.data.VersionData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by JJH on 2020-02-01
 */
class NetworkManager : NetworkInfo{
    private constructor(context : Context) : super(context)

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
    fun getShortUrl(originUrl : String) {
        mNetworkApi.getShortUrl(mHeadersMap, originUrl).enqueue(object : Callback<OriginToShortData> {
            override fun onFailure(call: Call<OriginToShortData>, t: Throwable) {
            }
            override fun onResponse(call: Call<OriginToShortData>, response: Response<OriginToShortData>) {
            }
        })
    }
}