package com.brief.android.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.brief.android.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by JJH on 2020-02-01
 */
class MainActivity : AppCompatActivity(), MainContract.View {

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
    }

    override fun setOriginUrl(value: String) {
        et_origin_url.setText(value)
    }

    override fun setShortUrl(value: String) {
        et_short_url.setText(value)
    }
}