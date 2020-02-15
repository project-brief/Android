package com.brief.android.view.main

import com.brief.android.adapter.ShortUrlListAdapterContract
import com.brief.android.view.BaseContract

/**
 * Created by JJH on 2020-02-01
 */
interface MainContract : BaseContract {

    interface View : BaseContract.View {
        fun refresh()
        fun showDialog(msg : String)
        fun setOriginUrl(value : String)

        fun showEmptyView()
        fun showRecyclerView()
    }

    interface Presenter : BaseContract.Presenter {
        fun setAdapterModal(modal : ShortUrlListAdapterContract.Model)
        fun getOriginUrl()
        fun getShortUrl(value : String)
        fun copyShortUrl(value : String)
    }
}