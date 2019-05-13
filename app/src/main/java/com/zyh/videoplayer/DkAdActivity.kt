package com.zyh.videoplayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.dueeeke.videocontroller.StandardVideoController
import com.zyh.videoplayer.bean.VideoModel
import com.zyh.videoplayer.impl.ControllerListener
import com.zyh.videoplayer.widget.AdController
import com.zyh.videoplayer.widget.ListIjkVideoView
import kotlinx.android.synthetic.main.activity_dk_ad.*

class DkAdActivity : AppCompatActivity() {

    val URL_VOD : String = "http://mov.bn.netease.com/open-movie/nos/flv/2017/01/03/SC8U8K7BC_hd.flv"
    val URL_AD : String = "https://gslb.miaopai.com/stream/IR3oMYDhrON5huCmf7sHCfnU5YKEkgO2.mp4"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dk_ad)
        val actionBar = supportActionBar
        actionBar!!.title = "带广告的视频"

//        ijkVideoView = findViewById(R.id.player)

        val videos = ArrayList<VideoModel>()
        val adController = AdController(this)
        adController.setControllerListener(object : ControllerListener{
            override fun onAdClick() {
                Toast.makeText(this@DkAdActivity,"广告点击跳转",Toast.LENGTH_SHORT).show()
            }
        })

        videos.add(VideoModel(URL_AD,"广告",adController,true))
        videos.add(VideoModel(URL_VOD,"这是一个标题",StandardVideoController(this),false))

        player.setVideos(videos)
        player.start()
    }

    override fun onPause() {
        super.onPause()
        player.pause()
    }

    override fun onBackPressed() {
        if (!player.onBackPressed())
            super.onBackPressed()
    }


    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}

