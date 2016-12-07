package com.deeny.test.recyclerviewdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;


import com.deeny.test.recyclerviewdemo.adapter.MyAdapter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by deeny on 2016/3/23.
 * 静态工具方法
 */
public class AppUtils {
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private SharedPreferences gesture;
    private SharedPreferences.Editor gestureEditor;

    public AppUtils(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("loginActivity", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gesture = context.getSharedPreferences("gesture", Context.MODE_PRIVATE);
        gestureEditor = gesture.edit();
    }
    public static int[] getScreenDispaly(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = windowManager.getDefaultDisplay().getWidth();// 手机屏幕的宽度
        int height = windowManager.getDefaultDisplay().getHeight();// 手机屏幕的高度
        int result[] = { width, height };
        return result;
    }


    //判断是否有网
    public static boolean checkNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        NetworkInfo netWorkInfo = info[i];
                        if (netWorkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                            return true;
                        } else if (netWorkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public static void getData(final int page, final List<String> list, final Handler handler) {
        // 开子线程模拟下载数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(list == null){
                    return;
                }

                for (int i = page*10; i < page*10+10; i++) {
                    list.add("第"+i+"条");
                }
                Message message = new Message();
                message.what = 100;
                message.obj = list;
                handler.sendMessage(message);
                /*runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(myadapter != null){
                            myadapter.notifyDataSetChanged();
                        }else {
                            rv_recycler.setAdapter(myadapter = new MyAdapter(MainActivity.this,list));
                        }

                        if(isRefresh){
                            refreshLayout.finishRefreshing();
                        }else{
                            refreshLayout.finishLoadmore();
                        }
                        *//*imageView.clearAnimation();
                        imageView.setVisibility(View.GONE);*//*
                    }
                });*/
            }
        }).start();
    }

}
