package com.panda.beautygirl.presenter;

import com.panda.beautygirl.bean.GirlBean;
import com.panda.beautygirl.model.CallbackModel;
import com.panda.beautygirl.model.IGirlModel;
import com.panda.beautygirl.model.MyGirlModel;
import com.panda.beautygirl.utils.MyApplication;
import com.panda.beautygirl.utils.NetInfo;
import com.panda.beautygirl.view.IGirlView;

/**
 * Created by PC on 2017/10/13.
 */

public class MyPresenter extends BasePresenter {

    private IGirlView iGirlView;
    private IGirlModel iGirlModel;

    public MyPresenter(IGirlView iGirlView)
    {
        this.iGirlView=iGirlView;
        iGirlModel=new MyGirlModel(callbackModel);
    }

   public CallbackModel callbackModel=new CallbackModel() {
        @Override
        public void onSuccess(GirlBean girlBean) {
            iGirlView.hideProgress();
            iGirlView.getGirlView(girlBean);
        }
    };
    /**
     * 加入Disposable
     */
    public void prepare()
    {
        iGirlView.showProgress();
        addDisposable(iGirlModel.getGirlModel());
    }
}
