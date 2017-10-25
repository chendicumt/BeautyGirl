package com.panda.beautygirl.utils;

import android.util.Log;

/**
 * Created by PC on 2017/10/12.
 */

public class Logger {

    public static final int V=1;
    public static final int D=2;
    public static final int I=3;
    public static final int W=4;
    public static final int E=5;
    public static final int N=6;

    public static int level=V;


    public static void v(String s1,String s2)
    {
        if(level<=V)
        {
            Log.d(s1, s2);
        }

    }
    public static void d(String s1,String s2)
    {
        if(level<=D)
        {
            Log.d(s1, s2);
        }
    }
    public static void i(String s1,String s2)
    {
        if(level<=I)
        {
            Log.d(s1, s2);
        }
    }
    public static void w(String s1,String s2)
    {
        if(level<=W)
        {
            Log.d(s1, s2);
        }
    }
    public static void e(String s1,String s2)
    {
        if(level<=E)
        {
            Log.d(s1, s2);
        }
    }

}
