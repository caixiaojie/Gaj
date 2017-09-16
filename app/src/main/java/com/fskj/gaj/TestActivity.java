package com.fskj.gaj;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fskj.gaj.Remote.ResultListInterface;
import com.fskj.gaj.Remote.ResultTVO;
import com.fskj.gaj.Util.PageQuery;
import com.fskj.gaj.request.PicNewsListRequest;
import com.fskj.gaj.vo.PicNewsListResultVo;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private XRecyclerView xRecyclerView;
    private Activity activity;
    private PicNewsListRequest picNewsListRequest;
    private List<PicNewsListResultVo> picNewsList;
    private PageQuery pageQuery;
    private CommonAdapter<PicNewsListResultVo> commonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        activity = TestActivity.this;
        xRecyclerView = (XRecyclerView) findViewById(R.id.xRecyclerView);
        picNewsList = new ArrayList<>();
        initData();
        initAdapter();
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                pageQuery.resetPage();
                picNewsListRequest.send();
                xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                pageQuery.nextPage();
                picNewsListRequest.send();
                xRecyclerView.loadMoreComplete();
            }
        });

        picNewsListRequest.send();
    }

    private void initAdapter() {
        commonAdapter = new CommonAdapter<PicNewsListResultVo>(activity, R.layout.item_recommend_layout, picNewsList) {
            @Override
            protected void convert(ViewHolder holder, PicNewsListResultVo picNewsListResultVo, int position) {
                holder.setText(R.id.tv_title,picNewsList.get(position).getTitle());
                holder.setText(R.id.tv_time,picNewsList.get(position).getCreatetime());
                holder.setText(R.id.tv_number,picNewsList.get(position).getVisit()+"");
                Glide.with(activity).load(AppConfig.imgPath+picNewsList.get(position).getImgurl())
                        .error(R.mipmap.ic_launcher)
                        .into((ImageView) holder.getView(R.id.img_news));
            }
        };
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
        xRecyclerView.setAdapter(commonAdapter);

        /**
         * new CommonAdapter(activity,R.layout.item_recommend_layout,picNewsList) {
        @Override
        protected void convert(ViewHolder holder, Object o, int position) {
        holder.setText(R.id.tv_title,picNewsList.get(position).getTitle());
        holder.setText(R.id.tv_time,picNewsList.get(position).getCreatetime());
        holder.setText(R.id.tv_number,picNewsList.get(position).getVisit()+"");
        Glide.with(activity).load(AppConfig.imgPath+picNewsList.get(position).getImgurl())
        .into((ImageView) holder.getView(R.id.img_news));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }
        }
         */
    }

    private void initData() {
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
                commonAdapter.notifyDataSetChanged();
            }

            @Override
            public void error(String errmsg) {

            }
        });
    }
}
