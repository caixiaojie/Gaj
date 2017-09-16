package com.fskj.gaj.Util;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.fskj.gaj.R;


/**
 * Created by Administrator on 2017/7/20 0020.
 */

public class SendTimer extends CountDownTimer {
    private TextView textView;
    private Context context;
    public SendTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }
    public SendTimer(Context context, TextView textView, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.context = context;
        this.textView = textView;
    }
    @Override
    public void onTick(long l) {
        textView.setText(l / 1000 + "S");
    }

    @Override
    public void onFinish() {
        //倒计时结束
        textView.setText("重新获取");
        textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
        textView.setEnabled(true);
    }
}
