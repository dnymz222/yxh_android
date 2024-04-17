package com.xueping.scanpdf.network;

import java.io.IOException;

import okhttp3.Response;

public interface NetCallback {

    void onFailure(IOException e,int code);

    void onSuccess(Object object , Response response);

}
