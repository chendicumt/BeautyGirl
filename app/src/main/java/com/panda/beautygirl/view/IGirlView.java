package com.panda.beautygirl.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import com.panda.beautygirl.R;
import com.panda.beautygirl.adapter.BeautyGirlRecAdapter;
import com.panda.beautygirl.bean.GirlBean;
import com.panda.beautygirl.presenter.MyPresenter;
import com.panda.beautygirl.utils.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PC on 2017/10/13.
 */

public interface IGirlView {

    void showProgress();
    void hideProgress();
    void getGirlView(GirlBean girlBean);

}
