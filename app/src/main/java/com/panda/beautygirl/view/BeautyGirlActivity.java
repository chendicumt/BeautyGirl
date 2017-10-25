package com.panda.beautygirl.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.panda.beautygirl.R;
import com.panda.beautygirl.utils.Toaster;
import com.panda.beautygirl.view.fragment.FragmentAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PC on 2017/10/13.
 */

public class BeautyGirlActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    private int count=2;
    String[] title=new String[]{"图片","聊天","发现","我的"};
    int[] drawable=new int[]{R.drawable.tab1_selected,R.drawable.tab2, R.drawable.tab3, R.drawable.tab4};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauty_girl);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar =getSupportActionBar();
        if(actionBar!=null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.beauty_girl_launch);
        }
        initFragment();
    }


    public void initFragment()
    {
        mViewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
//        左右各缓存3个fragment页面
        mViewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(mViewPager);
        for(int i=0;i<4;i++)
        {
            tabLayout.getTabAt(i).setCustomView(getTabView(i));
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                changTab(tab,true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                changTab(tab,false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public View getTabView(final int position){
        LayoutInflater inflater=LayoutInflater.from(this);
        View view=inflater.inflate(R.layout.tab_view,null);
        ImageView imageView=(ImageView)view.findViewById(R.id.tab_image);
        TextView textView=(TextView)view.findViewById(R.id.tab_text);
        imageView.setImageResource(drawable[position]);
        textView.setText(title[position]);
        if(position==0)
        {
            textView.setTextColor(ContextCompat.getColor(this,R.color.tabTextColorSelected));
        }
        else
        {
            textView.setTextColor(ContextCompat.getColor(this,R.color.tabTextColor));
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(position);
            }
        });
        return view;
    }

    public void changTab(TabLayout.Tab tab,boolean selected)
    {
        View view =tab.getCustomView();
        ImageView imageView=(ImageView)view.findViewById(R.id.tab_image);
        TextView textView=(TextView)view.findViewById(R.id.tab_text);
        if(selected)
        {
            textView.setTextColor(ContextCompat.getColor(this,R.color.tabTextColorSelected));
            if(tab==tabLayout.getTabAt(0))
            {
                imageView.setImageResource(R.drawable.tab1_selected);
            }else if(tab==tabLayout.getTabAt(1))
            {
                imageView.setImageResource(R.drawable.tab2_selected);
            }
            else if(tab==tabLayout.getTabAt(2))
            {
                imageView.setImageResource(R.drawable.tab3_selected);
            }
            else if(tab==tabLayout.getTabAt(3))
            {
                imageView.setImageResource(R.drawable.tab4_selected);
            }
        }
        else{
            textView.setTextColor(ContextCompat.getColor(this,R.color.tabTextColor));
            if(tab==tabLayout.getTabAt(0))
            {
                imageView.setImageResource(R.drawable.tab1);
            }else if(tab==tabLayout.getTabAt(1))
            {
                imageView.setImageResource(R.drawable.tab2);
            }
            else if(tab==tabLayout.getTabAt(2))
            {
                imageView.setImageResource(R.drawable.tab3);
            }
            else if(tab==tabLayout.getTabAt(3))
            {
                imageView.setImageResource(R.drawable.tab4);
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

//        Toaster.toast(this,"onPageScrolled");
    }

    @Override
    public void onPageSelected(int position) {

//        Toaster.toast(this,"onPageSelected");
    }

    @Override
    public void onPageScrollStateChanged(int state) {
//        Toaster.toast(this,"onPageScrollStateChanged");
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if(count==2)
        {
            Toaster.toast(this,"再按一次退出");
            count--;
        }
        else
        {
            this.finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
