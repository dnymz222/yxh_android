package com.xueping.scanpdf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.xueping.scanpdf.helper.MyApplication;
import com.xueping.scanpdf.model.Customer;
import com.xueping.scanpdf.network.NetCallback;
import com.xueping.scanpdf.network.Xunquannetwork;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import okhttp3.Response;

public class AddActivity extends AppCompatActivity {

    private EditText timeText;
    private  EditText wechat_name_tex;
    private  EditText wechat_num_text;
    private EditText wechat_special_text;
    private EditText wangwang_num_text;
    private EditText shop_name_text;
    private EditText company_name_text;
    private EditText detect_company_text;
    private EditText note_text;
    private Button addButton;
    private Button modifyButton;
    private Button deleteButton;

    private  int action;

    private Xunquannetwork mnetWork = new Xunquannetwork();

    private int modify = 0;


    private Customer customer;

    private Handler mhandler = new Handler();

    private Runnable finishAction = new Runnable() {
        @Override
        public void run() {

            finish();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);



        ImageView fanhuiImageView = (ImageView)findViewById(R.id.back);
        fanhuiImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        timeText  =(EditText)findViewById(R.id.timeedixt);
        wechat_name_tex  =(EditText)findViewById(R.id.wechat_name_edixt);
        wechat_num_text  =(EditText)findViewById(R.id.wechat_num_edixt);
        wechat_special_text = (EditText)findViewById(R.id.wechat_special_edixt);
        wangwang_num_text  =(EditText)findViewById(R.id.wangwang_num_edit);
        shop_name_text  =(EditText)findViewById(R.id.shop_name_edit);
        company_name_text = (EditText)findViewById(R.id.company_name_edit);
        detect_company_text = (EditText)findViewById(R.id.detect_company_edit);
        note_text = (EditText)findViewById(R.id.note_edixt);

        addButton = (Button)findViewById(R.id.addbutton);
        deleteButton = (Button)findViewById(R.id.deletebutton);
        modifyButton = (Button)findViewById(R.id.modifybutton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                action = 1;

                submitAction(action);

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action  =3;
                submitAction(action);
            }
        });

        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action = 2;
                submitAction(action);
            }
        });

        Intent intent = getIntent();
        modify = intent.getIntExtra("modify",0);
        if (1 == modify){

            addButton.setVisibility(View.INVISIBLE);

            customer = (Customer) intent.getSerializableExtra("customer");

            timeText.setText(customer.getDate());
            wechat_name_tex.setText(customer.getWechat_name());
            wechat_num_text.setText(customer.getWechat_number());
            wechat_special_text.setText(customer.getWechat_special());
            wangwang_num_text.setText(customer.getWangwang_num());
            shop_name_text.setText(customer.getShop_name());
            detect_company_text.setText(customer.getDetect_company());
            company_name_text.setText(customer.getCompany_name());
            note_text.setText(customer.getNote());




        } else  {
            deleteButton.setVisibility(View.INVISIBLE);
            modifyButton.setVisibility(View.INVISIBLE);
        }




    }


    private void  submitAction(int action){


        HashMap map = new HashMap();
        long timeGetTime =new Date().getTime();

        String index =Long.toString(timeGetTime);


        if (1 == modify){

            map.put("index",customer.getIndex());
        } else {
            map.put("index", index);
        }

        map.put("action",Integer.toString(action));


        if (  timeText.getText().length() <1 ){
            map.put("date","");
        } else  {
            map.put("date",timeText.getText().toString());
        }

        if (  wechat_name_tex.getText().length() <1 ){
            map.put("wechat_name","");
        } else  {
            map.put("wechat_name",wechat_name_tex.getText().toString());
        }

        if (  wechat_num_text.getText().length() <1 ){
            map.put("wechat_num","");
        } else  {
            map.put("wechat_num",wechat_num_text.getText().toString());
        }

        if (  wangwang_num_text.getText().length() <1 ){
            map.put("wangwang_num","");
        } else  {
            map.put("wangwang_num",wangwang_num_text.getText().toString());
        }

        if (  shop_name_text.getText().length() <1 ){
            map.put("shop_name","");
        } else  {
            map.put("shop_name",shop_name_text.getText().toString());
        }


        if (  company_name_text.getText().length() <1 ){
            map.put("company_name","");
        } else  {
            map.put("company_name",  company_name_text.getText().toString());
        }


        if (  detect_company_text.getText().length() <1 ){
            map.put("detect_company","");
        } else  {
            map.put("detect_company",detect_company_text.getText().toString());
        }

        if (  wechat_special_text.getText().length() <1 ){
            map.put("wechat_special","");
        } else  {
            map.put("wechat_special",wechat_special_text.getText().toString());
        }


        if (  note_text.getText().length() <1 ){
            map.put("note","");
        } else  {
            map.put("note",note_text.getText().toString());
        }

        Log.i("action",map.toString());
//


        mnetWork.CustomerAction(map, new NetCallback() {
            @Override
            public void onFailure(IOException e, int code) {
                final IOException ae = e;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(MyApplication.getApplication(), ae.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onSuccess(Object object, Response response) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MyApplication.getApplication(), "成功", Toast.LENGTH_SHORT).show();

                        mhandler.postDelayed(finishAction,3000);
                    }
                });

            }
        });





    }
}