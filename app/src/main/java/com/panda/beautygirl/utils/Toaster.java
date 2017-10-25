package com.panda.beautygirl.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by PC on 2017/10/15.
 */

public class Toaster {

    public static void toast(Context context,String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}
