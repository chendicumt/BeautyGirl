package com.panda.beautygirl.view.fragment;

import android.app.ProgressDialog;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.panda.beautygirl.R;
import com.panda.beautygirl.adapter.BeautyGirlRecAdapter;
import com.panda.beautygirl.adapter.OnItemClickListener;
import com.panda.beautygirl.bean.GirlBean;
import com.panda.beautygirl.presenter.MyPresenter;
import com.panda.beautygirl.utils.Logger;
import com.panda.beautygirl.utils.MyApplication;
import com.panda.beautygirl.utils.NetInfo;
import com.panda.beautygirl.utils.Toaster;
import com.panda.beautygirl.view.IGirlView;
import com.panda.beautygirl.view.ShowPic;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PC on 2017/10/16.
 */

public class FirstFragment extends Fragment implements IGirlView{

    private ProgressDialog progressDialog;
    private MyPresenter presenter;
    private BeautyGirlRecAdapter adapter;
    private GridLayoutManager manager;
    private int lastItem;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout refreshLayout;
    private List<GirlBean.ShowapiResBodyBean.NewslistBean> list=new ArrayList<>();
    private View view=null;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_first,container,false);
        ButterKnife.bind(this,view);
        progressDialog = new ProgressDialog(getContext(),ProgressDialog.STYLE_SPINNER);
        manager=new GridLayoutManager(MyApplication.getContext(),2);
        recyclerView.setLayoutManager(manager);
        if(NetInfo.netWorkState(MyApplication.getContext())) {
            presenter = new MyPresenter(this);
            presenter.prepare();
        }
        downSwipRefresh();
//        upSwipRefresh();
        return view;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void getGirlView(GirlBean girlBean) {

        Logger.d("BeautyGirlActivity", girlBean.getShowapi_res_body().getNewslist().get(1).getTitle());
        for (int i = 0; i < girlBean.getShowapi_res_body().getNewslist().size(); i++) {
            list.add(i, girlBean.getShowapi_res_body().getNewslist().get(i));
        }
        Logger.d("BeautyGirlActivity", list.size() + "");
        adapter = new BeautyGirlRecAdapter(list);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MyApplication.getContext(), ShowPic.class);
                intent.putExtra("picUrl", list.get(position).getPicUrl());
                startActivity(intent);
                }
            });
    }

    public void downSwipRefresh()
    {
        refreshLayout.setColorSchemeColors(ContextCompat.getColor(getContext(),R.color.colorAccent));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(NetInfo.netWorkState(MyApplication.getContext()))
                        {
                            presenter.prepare();
                        }

                        adapter.notifyDataSetChanged();
                        refreshLayout.setRefreshing(false);
                    }
                },1000);
            }
        });
    }

    public void upSwipRefresh()
    {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//              判断RecyclerView的状态，停止滚动，同时是最后一个可见Item
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastItem+1==adapter.getItemCount()) {

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (NetInfo.netWorkState(MyApplication.getContext())) {
                                Toaster.toast(getContext(),"正在加载...");
                                presenter.prepare();
                                adapter.notifyDataSetChanged();
                            }
                        }
                    },1000);
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                lastItem=manager.findLastVisibleItemPosition();
            }
        });

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.removeDisposable();
    }

}
