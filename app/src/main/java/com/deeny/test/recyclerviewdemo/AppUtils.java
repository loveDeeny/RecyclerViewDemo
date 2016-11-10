package com.deeny.test.recyclerviewdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.WindowManager;



import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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





    public static String getFormayStr(Date date, String type){
        if("time".equals(type)){
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            return sdf.format(date);
        }else{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(date);
        }
    }

    public static String getFormatTimeStr(int hour, int minute){
        String houeStr = null;
        String minuteStr = null;
        houeStr = hour >= 10?hour+"" : "0"+hour;
        minuteStr = minute >= 10?minute+"":"0"+minute;
        return houeStr+":"+minuteStr;
    }



    /**
     * 下载网络图片转化成bitmap
     * @param path
     * @return
     * @throws IOException
     */
    public static Bitmap getBitmap(String path) throws IOException {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if(conn.getResponseCode() == 200){
            InputStream inputStream = conn.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        }
        return null;

    }


    /**获取星期几*/
    public static String getDayOfWeek(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String weekStr = null;
        int week = cal.get(Calendar.DAY_OF_WEEK);
        switch (week){
            case 2:
                weekStr = "星期一";
                break;
            case 3:
                weekStr = "星期二";
                break;
            case 4:
                weekStr = "星期三";
                break;
            case 5:
                weekStr = "星期四";
                break;
            case 6:
                weekStr = "星期五";
                break;
            case 7:
                weekStr = "星期六";
                break;
            case 1:
                weekStr = "星期日";
                break;

        }
        return weekStr;
    }



    public static String formatTimeToStr(int duration){
        int time = Math
                .round(duration / 1000);
        String currentStr = String.format("%02d:%02d",
                time / 60, time % 60);
        return  currentStr;
    }


}
