package com.brief.android.network

import com.brief.android.network.data.OriginToShortData
import com.brief.android.network.data.ShortToOriginData
import com.brief.android.network.data.VersionData
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by JJH on 2020-02-01
 */
interface NetworkApi {
    @GET("version/app")
    fun getVersion(@HeaderMap map : Map<String, String>): Call<VersionData>

    @GET("url/{short}")
    fun getOriginUrl(@HeaderMap map : Map<String, String>, @Path("short") short : String): Call<ShortToOriginData>

    @POST("url")
    fun getShortUrl(@HeaderMap map : Map<String, String>, @Body body : RequestBody): Call<OriginToShortData>
}