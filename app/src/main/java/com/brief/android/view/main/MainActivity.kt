package com.brief.android.view.main

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.brief.android.R
import com.brief.android.adapter.ShortUrlListAdapter
import com.brief.android.util.AlertUtil
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by JJH on 2020-02-01
 */
class MainActivity : AppCompatActivity(), MainContract.View {

    private val mAdapter : ShortUrlListAdapter by lazy {
        ShortUrlListAdapter(this@MainActivity)
    }

    private lateinit var mMainPresenter : MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mMainPresenter = MainPresenter(this@MainActivity, this@MainActivity)

        btn_paste.setOnClickListener {
            mMainPresenter.getOriginUrl()
        }
        btn_copy.setOnClickListener {
            mMainPresenter.copyShortUrl(et_short_url.text.toString())
        }
        btn_short_url.setOnClickListener {
            mMainPresenter.getShortUrl(et_origin_url.text.toString())
        }

        rv_main.adapter =mAdapter
        rv_main.layoutManager = LinearLayoutManager(this)

        mMainPresenter.setAdapterModal(mAdapter)
        mMainPresenter.start()
    }

    override fun setOriginUrl(value: String) {
        et_origin_url.setText(value)
    }

    override fun setShortUrl(value: String) {
        et_short_url.setText(value)
    }

    override fun refresh() {
        mAdapter.refresh()
    }

    override fun showDialog(msg: String) {
        AlertUtil.showMsgAlearDialog(context = this, msg = msg, cancelable = true,
            positiveBtnLabel = "확인", okClickListener = DialogInterface.OnClickListener { dialogInterface, _ -> dialogInterface.dismiss() })
    }
}