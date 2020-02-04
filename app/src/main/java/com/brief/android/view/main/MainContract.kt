package com.brief.android.view.main

import com.brief.android.view.BaseContract

/**
 * Created by JJH on 2020-02-01
 */
interface MainContract : BaseContract {

    interface View : BaseContract.View {
        fun setOriginUrl(value : String)
        fun setShortUrl(value : String)
    }

    interface Presenter : BaseContract.Presenter {
        fun getOriginUrl()
        fun getShortUrl(value : String)
        fun copyShortUrl(value : String)
    }
}