package com.brief.android.view.main

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import com.brief.android.network.NetworkCallback
import com.brief.android.network.NetworkManager
import com.brief.android.network.data.OriginToShortData
import com.brief.android.util.PrefUtil

/**
 * Created by JJH on 2020-02-01
 */
class MainPresenter(private var mView : MainContract.View, private var mContext : Context) : MainContract.Presenter {

    private val clipboard : ClipboardManager by lazy {
        mContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    }

    private val mPrefUtil : PrefUtil by lazy {
        PrefUtil.getInstance(mContext)
    }

    override fun start() {
        val originToShortDataList = mPrefUtil.getArray<OriginToShortData>("object", OriginToShortData::class.java)
    }

    override fun getOriginUrl() {
        mView.setOriginUrl(clipboard.primaryClip?.getItemAt(0)?.text.toString())
    }

    override fun getShortUrl(value: String) {
        NetworkManager.getInstance(mContext).getShortUrl(value, object : NetworkCallback<OriginToShortData> {
            override fun onSuccess(obj: OriginToShortData) {
                val originToShortDataList : ArrayList<OriginToShortData> = when(mPrefUtil.contains("object")) {
                    true -> mPrefUtil.getArray<OriginToShortData>("object", OriginToShortData::class.java) as ArrayList
                    false -> ArrayList<OriginToShortData>()
                }

                originToShortDataList.add(obj)

                mPrefUtil.setArray("object", originToShortDataList)

                mView.setShortUrl(obj.short_url)
            }
            override fun onFail(errMsg: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    override fun copyShortUrl(value: String) {
        val clip : ClipData = ClipData.newPlainText("TEXT", value)
        clipboard.primaryClip = clip
    }
}