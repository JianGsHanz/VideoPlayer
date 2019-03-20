package com.zyh.videoplayer

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE
import android.view.View
import cn.jzvd.JZMediaManager
import cn.jzvd.Jzvd
import cn.jzvd.JzvdMgr
import cn.jzvd.JzvdStd
import com.zyh.videoplayer.adapter.AutoRvAdapter

class JiaoziActivity : AppCompatActivity() {

    lateinit var lManager : LinearLayoutManager
    var firstVisibleItem : Int = 0
    var lastVisibleItem : Int = 0
    var visibleCount : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jiaozi)
        val actionBar = supportActionBar
        actionBar!!.title = "列表滑动播放"
        val rv = findViewById<RecyclerView>(R.id.rv)

        lManager = LinearLayoutManager(this)

        rv.run {
            layoutManager = lManager
            addItemDecoration(DividerItemDecoration(this@JiaoziActivity,DividerItemDecoration.VERTICAL))
            adapter = AutoRvAdapter(this@JiaoziActivity)
            //监听子控件附着和移出事件
            addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener{
                override fun onChildViewDetachedFromWindow(v: View) {
                }

                override fun onChildViewAttachedToWindow(v: View) {
                    val jzvd = v.findViewById<Jzvd>(R.id.player)
                    if (jzvd != null && jzvd.jzDataSource.containsTheUrl(JZMediaManager.getCurrentUrl())) {
                        val currentJzvd = JzvdMgr.getCurrentJzvd()
                        if (currentJzvd != null && currentJzvd.currentScreen != Jzvd.SCREEN_WINDOW_FULLSCREEN) {
                            Jzvd.releaseAllVideos()
                        }
                    }

                }
            })
            //滚动事件
           addOnScrollListener(object : RecyclerView.OnScrollListener(){
               override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                   super.onScrolled(recyclerView, dx, dy)
                   firstVisibleItem = lManager.findFirstVisibleItemPosition()
                   lastVisibleItem = lManager.findLastVisibleItemPosition()
                   visibleCount = lastVisibleItem - firstVisibleItem//记录可视区域item个数
               }

               override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                   super.onScrollStateChanged(recyclerView, newState)
                   when(newState){
                       SCROLL_STATE_IDLE ->
                           aotuPlayerVideo(recyclerView)
                   }
               }
           })

            post {
                val view = rv.getChildAt(0)
                val jzvdStd = view.findViewById<JzvdStd>(R.id.player)
                jzvdStd.startVideo()
            }


        }
    }

    fun aotuPlayerVideo(rv : RecyclerView){
        for (index in 0 until visibleCount){
            if (rv?.getChildAt(index) == null) continue
            val jzvdStd = rv.getChildAt(index).findViewById<JzvdStd>(R.id.player)
            val rect = Rect()
            jzvdStd.getLocalVisibleRect(rect)
            val height = jzvdStd.height
            if (rect.top == 0 && rect.bottom == height){
                jzvdStd.startVideo()
                return
            }

        }
    }

    override fun onBackPressed() {
        if (Jzvd.backPress()) {
            return
        }
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        Jzvd.releaseAllVideos()
    }
}
