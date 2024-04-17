package com.xueping.scanpdf.network;

import java.io.IOException;
import java.util.Map;

import okhttp3.Response;

public class Xunquannetwork extends Basenetwork {

    private static final String  CouponsearchPath = "/xunquan/search";
    private static final String  AllcatePath = "/xunquan/allcate";
    private static final String  catePath = "/fish/cate";
    private static final String  pinpaicatePath = "/xunquan/pinpai/cate";
    private static final String  muyincatePath = "/xunquan/muyin/cate";

    private static final String  CatCouponPath  = "/xunquan/cat/";
    private static final String  couponRecommendPath = "/xunquan/recommend";
    //    #define homePath @"/xunquan/home"
//            #define sec(secstring)   [NSString stringWithFormat:@"/xunquan/sec/%@",secstring]
    private static final String homepath = "/xunquan/home";
    private static final String secpath = "/xunquan/sec/";
    private static final String configpath = "/xunquan/config";
    private static final String specialItemPath = "/xunquan/column/config";
    private static final  String subcatesearchpath = "/xunquan/search/cat/";
    private static final  String qianggouAllcatepath = "/qianggou/cate";
    private static final  String  qianggoucatepath = "/qianggou/new/cat/";
    private static final  String  qianggounearcatepath = "/qianggou/near/cat/";
    private static final  String pintuanlistpath = "/pintuan/list";
    private static final  String jusearchpath = "/ju/search";
    private static final  String searchHintPath = "/xunquan/search/hint";
    private static final String specialPath = "/xunquan/special/";
    private static final  String couponcheckPath = "/xunquan/search/check";
    private static final  String fishingsearchpath = "/xunquan/search/cat/50013886";

    private static final  String lunitidalSitePath = "/lunitidalsite";
    private static final  String chinatidespath = "/tide/china/forecast";

    private static final String fishbannerpath = "/fish/banner";
    private static final String appitemspath = "/app/items";
    private static final String luanchadpath  =  "/launch/ad";

    private static final String adbannerpath  =  "/ad/banner";
    private static final String addownloadpath = "/help/promote";
    private static final String addownload_clickpath = "/help/promote/click";



    private static final  String worldtidepath = "/tide/world/forecast/v2";


    private static final String requesrcodepath = "/user/requestcode";
    private static final String verfycodeloginpath = "/user/verifycode/login";
    private static final String passwordlonginpath = "/user/password/longin";
    private static final String setpasswordpath = "/user/setpassword";
    private static final String buyvippath = "/user/buyvip";

    private static final String alipaypath = "/alipay/fish/app/pay";

    private static final String wavepath  = "/storm/wave";

    private static final String hgtpath = "/hgt/elevation";

    private static final String userinfopath = "/user/userinfo";
    private static final String userdeletepath = "/user/delete";
    private static final String biopath = "/storm/bio";


    private static final String customerlistpath ="/yxx/customer/list";

    private static final String customersearchpath = "/yxx/customer/search";

    private static final String customeractionpath = "/yxx/customer/action";




    public Xunquannetwork() {

    }

    public void searchCoupon(Map paramaters, final NetCallback callback){

        super.sentGetRequest(CouponsearchPath, paramaters, new NetCallback() {
            @Override
            public void onFailure(IOException e,int code) {
                callback.onFailure(e,code);
            }

            @Override
            public void onSuccess(Object object, Response response) {
                callback.onSuccess(object,response);
            }
        });


    }

    public void catCoupon(String url, String cat, Map paramaters, final NetCallback callback){

        String path = url + cat ;
        super.sentGetRequest(path, paramaters, new NetCallback() {
            @Override
            public void onFailure(IOException e,int code) {
                callback.onFailure(e,code);
            }

            @Override
            public void onSuccess(Object object, Response response) {
                callback.onSuccess(object,response);

            }
        });


    }

    public void catCouponUrl(String url,  Map paramaters, final NetCallback callback){

        String path = url ;
        super.sentGetRequest(path, paramaters, new NetCallback() {
            @Override
            public void onFailure(IOException e,int code) {
                callback.onFailure(e,code);
            }

            @Override
            public void onSuccess(Object object, Response response) {
                callback.onSuccess(object,response);

            }
        });


    }

    public void allcate(String s, Map paramaters, final NetCallback callback){

        super.sentGetRequest(AllcatePath, paramaters, new NetCallback() {
            @Override
            public void onFailure(IOException e,int code) {
                callback.onFailure(e,code);
            }

            @Override
            public void onSuccess(Object object, Response response) {
                callback.onSuccess(object,response);
            }
        });


    }



    public void customersearch(Map paramaters, final NetCallback callback){

        super.sentGetRequest(customersearchpath, paramaters, new NetCallback() {
            @Override
            public void onFailure(IOException e,int code) {
                callback.onFailure(e,code);
            }

            @Override
            public void onSuccess(Object object, Response response) {
                callback.onSuccess(object,response);
            }
        });


    }

    public void customerlist(Map paramaters, final NetCallback callback){

        super.sentGetRequest(customerlistpath, paramaters, new NetCallback() {
            @Override
            public void onFailure(IOException e,int code) {
                callback.onFailure(e,code);
            }

            @Override
            public void onSuccess(Object object, Response response) {
                callback.onSuccess(object,response);
            }
        });


    }



    public void CustomerAction(Map paramaters, final NetCallback callback){

        super.sentGetRequest(customeractionpath, paramaters, new NetCallback() {
            @Override
            public void onFailure(IOException e,int code) {
                callback.onFailure(e,code);
            }

            @Override
            public void onSuccess(Object object, Response response) {
                callback.onSuccess(object,response);
            }
        });


    }

















}
