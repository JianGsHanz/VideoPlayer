package com.zyh.videoplayer.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.jzvd.Jzvd
import com.bumptech.glide.Glide
import com.zyh.videoplayer.R
import com.zyh.videoplayer.VideoConstant
import kotlinx.android.synthetic.main.rv_auto_item.view.*

/**
 *Time:2019/3/19
 *Author:ZYH
 *Description:
 */
class AutoRvAdapter() : RecyclerView.Adapter<AutoRvAdapter.ViewHolder>() {
    private var context: Context? = null

    constructor(context: Context) : this() {
        this.context = context
    }

    override fun onCreateViewHolder(p: ViewGroup, p1: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.rv_auto_item,
                p,
                false
            )
        )

    override fun getItemCount(): Int = 9

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: ViewHolder, p: Int) {
        holder.run {
            itemView.player.setUp(
                VideoConstant.videoUrls[0][p],
                VideoConstant.videoTitles[0][p], Jzvd.SCREEN_WINDOW_NORMAL
            )
        }
        Glide.with(context!!).load(VideoConstant.videoThumbs[0][p]).into(holder.itemView.player.thumbImageView)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}