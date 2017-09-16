package com.fskj.gaj;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;

import com.fskj.gaj.duty.DutyFragment;
import com.fskj.gaj.home.HomeFragment;
import com.fskj.gaj.notice.NoticeFragment;
import com.fskj.gaj.room.RoomFragment;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    public static void gotoActivity(Activity activity ){
        Intent intent=new Intent(activity,MainActivity.class);

        activity.startActivity(intent);
    }

    public static void gotoActivity(Fragment fr ){
        Intent intent=new Intent(fr.getActivity(),MainActivity.class);

        fr.startActivity(intent);
    }

    private RelativeLayout realLayout;
    private LinearLayout llMenuHome;
    private ImageView imgHome;
    private TextView tvHomePage;
    private LinearLayout llMenuNotice;
    private ImageView imgNotice;
    private TextView tvNotice;
    private LinearLayout llMenuDuty;
    private ImageView imgDuty;
    private TextView tvDuty;
    private LinearLayout llMenuRoom;
    private ImageView imgRoom;
    private TextView tvRoom;
    private LayoutInflater inflater;
    private Activity activity;
    private int lastpos;
    private boolean isExit = false;
    private int selectpos;//记录选中的项，用于登录后的返回
    private FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity=MainActivity.this;

        inflater = LayoutInflater.from(activity);


//界面初始化
        realLayout=(RelativeLayout)findViewById(R.id.real_layout);
        llMenuHome=(LinearLayout)findViewById(R.id.llMenuHome);
        imgHome=(ImageView)findViewById(R.id.img_home);
        tvHomePage=(TextView)findViewById(R.id.tv_home_page);
        llMenuNotice=(LinearLayout)findViewById(R.id.llMenuNotice);
        imgNotice=(ImageView)findViewById(R.id.img_notice);
        tvNotice=(TextView)findViewById(R.id.tv_notice);
        llMenuDuty=(LinearLayout)findViewById(R.id.llMenuDuty);
        imgDuty=(ImageView)findViewById(R.id.img_duty);
        tvDuty=(TextView)findViewById(R.id.tv_duty);
        llMenuRoom=(LinearLayout)findViewById(R.id.llMenuRoom);
        imgRoom=(ImageView)findViewById(R.id.img_room);
        tvRoom=(TextView)findViewById(R.id.tv_room);
//声明请求变量和返回结果
        initRequest();
//初始化控件事件
        initWidgetEvent();
//传入参数
        Bundle bundle=this.getIntent().getExtras();
        if(bundle!=null){

        }
        fm = getSupportFragmentManager();
        lastpos = -1;
        setSelected(0);

    }

    private void setSelected(int p) {
        if (p == lastpos) {
            return;
        }
        selectpos = p;
            FragmentTransaction transaction = fm.beginTransaction();
            switch (p) {
                case 0:

                lastpos = p;

                    HomeFragment homeFragment = HomeFragment.getInstance();
                    transaction.replace(R.id.real_layout,homeFragment);
                    transaction.commit();

                    break;
                case 1:


                    NoticeFragment noticeFragment = NoticeFragment.getInstance();
                    transaction.replace(R.id.real_layout,noticeFragment);
                    transaction.commit();
                lastpos = p;
                    break;
                case 2:
                    DutyFragment dutyFragment = DutyFragment.getInstance();
                    transaction.replace(R.id.real_layout,dutyFragment);
                    transaction.commit();
                    lastpos = p;
                    break;
                case 3:


                    RoomFragment roomFragment = RoomFragment.getInstance();
                    transaction.replace(R.id.real_layout,roomFragment);
                    transaction.commit();
                    lastpos = p;
                    break;
            }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){

        }
    }

    //声明请求变量和返回结果
    private void initRequest(){

    }

    //初始化控件事件
    private void initWidgetEvent(){
        //点击首页
        llMenuHome.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelected(0);
            }
        });
        //点击通知公告
        llMenuNotice.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelected(1);
            }
        });
        //点击今日值班
        llMenuDuty.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelected(2);
            }
        });
        //点击会议室
        llMenuRoom.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelected(3);
            }
        });
    }

    private void showMsg(String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {

            Timer tExit = null;
            if (isExit == false) {
                isExit = true; // 准备退出
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                tExit = new Timer();
                tExit.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        isExit = false; // 取消退出
                    }
                }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

            } else {
                finish();
                System.exit(0);
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
