package com.brief.android.view.main

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import com.brief.android.network.NetworkManager

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
        NetworkManager.getInstance(mContext).getShortUrl(value)
        mView.setShortUrl("Set Short URL")
    }

    override fun copyShortUrl(value: String) {
        val clip : ClipData = ClipData.newPlainText("TEXT", value)
        clipboard.primaryClip = clip
    }
}