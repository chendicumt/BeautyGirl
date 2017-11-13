package com.panda.beautygirl.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by PC on 2017/10/15.
 */

public class NetInfo {

    public static boolean netWorkState(Context context) {
//        管理和网络相关的操作
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//       获取网络相关信息
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info == null || !info.isAvailable()) {

            return false;
        } else {
            if (info.getType() == manager.TYPE_WIFI) {
//                Toaster.toast(context, "当前使用WiFi网络");
            } else if (info.getType() == manager.TYPE_MOBILE) {
//                Toaster.toast(context, "当前使用移动网络");
            }
            return true;
        }

    }
}
