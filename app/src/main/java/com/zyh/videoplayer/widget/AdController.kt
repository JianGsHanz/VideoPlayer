package com.zyh.videoplayer.widget

import android.content.Context
import android.content.pm.ActivityInfo
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.dueeeke.videoplayer.controller.BaseVideoController
import com.dueeeke.videoplayer.player.IjkVideoView
import com.dueeeke.videoplayer.util.PlayerUtils
import com.zyh.videoplayer.R
import com.zyh.videoplayer.impl.ControllerListener
import com.zyh.videoplayer.impl.ListMediaPlayerControl

class AdController : BaseVideoController, View.OnClickListener {
    var adTime: TextView? = null
    lateinit var adDetail: TextView
    lateinit var back: ImageView
    lateinit var volume: ImageView
    lateinit var fullScreen: ImageView
    lateinit var playButton: ImageView
    var listener: ControllerListener? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    override fun getLayoutId(): Int {
        return R.layout.layout_ad_controller
    }

    override fun initView() {
        super.initView()
        adTime = mControllerView.findViewById(R.id.ad_time)
        adDetail = mControllerView.findViewById(R.id.ad_detail)
        adDetail.text = "了解详情>"
        back = mControllerView.findViewById(R.id.back)
        back.visibility = View.GONE
        volume = mControllerView.findViewById(R.id.iv_volume)
        fullScreen = mControllerView.findViewById(R.id.fullscreen)
        playButton = mControllerView.findViewById(R.id.iv_play)
        playButton.setOnClickListener(this)
        adTime!!.setOnClickListener(this)
        adDetail.setOnClickListener(this)
        back.setOnClickListener(this)
        volume.setOnClickListener(this)
        fullScreen.setOnClickListener(this)
        this.setOnClickListener { if (listener != null) listener!!.onAdClick() }
    }

    override fun onClick(v: View) {
        val id = v.id
        if ((id == R.id.back) or (id == R.id.fullscreen)) {
            doStartStopFullScreen()
        } else if (id == R.id.iv_volume) {
            doMute()
        } else if (id == R.id.ad_detail) {
            if (listener != null) listener!!.onAdClick()
        } else if (id == R.id.ad_time) {
            (mMediaPlayer as ListMediaPlayerControl).skipToNext()
        } else if (id == R.id.iv_play) {
            doPauseResume()
        }
    }

    private fun doMute() {
        mMediaPlayer.isMute = !mMediaPlayer.isMute
        volume.setImageResource(if (!mMediaPlayer.isMute) R.drawable.dkplayer_ic_action_volume_up else R.drawable.dkplayer_ic_action_volume_off)
    }

    override fun setPlayState(playState: Int) {
        super.setPlayState(playState)
        when (playState) {
            IjkVideoView.STATE_PLAYING -> {
                post(mShowProgress)
                playButton.isSelected = true
            }
            IjkVideoView.STATE_PAUSED -> playButton.isSelected = false
        }
    }

    override fun setPlayerState(playerState: Int) {
        super.setPlayerState(playerState)
        when (playerState) {
            IjkVideoView.PLAYER_NORMAL -> {
                back.visibility = View.GONE
                fullScreen.isSelected = false
            }
            IjkVideoView.PLAYER_FULL_SCREEN -> {
                back.visibility = View.VISIBLE
                fullScreen.isSelected = true
            }
        }
    }

    override fun setProgress(): Int {
        if (mMediaPlayer == null) {
            return 0
        }
        val position = mMediaPlayer.currentPosition.toInt()
        val duration = mMediaPlayer.duration.toInt()

        if (adTime != null)
            adTime!!.text = String.format("%s | 跳过", (duration - position) / 1000)
        return position
    }

    override fun onBackPressed(): Boolean {
        if (mMediaPlayer.isFullScreen) {
            PlayerUtils.scanForActivity(context).requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            mMediaPlayer.stopFullScreen()
            setPlayerState(IjkVideoView.PLAYER_NORMAL)
            return true
        }
        return super.onBackPressed()
    }

    fun setControllerListener(listener: ControllerListener) {
        this.listener = listener
    }
}
