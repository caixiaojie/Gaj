package com.fskj.gaj.home.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;


import com.fskj.gaj.R;
import com.fskj.gaj.Remote.ResultListInterface;
import com.fskj.gaj.Remote.ResultTVO;
import com.fskj.gaj.Util.Tools;
import com.fskj.gaj.request.MsgListRequest;
import com.fskj.gaj.vo.MsgListCommitVo;
import com.fskj.gaj.vo.MsgListResultVo;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommonFragment extends Fragment {


    private MsgListCommitVo msgListCommitVo;
    private String type;
    private MsgListRequest msgListRequest;
    private List<MsgListResultVo> msgList = new ArrayList<>();
    private CommonAdapter adapter;
    private Handler handler = new Handler();

    public static CommonFragment getInstance(String type){
        CommonFragment f =new CommonFragment();
        Bundle bundle=new Bundle();
        bundle.putString("type",type);
        f.setArguments(bundle);

        return f;
    }

    private Activity activity;

    private SwipeRefreshLayout srLayout;
    private RecyclerView recyclerView;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Bundle bundle=getArguments();
        if(bundle!=null){
            type = bundle.getString("type", "");
        }
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        Bundle bundle=getArguments();
        if(bundle!=null){

        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity=this.getActivity();


            SharedPreferences sp = Tools.getSharePreferences(activity, "FragmentPosition");
            sp.edit().putInt("position",0).commit();

//界面初始化
        View v=inflater.inflate(R.layout.fragment_common, null);
        srLayout=(SwipeRefreshLayout)v.findViewById(R.id.sr_layout);
        recyclerView=(RecyclerView)v.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        srLayout.post(new Runnable() {
            @Override
            public void run() {
                srLayout.setRefreshing(true);
            }
        });
//声明请求变量和返回结果
        initRequest();
        adapter = new CommonAdapter();
        recyclerView.setAdapter(adapter);
//初始化控件事件
        initWidgetEvent();
        msgListRequest.send();
        v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,  LinearLayout.LayoutParams.MATCH_PARENT));
        return v;
    }

    //声明请求变量和返回结果
    private void initRequest(){
        msgListCommitVo = new MsgListCommitVo();
        if (type != null && type.equals("") == false) {
            msgListCommitVo.setType(type);
        }
        msgListRequest = new MsgListRequest(activity, msgListCommitVo, new ResultListInterface<MsgListResultVo>() {
            @Override
            public void success(ResultTVO<MsgListResultVo> data) {
                ArrayList<MsgListResultVo> msgListResultVos = data.getData();
                if (msgListCommitVo.isFirstPage()) {
                    msgList.clear();
                }
                if (msgListResultVos != null && msgListResultVos.size() > 0) {
                    msgList.addAll(msgListResultVos);
                }
                adapter.notifyDataSetChanged();
                srLayout.setRefreshing(false);
            }

            @Override
            public void error(String errmsg) {
                srLayout.setRefreshing(false);
                Toast.makeText(activity,errmsg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    //初始化控件事件
    private void initWidgetEvent(){
        srLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        msgListCommitVo.resetPage();
                        msgListRequest.send();
                    }
                }, 2000);
            }
        });
    }

    private void showMsg(String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==Activity.RESULT_OK){

        }
    }

    class CommonAdapter extends RecyclerView.Adapter<CommonAdapter.CommonViewHolder> {
        @Override
        public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(activity).inflate(R.layout.item_common_layout,parent,false);
            return new CommonViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CommonViewHolder holder, int position) {
            MsgListResultVo vo = msgList.get(position);
            holder.tvTitle.setText(vo.getTitle());
            holder.tvTime.setText(vo.getCreatetime());
            holder.tvVisit.setText(vo.getVisit()+"");
        }

        @Override
        public int getItemCount() {
            return msgList.size();
        }

        class CommonViewHolder extends RecyclerView.ViewHolder{
            TextView tvTime,tvVisit,tvTitle;
            public CommonViewHolder(View itemView) {
                super(itemView);
                tvTime = (TextView) itemView.findViewById(R.id.tv_time);
                tvVisit = (TextView) itemView.findViewById(R.id.tv_count);
                tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            }
        }
    }
}
