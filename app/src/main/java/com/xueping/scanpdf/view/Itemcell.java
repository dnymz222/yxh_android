package com.xueping.scanpdf.view;

import android.content.Context;
import android.telecom.TelecomManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xueping.scanpdf.R;

public class Itemcell extends RelativeLayout {


    private TextView    textView;

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public Itemcell (Context context){

        super(context);
        initView(context);
    }

    public Itemcell(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        initView(context);
    }

    private void  initView(Context context){

        LayoutInflater.from(context).inflate(R.layout.cell_layout, this, true);

        textView = (TextView)findViewById(R.id.content);

    }



}
