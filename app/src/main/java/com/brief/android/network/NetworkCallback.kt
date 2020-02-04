package com.brief.android.network

/**
 * Created by JJH on 2020-02-04
 */
interface NetworkCallback<T> {
    fun onSuccess(obj : T)
    fun onFail(errMsg : String?)
}