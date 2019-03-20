package com.zyh.videoplayer.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.zyh.videoplayer.R
import com.zyh.videoplayer.bean.VideoBean
import kotlinx.android.synthetic.main.item_tik_tok.view.*

class TikTokAdapter(private val videos: List<VideoBean>, private val context: Context) :
    RecyclerView.Adapter<TikTokAdapter.VideoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_tik_tok, parent, false)
        return VideoHolder(itemView)

    }

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {

        val videoBean = videos[position]
        Glide.with(context)
            .load(videoBean.thumb)
            .placeholder(android.R.color.white)
            .into(holder.itemView.thumb)
    }

    override fun getItemCount(): Int {
        return videos.size
    }

    class VideoHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}