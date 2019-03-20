package com.zyh.videoplayer.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import com.dueeeke.videoplayer.controller.BaseVideoController
import com.dueeeke.videoplayer.player.IjkVideoView
import com.dueeeke.videoplayer.util.L
import com.zyh.videoplayer.R

/**
 * 抖音
 */

class TikTokController : BaseVideoController {

    var thumb: ImageView? = null
        private set

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun getLayoutId(): Int {
        return R.layout.layout_tiktok_controller
    }

    override fun initView() {
        super.initView()
        thumb = mControllerView.findViewById(R.id.iv_thumb)
    }

    override fun setPlayState(playState: Int) {
        super.setPlayState(playState)

        when (playState) {
            IjkVideoView.STATE_IDLE -> {
                L.e("STATE_IDLE")
                thumb!!.visibility = View.VISIBLE
            }
            IjkVideoView.STATE_PLAYING -> {
                L.e("STATE_PLAYING")
                thumb!!.visibility = View.GONE
            }
            IjkVideoView.STATE_PREPARED ->
                L.e("STATE_PREPARED")
        }
    }
}
