package com.brief.android.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brief.android.R
import com.brief.android.network.data.OriginToShortData
import kotlinx.android.synthetic.main.view_main_recycler_item.view.*

/**
 * Created by JJH on 2020-02-05
 */
class ShortUrlListAdapter(private val mContext : Context) : RecyclerView.Adapter<ShortUrlListAdapter.ShortUrlViewHolder>(), ShortUrlListAdapterContract.View, ShortUrlListAdapterContract.Model{
    private var mItemArray : ArrayList<OriginToShortData> = ArrayList()

    constructor(context : Context, itemArray : ArrayList<OriginToShortData>) : this(context){
        mItemArray = itemArray
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShortUrlViewHolder = ShortUrlViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_main_recycler_item, parent, false))

    override fun getItemCount(): Int = mItemArray.size

    override fun onBindViewHolder(holder: ShortUrlViewHolder, position: Int) {
        with(holder.itemView) {
            tv_origin_url.text = mItemArray[position].original_url
            tv_short_url.text = mItemArray[position].short_url
        }
    }

    class ShortUrlViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun refresh() {
        notifyDataSetChanged()
    }

    override fun addAll(items: ArrayList<OriginToShortData>) {
        mItemArray.addAll(items.reversed())
    }

    override fun add(item: OriginToShortData) {
        mItemArray.add(0, item)
    }

    override fun remove(position: Int) {
        mItemArray.removeAt(position)
    }

    override fun getItem(position: Int): OriginToShortData = mItemArray[position]
}