package com.panda.beautygirl.presenter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by PC on 2017/10/13.
 */

public class BasePresenter {

//    统一管理Disposable
    private CompositeDisposable compositeDisposable;

    public void addDisposable(Disposable disposable)
    {
        if (compositeDisposable==null||compositeDisposable.isDisposed()) {

            compositeDisposable=new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    public void removeDisposable()
    {
        if (compositeDisposable!=null) {
            compositeDisposable.dispose();
        }
    }
}
