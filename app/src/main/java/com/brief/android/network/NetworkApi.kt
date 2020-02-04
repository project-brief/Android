package com.brief.android.network

import com.brief.android.network.data.OriginToShortData
import com.brief.android.network.data.ShortToOriginData
import com.brief.android.network.data.VersionData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by JJH on 2020-02-01
 */
interface NetworkApi {
    @GET("version/app")
    fun getVersion(@HeaderMap map : Map<String, String>): Call<VersionData>

    @GET("url")
    fun getOriginUrl(@HeaderMap map : Map<String, String>, @Query(value = "short", encoded = true) short : String): Call<ShortToOriginData>

    @POST("originUrl")
    fun getShortUrl(@HeaderMap map : Map<String, String>, @Query(value = "origin", encoded = true) origin : String): Call<OriginToShortData>
}