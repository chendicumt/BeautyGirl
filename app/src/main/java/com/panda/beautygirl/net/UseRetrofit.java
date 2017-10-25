package com.panda.beautygirl.net;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.panda.beautygirl.bean.GirlBean;

import java.util.Random;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by PC on 2017/10/13.
 */

public class UseRetrofit {

    private static UseRetrofit useRetrofit;
    public static final String baseUrl="http://route.showapi.com/";
    public static final String showapi_appid = "47720";
    public static final String showapi_sign = "57b54e7a1206495e860a3c700c9e7e57";
    public static final int num = 10;
//    public static final int page = (int)(Math.random()*40+1);

    public Retrofit retrofit=new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    NetApi netApi=retrofit.create(NetApi.class);

    public Observable<GirlBean> getObservable()
    {
        int page=(int)(Math.random()*40+1);
        return netApi.getGirl(showapi_appid,showapi_sign,num,page);
    }

    public static UseRetrofit getIntance()
    {
        if(useRetrofit==null)
        {
            useRetrofit=new UseRetrofit();
        }
        return useRetrofit;
    }
}
