package com.panda.beautygirl.model;

import com.panda.beautygirl.bean.GirlBean;
import com.panda.beautygirl.net.UseRetrofit;
import com.panda.beautygirl.utils.Logger;
import com.panda.beautygirl.utils.MyApplication;
import com.panda.beautygirl.utils.NetInfo;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by PC on 2017/10/13.
 */

public class MyGirlModel implements IGirlModel {

    private CallbackModel callbackModel;
    public MyGirlModel(CallbackModel callbackModel)
    {
        this.callbackModel=callbackModel;
    }
    Disposable disposable=null;
    @Override
    public Disposable getGirlModel() {

            Observable<GirlBean> observable= UseRetrofit.getIntance().getObservable();

            disposable=observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<GirlBean>() {
                        @Override
                        public void accept(GirlBean girlBean) throws Exception {
                            callbackModel.onSuccess(girlBean);
                            Logger.d("MyGirlModel","success");
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Logger.d("MyGirlModel","throwable");
                        }
                    });
        return disposable;
    }
}
