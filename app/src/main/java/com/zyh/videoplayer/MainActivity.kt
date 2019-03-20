package com.zyh.videoplayer

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(),View.OnClickListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bt_aotuPlayer = findViewById<Button>(R.id.bt_aotuPlayer)
        val bt_dkPlayer = findViewById<Button>(R.id.bt_dkAd)
        val bt_dkTiktok = findViewById<Button>(R.id.bt_dkTiktok)
        bt_aotuPlayer.setOnClickListener(this)
        bt_dkPlayer.setOnClickListener(this)
        bt_dkTiktok.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.bt_aotuPlayer ->
                startActivity(Intent(this,JiaoziActivity::class.java))
            R.id.bt_dkAd ->
                startActivity(Intent(this,DkAdActivity::class.java))
            R.id.bt_dkTiktok ->
                startActivity(Intent(this,DkTiktokActivity::class.java))

        }
    }
}
