package com.zyh.videoplayer.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import cn.jzvd.JZDataSource;
import cn.jzvd.JZMediaManager;
import cn.jzvd.JzvdStd;
import com.zyh.videoplayer.R;

/**
 * Time:2019/3/19
 * Author:ZYH
 * Description:自定义倍速播放
 */
public class SpeedJzvdStd extends JzvdStd {

    private TextView speed;
    private int currentSpeedIndex = 2;

    public SpeedJzvdStd(Context context) {
        super(context);
    }

    public SpeedJzvdStd(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void init(Context context) {
        super.init(context);
        speed = findViewById(R.id.speed);
        speed.setOnClickListener(this);
    }
    @Override
    public void setUp(JZDataSource jzDataSource, int screen) {
        super.setUp(jzDataSource, screen);
        if (currentScreen == SCREEN_WINDOW_FULLSCREEN) {
            speed.setVisibility(View.VISIBLE);
        } else {
            speed.setVisibility(View.GONE);
        }
        if (jzDataSource.objects == null) {
            Object[] object = {2};
            jzDataSource.objects = object;
            currentSpeedIndex = 2;
        } else {
            currentSpeedIndex = (int) jzDataSource.objects[0];
        }
        if (currentSpeedIndex == 2) {
            speed.setText("倍速");
        } else {
            speed.setText(getSpeedFromIndex(currentSpeedIndex) + "X");
        }
    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.speed){
            if (currentSpeedIndex == 6) {
                currentSpeedIndex = 0;
            } else {
                currentSpeedIndex += 1;
            }
            JZMediaManager.setSpeed(getSpeedFromIndex(currentSpeedIndex));
            speed.setText(getSpeedFromIndex(currentSpeedIndex) + "X");
            jzDataSource.objects[0] = currentSpeedIndex;

        }
    }

    private float getSpeedFromIndex(int index) {
        float ret = 0f;
        if (index == 0) {
            ret = 0.5f;
        } else if (index == 1) {
            ret = 0.75f;
        } else if (index == 2) {
            ret = 1.0f;
        } else if (index == 3) {
            ret = 1.25f;
        } else if (index == 4) {
            ret = 1.5f;
        } else if (index == 5) {
            ret = 1.75f;
        } else if (index == 6) {
            ret = 2.0f;
        }
        return ret;
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_std_with_speed;
    }

}
