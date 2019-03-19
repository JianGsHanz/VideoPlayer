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
        bt_aotuPlayer.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.bt_aotuPlayer ->
                startActivity(Intent(this,JiaoziActivity::class.java))

        }
    }
}
