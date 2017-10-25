package com.panda.beautygirl.net;

import com.panda.beautygirl.bean.GirlBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by PC on 2017/10/13.
 */

public interface NetApi {

    @FormUrlEncoded
    @POST("197-1")
    Observable<GirlBean> getGirl(@Field("showapi_appid") String showapi_appid,
                                 @Field("showapi_sign") String showapi_sign,
                                 @Field("num") int num,
                                 @Field("page") int page);
}
