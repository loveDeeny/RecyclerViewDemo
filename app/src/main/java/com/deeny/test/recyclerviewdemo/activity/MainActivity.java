package com.deeny.test.recyclerviewdemo.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.deeny.test.recyclerviewdemo.driver.DividerItemDecoration;
import com.deeny.test.recyclerviewdemo.adapter.MyAdapter;
import com.deeny.test.recyclerviewdemo.R;
import com.deeny.test.recyclerviewdemo.utils.AppUtils;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.bezierlayout.BezierLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {


    @InjectView(R.id.rv_recycler)
    RecyclerView rv_recycler;
    @InjectView(R.id.refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    private List<String> list;//数据源
    private MyAdapter myadapter;
    private int page;
    private boolean isRefresh;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 100:
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
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 无标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        list = new ArrayList<>();
        //initList();
        AppUtils.getData(0,list,handler);
        BezierLayout headview = new BezierLayout(this);
        refreshLayout.setHeaderView(headview);
        //listview的效果
        rv_recycler.setLayoutManager(new LinearLayoutManager(this));
        //rv_recycler.setAdapter(myadapter = new MyAdapter(this,list));
        rv_recycler.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        //gridview的效果
        /*rv_recycler.setLayoutManager(new GridLayoutManager(this,2));
        rv_recycler.setAdapter(myadapter = new MyAdapter(this,list));
        rv_recycler.addItemDecoration(new DividerGridItemDecoration(this));*/
        //瀑布流的样式：
        /*rv_recycler.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));

        rv_recycler.addItemDecoration(new DividerGridItemDecoration(this));*/

        refreshLayout.setOnRefreshListener(new TwinklingRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                isRefresh = true;
                if(list!= null){
                    list.clear();
                }

                page = 0;
                AppUtils.getData(page,list,handler);
                refreshLayout.finishRefreshing();
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                isRefresh = false;
                AppUtils.getData(++page,list,handler);
                refreshLayout.finishLoadmore();
            }
        });

    }

    /*private void initList(){
        list = new ArrayList<>();
        for (int i = 0; i <100 ; i++) {
            list.add("第"+i+"条");
        }
    }*/


}
