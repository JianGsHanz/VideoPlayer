package com.zyh.videoplayer

import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.dueeeke.videoplayer.player.IjkVideoView
import com.dueeeke.videoplayer.player.PlayerConfig
import com.zyh.videoplayer.adapter.TikTokAdapter
import com.zyh.videoplayer.bean.VideoBean
import com.zyh.videoplayer.impl.OnViewPagerListener
import com.zyh.videoplayer.widget.TikTokController
import com.zyh.videoplayer.widget.ViewPagerLayoutManager

class DkTiktokActivity : AppCompatActivity() {
    private lateinit var rv : RecyclerView
    private lateinit var ijkVideoView : IjkVideoView
    private lateinit var mTikTokController : TikTokController
    private lateinit var videoList : ArrayList<VideoBean>
    private var mCurrentPosition : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dk_tiktok)

        setStatusBarTransparent()

        rv = findViewById(R.id.rv)

        ijkVideoView = IjkVideoView(this)
        val config = PlayerConfig.Builder().setLooping().build()
        ijkVideoView.setPlayerConfig(config)
        mTikTokController = TikTokController(this)
        ijkVideoView.setVideoController(mTikTokController)
        videoList = getTikTokVideoList()

        val tikTokAdapter = TikTokAdapter(videoList, this)
        val layoutManager = ViewPagerLayoutManager(this, OrientationHelper.VERTICAL)

        rv.layoutManager = layoutManager
        rv.adapter = tikTokAdapter
        layoutManager.setOnViewPagerListener(object : OnViewPagerListener {
            override fun onInitComplete() {
                //自动播放第一条
                startPlay(0)
            }

            override fun onPageRelease(isNext: Boolean, position: Int) {
                if (mCurrentPosition == position) {
                    ijkVideoView.release()
                }
            }

            override fun onPageSelected(position: Int, isBottom: Boolean) {
                if (mCurrentPosition == position) return
                startPlay(position)
                mCurrentPosition = position
            }
        })
    }

    private fun startPlay(position: Int) {
        val itemView = rv.getChildAt(0)
        val frameLayout = itemView.findViewById<FrameLayout>(R.id.container)
        Glide.with(this)
            .load(videoList[position].thumb)
            .placeholder(android.R.color.white)
            .into(mTikTokController.thumb!!)
        val parent = ijkVideoView.parent
        if (parent is FrameLayout) {
            parent.removeView(ijkVideoView)
        }
        frameLayout.addView(ijkVideoView)
        ijkVideoView.setUrl(videoList[position].url)
        ijkVideoView.setScreenScale(IjkVideoView.SCREEN_SCALE_CENTER_CROP)
        ijkVideoView.start()
    }
    /**
     * 抖音演示数据
     */
    private fun getTikTokVideoList(): ArrayList<VideoBean> {
        val videoList = ArrayList<VideoBean>()
        videoList.add(
            VideoBean(
                "",
                "http://p9.pstatp.com/large/4c87000639ab0f21c285.jpeg",
                "https://aweme.snssdk.com/aweme/v1/play/?video_id=97022dc18711411ead17e8dcb75bccd2&line=0&ratio=720p&media_type=4&vr_type=0"
            )
        )

        videoList.add(
            VideoBean(
                "",
                "http://p1.pstatp.com/large/4bea0014e31708ecb03e.jpeg",
                "https://aweme.snssdk.com/aweme/v1/play/?video_id=374e166692ee4ebfae030ceae117a9d0&line=0&ratio=720p&media_type=4&vr_type=0"
            )
        )

        videoList.add(
            VideoBean(
                "",
                "http://p1.pstatp.com/large/4bb500130248a3bcdad0.jpeg",
                "https://aweme.snssdk.com/aweme/v1/play/?video_id=8a55161f84cb4b6aab70cf9e84810ad2&line=0&ratio=720p&media_type=4&vr_type=0"
            )
        )

        videoList.add(
            VideoBean(
                "",
                "http://p9.pstatp.com/large/4b8300007d1906573584.jpeg",
                "https://aweme.snssdk.com/aweme/v1/play/?video_id=47a9d69fe7d94280a59e639f39e4b8f4&line=0&ratio=720p&media_type=4&vr_type=0"
            )
        )

        videoList.add(
            VideoBean(
                "",
                "http://p9.pstatp.com/large/4b61000b6a4187626dda.jpeg",
                "https://aweme.snssdk.com/aweme/v1/play/?video_id=3fdb4876a7f34bad8fa957db4b5ed159&line=0&ratio=720p&media_type=4&vr_type=0"
            )
        )

        videoList.add(
            VideoBean(
                "",
                "http://p9.pstatp.com/large/4c87000639ab0f21c285.jpeg",
                "https://aweme.snssdk.com/aweme/v1/play/?video_id=97022dc18711411ead17e8dcb75bccd2&line=0&ratio=720p&media_type=4&vr_type=0"
            )
        )

        videoList.add(
            VideoBean(
                "",
                "http://p1.pstatp.com/large/4bea0014e31708ecb03e.jpeg",
                "https://aweme.snssdk.com/aweme/v1/play/?video_id=374e166692ee4ebfae030ceae117a9d0&line=0&ratio=720p&media_type=4&vr_type=0"
            )
        )

        videoList.add(
            VideoBean(
                "",
                "http://p1.pstatp.com/large/4bb500130248a3bcdad0.jpeg",
                "https://aweme.snssdk.com/aweme/v1/play/?video_id=8a55161f84cb4b6aab70cf9e84810ad2&line=0&ratio=720p&media_type=4&vr_type=0"
            )
        )

        videoList.add(
            VideoBean(
                "",
                "http://p9.pstatp.com/large/4b8300007d1906573584.jpeg",
                "https://aweme.snssdk.com/aweme/v1/play/?video_id=47a9d69fe7d94280a59e639f39e4b8f4&line=0&ratio=720p&media_type=4&vr_type=0"
            )
        )

        videoList.add(
            VideoBean(
                "",
                "http://p9.pstatp.com/large/4b61000b6a4187626dda.jpeg",
                "https://aweme.snssdk.com/aweme/v1/play/?video_id=3fdb4876a7f34bad8fa957db4b5ed159&line=0&ratio=720p&media_type=4&vr_type=0"
            )
        )

        videoList.add(
            VideoBean(
                "",
                "http://p9.pstatp.com/large/4c87000639ab0f21c285.jpeg",
                "https://aweme.snssdk.com/aweme/v1/play/?video_id=97022dc18711411ead17e8dcb75bccd2&line=0&ratio=720p&media_type=4&vr_type=0"
            )
        )

        videoList.add(
            VideoBean(
                "",
                "http://p1.pstatp.com/large/4bea0014e31708ecb03e.jpeg",
                "https://aweme.snssdk.com/aweme/v1/play/?video_id=374e166692ee4ebfae030ceae117a9d0&line=0&ratio=720p&media_type=4&vr_type=0"
            )
        )

        videoList.add(
            VideoBean(
                "",
                "http://p1.pstatp.com/large/4bb500130248a3bcdad0.jpeg",
                "https://aweme.snssdk.com/aweme/v1/play/?video_id=8a55161f84cb4b6aab70cf9e84810ad2&line=0&ratio=720p&media_type=4&vr_type=0"
            )
        )

        videoList.add(
            VideoBean(
                "",
                "http://p9.pstatp.com/large/4b8300007d1906573584.jpeg",
                "https://aweme.snssdk.com/aweme/v1/play/?video_id=47a9d69fe7d94280a59e639f39e4b8f4&line=0&ratio=720p&media_type=4&vr_type=0"
            )
        )

        videoList.add(
            VideoBean(
                "",
                "http://p9.pstatp.com/large/4b61000b6a4187626dda.jpeg",
                "https://aweme.snssdk.com/aweme/v1/play/?video_id=3fdb4876a7f34bad8fa957db4b5ed159&line=0&ratio=720p&media_type=4&vr_type=0"
            )
        )
        return videoList
    }
    /**
     * 把状态栏设成透明
     */
    private fun setStatusBarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val decorView = this.window.decorView
            decorView.setOnApplyWindowInsetsListener { v, insets ->
                val defaultInsets = v.onApplyWindowInsets(insets)
                defaultInsets.replaceSystemWindowInsets(
                    defaultInsets.systemWindowInsetLeft,
                    0,
                    defaultInsets.systemWindowInsetRight,
                    defaultInsets.systemWindowInsetBottom
                )
            }
            ViewCompat.requestApplyInsets(decorView)
            window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        }
    }

    override fun onPause() {
        super.onPause()
        ijkVideoView.pause()
    }

    override fun onResume() {
        super.onResume()
        ijkVideoView.resume()
    }

    override fun onDestroy() {
        super.onDestroy()
        ijkVideoView.release()
    }
}
