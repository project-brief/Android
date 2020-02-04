package com.brief.android.view.main

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import com.brief.android.network.NetworkCallback
import com.brief.android.network.NetworkManager
import com.brief.android.network.data.OriginToShortData

/**
 * Created by JJH on 2020-02-01
 */
class MainPresenter(private var mView : MainContract.View, private var mContext : Context) : MainContract.Presenter {

    private val clipboard : ClipboardManager by lazy {
        mContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getOriginUrl() {
        mView.setOriginUrl(clipboard.primaryClip?.getItemAt(0)?.text.toString())
    }

    override fun getShortUrl(value: String) {
        NetworkManager.getInstance(mContext).getShortUrl(value, object : NetworkCallback<OriginToShortData> {
            override fun onSuccess(obj: OriginToShortData) {
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