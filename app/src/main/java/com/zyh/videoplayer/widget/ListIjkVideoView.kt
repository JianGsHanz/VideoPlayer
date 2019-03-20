package com.zyh.videoplayer.widget

import android.content.Context
import android.util.AttributeSet
import com.zyh.videoplayer.bean.VideoModel
import com.zyh.videoplayer.impl.ListMediaPlayerControl

/**
 * 连续播放一个列表
 */

class ListIjkVideoView : CacheIjkVideoView, ListMediaPlayerControl {

    var mVideoModels: List<VideoModel>? = null//列表播放数据
    var mCurrentVideoPosition = 0//列表播放时当前播放视频的在List中的位置

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onCompletion() {
        super.onCompletion()
        skipToNext()
    }

    /**
     * 播放下一条视频
     */
    private fun playNext() {
        val videoModel = mVideoModels!![mCurrentVideoPosition]
        videoModel.run {
            mCurrentUrl = url
            mCurrentTitle = title
            setCacheEnabled(isCache)
            mCurrentPosition = 0
            setVideoController(controller)
        }
    }

    /**
     * 设置一个列表的视频
     */
    fun setVideos(videoModels: List<VideoModel>) {
        this.mVideoModels = videoModels
        playNext()
    }

    /**
     * 播放下一条视频，可用于跳过广告
     */
    override fun skipToNext() {
        mCurrentVideoPosition++
        if (mVideoModels != null && mVideoModels!!.size > 1) {
            if (mCurrentVideoPosition >= mVideoModels!!.size) return
            playNext()
            addDisplay()
            startPrepare(true)
        }
    }
}
