package com.brief.android.network

import android.content.Context
import com.brief.android.R
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by JJH on 2020-02-01
 * Network 설정
 */
open class NetworkInfo {
    protected lateinit var mHeadersMap : HashMap<String, String>
    protected lateinit var mNetworkApi : NetworkApi
    private var mContext : Context

    protected constructor(context : Context) {
        mContext = context

        initNetwork()
    }

    private fun initNetwork() {
        mHeadersMap = HashMap()
        mHeadersMap.put("Content-Type", "application/json")

        val interceptor = HttpLoggingInterceptor()
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val retrofit = Retrofit.Builder().baseUrl(mContext.getString(R.string.network_url))
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        mNetworkApi = retrofit.create(NetworkApi::class.java)
    }
}