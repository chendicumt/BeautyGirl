package com.panda.beautygirl.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.panda.beautygirl.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PC on 2017/10/13.
 */

public class ShowPic extends AppCompatActivity {

    @BindView(R.id.toolbar_detail)
    Toolbar toolbar;
    @BindView(R.id.image_girl_detail)
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pic);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.back);
        }

        Intent intent=getIntent();
        String url=intent.getStringExtra("picUrl");

        Glide.with(this).load(url).asBitmap()
                .fitCenter()
//                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
//                .override(1000,1500)
                .into(imageView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                this.finish();
                break;
            default:
                break;
        }
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Glide.get(this).clearMemory();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Glide.get(this).clearMemory();
    }
}
