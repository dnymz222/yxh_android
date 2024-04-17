package com.xueping.scanpdf.model;

import java.io.Serializable;

public class Customer  implements Serializable {


    private  String  index ;
    private  String date ;
    private  String  wechat_number ;
    private  String  wechat_name ;
    private  String  wangwang_num;
    private  String  company_name ;
    private  String  shop_name ;
    private  String  wechat_special;
    private  String  detect_company ;
    private  String  note;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWechat_number() {
        return wechat_number;
    }

    public void setWechat_number(String wechat_number) {
        this.wechat_number = wechat_number;
    }

    public String getWechat_name() {
        return wechat_name;
    }

    public void setWechat_name(String wechat_name) {
        this.wechat_name = wechat_name;
    }

    public String getWangwang_num() {
        return wangwang_num;
    }

    public void setWangwang_num(String wangwang_num) {
        this.wangwang_num = wangwang_num;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getWechat_special() {
        return wechat_special;
    }

    public void setWechat_special(String wechat_special) {
        this.wechat_special = wechat_special;
    }

    public String getDetect_company() {
        return detect_company;
    }

    public void setDetect_company(String detect_company) {
        this.detect_company = detect_company;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public  String descriptionString(){

        String string = String.format("日期:%s, 微信名称:%s, 微信号:%s, 旺旺号:%s, 公司名称:%s, 店铺:%s, 微信专属:%s, 检测公司:%s, 备注:%s ",date,wechat_name,wechat_number,wangwang_num,company_name,shop_name,wechat_special,detect_company,note);

        return string;
    }
}
