package com.xueping.scanpdf.helper;

import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;


//import com.umeng.commonsdk.UMConfigure;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MyApplication extends Application {


    private static MyApplication application;
//    public static String APP_ID = "wx11b0be43c651e385";
    public static String localversion;





    private static String hasshowalertKey = "hasshowalertKey";

    private static String hastranskey = "hastranskey";

    public static  String defalutstationkey  = "defalutstationkey ";


    public static  String defalutstation_newkey  = "defalutstation_newkey";







    @Override
    public void onCreate() {
        super.onCreate();

//        IWXAPI api = WXAPIFactory.createWXAPI(this, APP_ID, false);
//        api.registerApp(APP_ID);








        application = this;

        localversion = MyApplication.getLocalVersion(this);

//        transDefaultSation();

//        String secret  = SecretUtil.md5Decode32("123456fish");
//        Log.i("secret",secret);



    }




    public static String getLocalVersion(Context ctx) {
        String localVersion = "0";
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }






    public static boolean isPkgInstalled(Context context, String pkgName) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(pkgName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
            e.printStackTrace();
        }
        return packageInfo != null;
    }








    public boolean hasshowAhert(){


        SharedPreferences sharedPreferences= getSharedPreferences("data", Context.MODE_PRIVATE);
        //步骤2： 实例化SharedPreferences.Editor对象
        SharedPreferences.Editor editor = sharedPreferences.edit();

        return sharedPreferences.getBoolean(hasshowalertKey,false);
    }

    public void showalert(){
        SharedPreferences sharedPreferences= getSharedPreferences("data", Context.MODE_PRIVATE);
        //步骤2： 实例化SharedPreferences.Editor对象
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(hasshowalertKey,true);
        editor.commit();

    }





    public static MyApplication getApplication() {
        return application;
    }

}
