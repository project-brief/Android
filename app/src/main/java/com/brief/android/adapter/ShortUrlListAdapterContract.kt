package com.brief.android.adapter

import com.brief.android.network.data.OriginToShortData

/**
 * Created by JJH on 2020-02-08
 */
interface ShortUrlListAdapterContract {
    interface View {
        fun refresh()
    }

    interface Model {
        fun addAll(items : ArrayList<OriginToShortData>)
        fun add(item: OriginToShortData)
        fun remove(position: Int)
        fun getItem(position: Int) : OriginToShortData
    }
}