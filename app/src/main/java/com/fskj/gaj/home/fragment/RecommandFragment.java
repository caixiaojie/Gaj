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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;


import com.bumptech.glide.Glide;
import com.fskj.gaj.AppConfig;
import com.fskj.gaj.R;
import com.fskj.gaj.Remote.ResultListInterface;
import com.fskj.gaj.Remote.ResultTVO;
import com.fskj.gaj.Util.PageQuery;
import com.fskj.gaj.Util.Tools;
import com.fskj.gaj.request.PicNewsListRequest;
import com.fskj.gaj.vo.PicNewsListResultVo;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommandFragment extends Fragment {

    public static RecommandFragment getInstance( ){
        RecommandFragment f =new RecommandFragment();
        Bundle bundle=new Bundle();

        f.setArguments(bundle);

        return f;
    }

    private Activity activity;
    private PageQuery pageQuery;
    private PicNewsListRequest picNewsListRequest;
    private List<PicNewsListResultVo> picNewsList = new ArrayList<>();

    private SwipeRefreshLayout srLayout;
    private RecyclerView recyclerView;
    private RecommandAdapter adapter;
    private Handler handler = new Handler();


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser == true) {

        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
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
        sp.edit().putInt("position",1).commit();
//界面初始化
        View v=inflater.inflate(R.layout.fragment_recommand, null);
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

        adapter = new RecommandAdapter();
        recyclerView.setAdapter(adapter);
//初始化控件事件
        initWidgetEvent();
        picNewsListRequest.send();
        v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,  LinearLayout.LayoutParams.MATCH_PARENT));
        return v;
    }

    //声明请求变量和返回结果
    private void initRequest(){
        pageQuery = new PageQuery();
        picNewsListRequest = new PicNewsListRequest(activity, pageQuery, new ResultListInterface<PicNewsListResultVo>() {
            @Override
            public void success(ResultTVO<PicNewsListResultVo> data) {

                ArrayList<PicNewsListResultVo> newsListResultVos = data.getData();
                if (pageQuery.isFirstPage()) {
                    picNewsList.clear();
                }
                if (newsListResultVos != null && newsListResultVos.size() > 0) {
                    picNewsList.addAll(newsListResultVos);
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
        //下拉刷新
        srLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pageQuery.resetPage();
                        picNewsListRequest.send();
                    }
                }, 2000);
            }
        });
        //上拉加载更多
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
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

    class RecommandAdapter extends RecyclerView.Adapter<RecommandAdapter.MyViewHoler> {

        @Override
        public MyViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(activity).inflate(R.layout.item_recommend_layout,parent,false);
            return new MyViewHoler(view);
        }

        @Override
        public void onBindViewHolder(MyViewHoler holder, int position) {
            PicNewsListResultVo vo = picNewsList.get(position);
            holder.tvTitle.setText(vo.getTitle());
            holder.tvTime.setText(vo.getCreatetime());
            holder.tvCount.setText(""+vo.getVisit());
            Glide.with(activity).load(AppConfig.imgPath+vo.getImgurl())
                    .centerCrop()
                    .error(R.mipmap.ic_launcher)
                    .into(holder.img);
        }

        @Override
        public int getItemCount() {
            return picNewsList == null ? 0 : picNewsList.size();
        }

        class MyViewHoler extends RecyclerView.ViewHolder{
            TextView tvTitle,tvTime,tvCount;
            ImageView img;
            public MyViewHoler(View itemView) {
                super(itemView);
                tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
                tvTime = (TextView) itemView.findViewById(R.id.tv_time);
                tvCount = (TextView) itemView.findViewById(R.id.tv_number);
                img = (ImageView) itemView.findViewById(R.id.img_news);
            }
        }
    }
}
