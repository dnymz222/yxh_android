package com.xueping.scanpdf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.xueping.scanpdf.helper.MyApplication;
import com.xueping.scanpdf.helper.RecycleViewDivider;
import com.xueping.scanpdf.model.Customer;
import com.xueping.scanpdf.network.NetCallback;
import com.xueping.scanpdf.network.Xunquannetwork;
import com.xueping.scanpdf.view.Itemcell;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Response;

public class SearchActivity extends AppCompatActivity implements PullLoadMoreRecyclerView.PullLoadMoreListener{

    private RecyclerView mRecyclerView;
    private PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private int mCount = 1;
    private FishPinpaiGoodsAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private Xunquannetwork mnetwork;
    private SearchView msearchView;

    private String queryString ;


    private Handler mhandler = new Handler();

    private Runnable finishAction = new Runnable() {
        @Override
        public void run() {

            finish();

        }
    };


    private Runnable showkeyboard = new Runnable() {
        @Override
        public void run() {

            msearchView.setIconified(false);

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ImageView fanhuiImageView = (ImageView)findViewById(R.id.liebiao);
        fanhuiImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(SearchActivity.this,MainActivity.class);
               startActivity(intent);
            }
        });




        mPullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) findViewById(R.id.secondcategoryrecycleview);
        //获取mRecyclerView对象
        mRecyclerView = mPullLoadMoreRecyclerView.getRecyclerView();
        //代码设置scrollbar无效？未解决！
        mRecyclerView.setVerticalScrollBarEnabled(true);
        //设置下拉刷新是否可见
        //mPullLoadMoreRecyclerView.setRefreshing(true);
        //设置是否可以下拉刷新
        mPullLoadMoreRecyclerView.setPullRefreshEnable(true);
        //设置是否可以上拉刷新
        mPullLoadMoreRecyclerView.setPushRefreshEnable(true);
        //显示下拉刷新
        mPullLoadMoreRecyclerView.setRefreshing(false);
        //设置上拉刷新文字
        mPullLoadMoreRecyclerView.setFooterViewText("loading");
        //设置上拉刷新文字颜色
        //mPullLoadMoreRecyclerView.setFooterViewTextColor(R.color.white);
        //设置加载更多背景色
        //mPullLoadMoreRecyclerView.setFooterViewBackgroundColor(R.color.colorBackground);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this){
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        };
        mPullLoadMoreRecyclerView.setLinearLayout();

        mRecyclerView.setLayoutManager(linearLayoutManager);


        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        mAdapter = new FishPinpaiGoodsAdapter(this);
        mPullLoadMoreRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addItemDecoration(new RecycleViewDivider(this,  LinearLayoutManager.HORIZONTAL, 1, 0xffdfdfdf));
        //mRecyclerView.setLayoutManager(linearLayoutManager);
        mnetwork = new Xunquannetwork();

        msearchView = (SearchView) findViewById(R.id.searchresultserchview);


        mhandler.postDelayed(showkeyboard,250);

        msearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {

                queryString = query;
                mCount = 1;
                loadData(mCount);

//                if (showHint) {
//                    suggestView.setVisibility(View.INVISIBLE);
//                    mRecyclerView.setVisibility(View.VISIBLE);
//                    showHint = false;
//                }


                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
//                if (!TextUtils.isEmpty(newText)){
//                    mListView.setFilterText(newText);
//                }else{
//                    mListView.clearTextFilter();


//                }

//                if (!showHint) {
//                    suggestView.setVisibility(View.VISIBLE);
//                    mRecyclerView.setVisibility(View.INVISIBLE);
//                    showHint = true;
//                }


//                loadHint(newText);


                return false;
            }


        });



//
//        setCursorIcon();

//        mCount = 1;
//        loadData(mCount);
    }



    @Override
    public void onRefresh() {
        mCount = 1;
        loadData(1);
    }

    @Override
    public void onLoadMore() {
        mCount ++;
        loadData(mCount);
    }

    void loadData(final int count) {

        if (queryString == null) {
            mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
            return;
        }

        HashMap map  =  new HashMap();
        map.put("page",Integer.toString(mCount));
        map.put("q",queryString);










        mnetwork.customersearch(map, new NetCallback() {
            @Override
            public void onFailure( IOException e,int code) {
                final IOException ae = e;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                        Toast.makeText(MyApplication.getApplication(), ae.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onSuccess(Object object, Response response) {

                if (object == null) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                             Toast.makeText(MyApplication.getApplication(), "没有数据", Toast.LENGTH_SHORT).show();
                        }
                    });

                    return;

                }


                final Object mobject = object;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        List<Customer> couponItemnlist = JSON.parseArray(mobject.toString(),Customer.class);

                        if (count==1) {
                            mAdapter.getMdataList().clear();

                            if (couponItemnlist.size() == 0) {

                                Toast.makeText(MyApplication.getApplication(), "没有数据", Toast.LENGTH_SHORT).show();
                            }


                        }else  {

                            if (couponItemnlist.size() == 0) {

                                Toast.makeText(MyApplication.getApplication(), "没有更多了", Toast.LENGTH_SHORT).show();
                            }
                        }
                        mAdapter.getMdataList().addAll(couponItemnlist);
                        mAdapter.notifyDataSetChanged();
                        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();

                    }
                });

            }
        });




    }



    public class FishPinpaiGoodsAdapter extends RecyclerView.Adapter<FishPinpaiGoodsAdapter.ViewHolder> {
        private List<Customer> mdataList = new ArrayList<>() ;
        private Context mcontext;



        // Provide a reference to the type of views that you are using
        // (custom viewholder)
        public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener,View.OnLongClickListener {
            public Itemcell mitemView;
            private int mPosition;

            public ViewHolder(Itemcell v) {
                super(v);
                v.setOnClickListener(this);
                mitemView = v;
            }

            public void setmPosition(int mPosition) {
                this.mPosition = mPosition;
            }
            @Override
            public boolean onLongClick(View v) {
                // TODO 自动生成的方法存根
                return false;
            }

            @Override
            public void onClick(View v) {
                // TODO 自动生成的方法存根

                Customer item = (Customer) mdataList.get(mPosition);
//
//                if (item.getCoupon_share_url().length() >0){
//                                    String url ;
//                    if (item.getCoupon_share_url().contains("https:")){
//                        url = item.getCoupon_share_url();
//                    } else {
//                        url = "https:"+item.getCoupon_share_url();
//                    }
//
//                    BaichuanHandler.showUrl(FishPinpaiGoodsActivity.this, url);
//                } else  {
//
//                    BaichuanHandler.showGoodsItem(FishPinpaiGoodsActivity.this,item.getItem_id());
//                }




                Intent intent = new Intent(SearchActivity.this,AddActivity.class);
                intent.putExtra("modify",1);
                intent.putExtra("customer",item);
                startActivity(intent);




                //baichuanHandler.showUrl(getActivity(),mdataList.get(mPosition).getCouponPromotUrl());
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)


        public FishPinpaiGoodsAdapter(Context context) {

            mcontext = context;

        }

        public List<Customer> getMdataList() {
            return mdataList;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public FishPinpaiGoodsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                                 int viewType) {
            // create a new view
//            couponItemView v =(View) LayoutInflater.from(parent.getContext()).inflate(
//                    R.layout.couponlist_item, parent, false);
            // set the view's size, margins, paddings and layout parameters
            Itemcell v =  new Itemcell(parent.getContext());

//            RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(screenWidth,(int)(screenWidth*0.36f));
//
//             v.setLayoutParams(param);
//            v.getIconView().getLayoutParams().height =(int)(MainActivity.getScreenWidth() *0.36f -10);
//            v.getIconView().getLayoutParams().width =(int)(MainActivity.getScreenWidth() *0.36f -10);

            FishPinpaiGoodsAdapter.ViewHolder vh = new FishPinpaiGoodsAdapter.ViewHolder( v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(FishPinpaiGoodsAdapter.ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            Customer item = mdataList.get(position);
            holder.setmPosition(position);

            Itemcell itemcell = holder.mitemView;

            itemcell.getTextView().setText(item.descriptionString());



        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mdataList.size();
        }


    }
}