package com.panda.beautygirl.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.panda.beautygirl.R;
import com.panda.beautygirl.bean.GirlBean;
import com.panda.beautygirl.utils.MyApplication;
import java.util.List;

/**
 * Created by PC on 2017/10/13.
 */

public class BeautyGirlRecAdapter extends RecyclerView.Adapter<BeautyGirlRecAdapter.MyViewHolder>{

    private List<GirlBean.ShowapiResBodyBean.NewslistBean> list;
    private OnItemClickListener onItemClickListener;
    private View view;
    public BeautyGirlRecAdapter(List<GirlBean.ShowapiResBodyBean.NewslistBean> list)
    {
        this.list=list;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener=onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        view=inflater.inflate(R.layout.recyxle_girl,parent,false);
        MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        Glide.with(MyApplication.getContext())
                .load(list.get(position).getPicUrl())
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(holder.imageView);

        if(onItemClickListener!=null)
        {
            holder.girlView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position=holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.girlView,position);

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        View girlView;
//        @BindView(R.id.image_girl)
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            girlView=itemView;//保存外层视图
            imageView=(ImageView)itemView.findViewById(R.id.image_girl);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
