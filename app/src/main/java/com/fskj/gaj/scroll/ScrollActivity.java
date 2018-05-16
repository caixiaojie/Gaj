package com.fskj.gaj.scroll;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.fskj.gaj.R;
import com.fskj.gaj.Util.Tools;

public class ScrollActivity extends AppCompatActivity {

    private ScrollView scrollView;
    private Activity activity;
    private int cyend;
    private int cystart;
    private ViewFlipper viewFlipper;
    private boolean b = true;
    private LinearLayout lladdView;
    private LinearLayout lladdView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        activity = ScrollActivity.this;
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        initView();

     /*   new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    View childStart = llAddView.getChildAt(0);
                    View childEnd = llAddView.getChildAt(llAddView.getChildCount() - 1);
                    int location [] = new int[2];
                    int location1 [] = new int[2];
                    childStart.getLocationOnScreen(location);
                    childEnd.getLocationOnScreen(location1);
                    cystart = location[1];
                    cyend = location1[1];
                    Log.e("====","cyend="+cyend+"------cystart="+cystart);
                    int height = scrollView.getHeight();
                    scrollView.smoothScrollTo(0,(cyend-cystart));
                    handler.hasMessages(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();*/
//        scrollView.post(ScrollRunnable);
    }

    private Runnable ScrollRunnable = new Runnable() {
        @Override
        public void run() {
            handler.sendEmptyMessageDelayed(0,1500);
        }
    };

    private void initView() {
        for (int j = 0; j < 4; j++) {
//            viewFlipper.removeAllViews();
            lladdView = new LinearLayout(activity);
            lladdView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lladdView.setOrientation(LinearLayout.VERTICAL);
//            lladdView.removeAllViews();
            for (int i = 0; i < 5; i++) {
                if (j < 3) {
                    LinearLayout layout = new LinearLayout(activity);
                    layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Tools.dip2px(activity, 50f)));
                    TextView txt = new TextView(activity);
                    txt.setText("第" + j + i + "条数据");
                    layout.addView(txt);
                    layout.setGravity(Gravity.CENTER);
                    lladdView.addView(layout);
                }else {
                    LinearLayout layout = new LinearLayout(activity);
                    layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Tools.dip2px(activity, 50f)));
                    TextView txt = new TextView(activity);
                    txt.setText("最后一条数据");
                    layout.addView(txt);
                    layout.setGravity(Gravity.CENTER);
                    lladdView.addView(layout);
                    break;
                }
            }
            viewFlipper.addView(lladdView);
            viewFlipper.startFlipping();
        }



//        lladdView2 = new LinearLayout(activity);
//        lladdView2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        lladdView2.setOrientation(LinearLayout.VERTICAL);
//        for (int i = 6; i < 10; i++) {
//            LinearLayout layout = new LinearLayout(activity);
//            layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Tools.dip2px(activity,50f)));
//            TextView txt = new TextView(activity);
//            txt.setText("第"+i+"条数据");
//            layout.addView(txt);
//            layout.setGravity(Gravity.CENTER);
//            lladdView2.addView(layout);
//        }


//        viewFlipper.addView(lladdView);

//        viewFlipper.post(ScrollRunnable);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    viewFlipper.removeAllViews();
                    if (b) {
                        viewFlipper.addView(lladdView);
                        viewFlipper.startFlipping();
                        b = false;
                    }else {
                        viewFlipper.addView(lladdView2);
                        viewFlipper.startFlipping();
                        b = true;
                    }
                    viewFlipper.post(ScrollRunnable);
                    break;
            }
        }
    };
}
